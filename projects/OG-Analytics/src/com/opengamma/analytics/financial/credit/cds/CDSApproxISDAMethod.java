/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.credit.cds;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import javax.time.calendar.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.YieldCurveBundle;
import com.opengamma.analytics.financial.interestrate.method.PricingMethod;
import com.opengamma.analytics.financial.interestrate.payments.derivative.CouponFixed;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldAndDiscountCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldCurve;
import com.opengamma.financial.convention.daycount.ActualThreeSixtyFive;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.util.money.CurrencyAmount;

/**
 * An approximation to the calculation method for the ISDA CDS model
 * 
 * @author Martin Traverse, Niels Stchedroff (Riskcare)
 */
public class CDSApproxISDAMethod implements PricingMethod {
  
  private static final Logger s_logger = LoggerFactory.getLogger(CDSApproxISDAMethod.class);
  
  private static DayCount s_act365 = new ActualThreeSixtyFive();

  @Override
  public CurrencyAmount presentValue(InstrumentDerivative instrument, YieldCurveBundle curves) {
    throw new RuntimeException("please pass in the pricing date");
  }
  
  public CurrencyAmount presentValue(InstrumentDerivative instrument, YieldCurveBundle curves, ZonedDateTime pricingDate, ZonedDateTime stepinDate, ZonedDateTime settlementDate, boolean cleanPrice) {
    
    CDSDerivative cds = (CDSDerivative) instrument;
    YieldAndDiscountCurve discountCurve = curves.getCurve(cds.getDiscountCurveName());
    YieldAndDiscountCurve spreadCurve = curves.getCurve(cds.getSpreadCurveName());

    return CurrencyAmount.of(cds.getPremium().getCurrency(), calculateUpfrontCharge(cds, discountCurve, spreadCurve, pricingDate, stepinDate, settlementDate, cleanPrice));
  }

  /**
   * Calculate the up-front charge of for a CDS. This is what would change hands if the contract were traded.
   * and is effectively its PV.
   * 
   * Step-in date is the date when ownership of protection is assumed and is normally taken as T+1, so a CDS traded
   * on a particular day becomes effective the next day at 00:01. To value an new CDS contract with start date
   * T >= 0, then step-in date = T+1. To value an existing contract at some point T, step-in date = T+1 again
   * but now start date < 0.
   * 
   * @param cds The CDS to be valued
   * @param discountCurve The discount curve
   * @param spreadCurve The credit spread curve
   * @param pricingDate The pricing date
   * @param stepinDate The step-in date
   * @return PV of the CDS contract
   */
  public double calculateUpfrontCharge(CDSDerivative cds, YieldAndDiscountCurve discountCurve, YieldAndDiscountCurve spreadCurve, ZonedDateTime pricingDate, ZonedDateTime stepinDate, ZonedDateTime settlementDate, boolean cleanPrice) {
    
    if (stepinDate.isBefore(pricingDate)) {
      throw new OpenGammaRuntimeException("Cannot value a CDS with step-in date before pricing date");
    }
    
    final double contingentLeg = valueContingentLeg(cds, discountCurve, spreadCurve, pricingDate, stepinDate, settlementDate);
    final double feeLeg = valueFeeLeg(cds, discountCurve, spreadCurve, pricingDate, stepinDate, settlementDate);
    final double dirtyPrice = (contingentLeg - feeLeg) * cds.getNotional();
    
    System.out.println("contingent=" + contingentLeg + ", fee=" + feeLeg);
    
    return cleanPrice ? dirtyPrice + cds.getAccruedInterest() : dirtyPrice;
  }

  /**
   * Value the premium leg of a CDS taken from the step-in date.
   * 
   * @param cds The CDS contract being priced
   * @param discountCurve The discount curve
   * @param hazardCurve Curve describing the hazard rate function
   * @param pricingDate The pricing date
   * @param stepinDate The step-in date
   * @return PV of the CDS premium leg
   */
  private double valueFeeLeg(CDSDerivative cds, YieldAndDiscountCurve discountCurve, YieldAndDiscountCurve hazardCurve, ZonedDateTime pricingDate, ZonedDateTime stepinDate, ZonedDateTime settlementDate) {
    
    // If the "protect start" flag is set, then the start date of the CDS is protected and observations are made at the start
    // of the day rather than the end. This is modelled by shifting all period start/end dates one day forward,
    // and adding one extra day's protection to the final period
    
    final CouponFixed[] premiums = cds.getPremium().getPayments();
    final int maturityIndex = premiums.length - 1;
    final int offset = cds.isProtectStart() ? 1 : 0;
    
    final ZonedDateTime startDate = premiums[0].getAccrualStartDate();
    final ZonedDateTime maturityDate = premiums[premiums.length - 1].getAccrualEndDate().plusDays(offset);
    final double startTime = getTimeBetween(pricingDate, startDate);
    final double maturity = getTimeBetween(pricingDate, maturityDate);
    final double offsetStepinTime = getTimeBetween(pricingDate, stepinDate.minusDays(offset));
    final double settlementTime = getTimeBetween(pricingDate, settlementDate);

    // List of all time points for which real data points exist on the discount/spread curves, only need for accrual on default calculation
    final NavigableSet<Double> timeline = cds.isAccrualOnDefault() ? buildTimeLine(discountCurve, hazardCurve, startTime, maturity) : null;
    
    System.out.println(timeline.toString());
    
    CouponFixed payment;
    ZonedDateTime accrualPeriodStart, accrualPeriodEnd;
    double periodStart, periodEnd;
    double amount, survival, discount;
    double result = 0.0;
    
    for (int i = 0; i < premiums.length; i++) {
      
      // TODO: Use payment date != period end date for discount of final payment, since maturity is not adjusted for calendars
      payment = premiums[i];
      accrualPeriodEnd = i < maturityIndex ? payment.getAccrualEndDate() : maturityDate;
      periodEnd = getTimeBetween(pricingDate, accrualPeriodEnd.minusDays(offset));

      amount = payment.getFixedRate() * payment.getPaymentYearFraction();
      survival = hazardCurve.getDiscountFactor(periodEnd);
      discount = discountCurve.getDiscountFactor(periodEnd);
      result += amount * survival * discount;
      
      if (s_logger.isDebugEnabled()) {
        s_logger.debug("i=" + i + ", accrualEnd=" + accrualPeriodEnd + ", t-value=" + periodEnd + ", accrualTime=" + payment.getPaymentYearFraction());
        s_logger.debug("ammount=" + amount + ", survival=" + survival + ", discount=" + discount + ", pv=" + amount * survival * discount);
      }
      
      if (cds.isAccrualOnDefault()) {
        timeline.add(periodEnd);
        accrualPeriodStart = payment.getAccrualStartDate();
        periodStart = getTimeBetween(pricingDate, accrualPeriodStart.minusDays(offset));
        result += valueFeeLegAccrualOnDefault(amount, periodStart, periodEnd, offsetStepinTime, discountCurve, hazardCurve, timeline);
      }
    }
    
    System.out.println("fee undiscounted: " + result);
    return result / discountCurve.getDiscountFactor(settlementTime);
  }

  /**
   * Calculate the accrual-on-default portion of the PV for a specified accrual period.
   * 
   * @param amount Amount of premium that would be accrued over the entire period (in the case of no default)
   * @param startTime Accrual period start time
   * @param endTime Accrual period end time
   * @param stepinTime Step-in time for the CDS contract
   * @param discountCurve The discount curve
   * @param hazardCurve Curve describing the hazard rate function
   * @param fullTimeline List of time points corresponding to real data points on the discount and spread curves
   * @return Accrual-on-default portion of PV for the accrual period
   */
  private double valueFeeLegAccrualOnDefault(final double amount, final double startTime, final double endTime, final double stepinTime,
    YieldAndDiscountCurve discountCurve, YieldAndDiscountCurve hazardCurve, NavigableSet<Double> fullTimeline) {
    
    final double today = 0.0;
    final double subStartTime = stepinTime > startTime ? stepinTime : startTime;
    final double accrualRate = amount / (endTime - startTime); // TODO: Handle startTime == endTime
    
    final Double[] timeline = truncateTimeLine(fullTimeline, startTime, endTime);

    double t0, t1, dt, survival0, survival1, discount0, discount1;
    double lambda, fwdRate, lambdaFwdRate, valueForTimeStep, value;
    
    t0 = subStartTime - startTime + (0.5 / 365.0);
    survival0 = hazardCurve.getDiscountFactor(subStartTime);
    discount0 = discountCurve.getDiscountFactor(Math.max(today, subStartTime));
    
    value = 0.0;

    for (int i = 1; i < timeline.length; ++i) {

      if (timeline[i] <= stepinTime) {
        continue;
      }

      t1 = timeline[i] - startTime + (0.5 / 365.0);
      dt = t1 - t0;
      
      survival1 = hazardCurve.getDiscountFactor(timeline[i]);
      discount1 = discountCurve.getDiscountFactor(timeline[i]);

      lambda = Math.log(survival0 / survival1) / dt;
      fwdRate = Math.log(discount0 / discount1) / dt;
      lambdaFwdRate = lambda + fwdRate + 1.0e-50;
      valueForTimeStep = lambda * accrualRate * survival0 * discount0
        * (((t0 + 1.0 / lambdaFwdRate) / lambdaFwdRate) - ((t1 + 1.0 / lambdaFwdRate) / lambdaFwdRate) * survival1 / survival0 * discount1 / discount0);

      value += valueForTimeStep;

      t0 = t1;
      survival0 = survival1;
      discount0 = discount1;
    }

    s_logger.debug("Accrual=" + value);
    return value;
  }

  /**
   * Value the contingent leg of a CDS taken from the step-in date.
   * 
   * @param cds The derivative object representing the CDS
   * @param discountCurve The discount curve
   * @param hazardCurve Curve describing the hazard rate function
   * @param pricingDate The pricing date
   * @param stepinDate The step-in date
   * @return PV of the CDS default leg
   */
  private double valueContingentLeg(CDSDerivative cds, YieldAndDiscountCurve discountCurve, YieldAndDiscountCurve hazardCurve, ZonedDateTime pricingDate, ZonedDateTime stepinDate, ZonedDateTime settlementDate) {

    final CouponFixed[] premiums = cds.getPremium().getPayments();
    final ZonedDateTime startDate = premiums[0].getAccrualStartDate();
    final ZonedDateTime maturityDate = premiums[premiums.length - 1].getAccrualEndDate();
    
    final int offset = cds.isProtectStart() ? 1 : 0;
    final ZonedDateTime offsetStepinDate = stepinDate.minusDays(offset);
    final ZonedDateTime offsetPricingDate = pricingDate.minusDays(offset);
    
    final ZonedDateTime valuationStartDate =
      startDate.isAfter(offsetStepinDate)
      ? startDate.isAfter(offsetPricingDate)
        ? startDate
        : offsetPricingDate
      : offsetStepinDate.isAfter(offsetPricingDate)
        ? offsetStepinDate
        : offsetPricingDate;
    
    final double valuationStartTime = getTimeBetween(pricingDate, valuationStartDate);
    final double maturity = getTimeBetween(pricingDate, maturityDate);
    final double settlementTime = getTimeBetween(pricingDate, settlementDate);
    final double recoveryRate = cds.getRecoveryRate();
    
    final double value = cds.isPayOnDefault()
      ? valueContingentLegPayOnDefault(recoveryRate, valuationStartTime, maturity, discountCurve, hazardCurve)
      : valueContingentLegPayOnMaturity(recoveryRate, valuationStartTime, maturity, discountCurve, hazardCurve);

      System.out.println("cont undiscounted: " + value);
    return value / discountCurve.getDiscountFactor(settlementTime);
  }

  /**
   * Value the default leg, assuming any possible payout is received at the time of default.
   * 
   * @param recoveryRate Recovery rate of the CDS underlying asset
   * @param startTime protection start time
   * @param maturity CDS maturity
   * @param discountCurve The discount curve
   * @param hazardCurve Curve describing the hazard rate function
   * @return PV for the default leg
   */
  private double valueContingentLegPayOnDefault(final double recoveryRate, final double startTime, final double maturity,
    YieldAndDiscountCurve discountCurve, YieldAndDiscountCurve hazardCurve) {
    
    if (maturity < 0.0) {
      return 0.0;
    }
    
    final double loss = 1.0 - recoveryRate;
    final Double[] timeline = truncateTimeLine(buildTimeLine(discountCurve, hazardCurve, startTime, maturity), startTime, maturity);

    double dt, survival0, survival1, discount0, discount1;
    double lambda, fwdRate, valueForTimeStep, value;
    
    // TODO: handle startTime == -1 day
    survival1 = hazardCurve.getDiscountFactor(startTime);
    discount1 = startTime > 0.0 ? discountCurve.getDiscountFactor(startTime) : 1.0;
    value = 0.0;

    for (int i = 1; i < timeline.length; ++i) {

      dt = timeline[i] - timeline[i - 1];

      survival0 = survival1;
      discount0 = discount1;
      survival1 = hazardCurve.getDiscountFactor(timeline[i]);
      discount1 = discountCurve.getDiscountFactor(timeline[i]);

      lambda = Math.log(survival0 / survival1) / dt;
      fwdRate = Math.log(discount0 / discount1) / dt;
      valueForTimeStep = ((loss * lambda) / (lambda + fwdRate)) * (1.0 - Math.exp(-(lambda + fwdRate) * dt)) * survival0 * discount0;

      value += valueForTimeStep;
    }

    return value;
  }
  
  /**
   * Value the default leg, assuming any possible payout is received at maturity.
   * 
   * @param recoveryRate Recovery rate of the CDS underlying asset
   * @param startTime protection start time
   * @param maturity CDS maturity
   * @param discountCurve The discount curve
   * @param hazardCurve Curve describing the hazard rate function
   * @return PV for the default leg
   */
  private double valueContingentLegPayOnMaturity(final double recoveryRate, final double startTime, final double maturity,
    YieldAndDiscountCurve discountCurve, YieldAndDiscountCurve hazardCurve) {
    
    if (maturity < 0.0) {
      return 0.0;
    }
    
    // TODO: handle startTime == -1 day
    final double loss = 1.0 - recoveryRate;
    final double survival0 = hazardCurve.getDiscountFactor(startTime);
    final double survival1 = hazardCurve.getDiscountFactor(maturity);
    final double discount = discountCurve.getDiscountFactor(maturity);
    
    return (survival0 - survival1) * discount * loss;
  }

  /**
   * Build a set of time points corresponding to data points on the discount and spread curve.
   * 
   * @param discountCurve The discount curve
   * @param spreadCurve The spread curve
   * @param startTime The start time is included as an extra data point on the curve
   * @param endTime The end time is included as an extra data point on the curve
   * 
   * @return A set of time points
   */
  public static NavigableSet<Double> buildTimeLine(YieldAndDiscountCurve discountCurve, YieldAndDiscountCurve spreadCurve, double startTime, double endTime) {

    if (!(discountCurve instanceof YieldCurve) || !(spreadCurve instanceof YieldCurve)) {
      throw new OpenGammaRuntimeException("ISDA CDS model requires YieldCurve objects for discount and spread curves");
    }

    final Double[] cdsCcyCurveTimes = ((YieldCurve) discountCurve).getCurve().getXData();
    final Double[] spreadCurveTimes = ((YieldCurve) spreadCurve).getCurve().getXData();

    NavigableSet<Double> timePoints = new TreeSet<Double>();
    
    for (int i = 0; i < cdsCcyCurveTimes.length; i++) {
      timePoints.add(cdsCcyCurveTimes[i]);
    }

    for (int i = 0; i < spreadCurveTimes.length; i++) {
      timePoints.add(spreadCurveTimes[i]);
    }

    timePoints.add(startTime);
    timePoints.add(endTime);      
    
    return timePoints;
  }
  
  /**
   * Truncate a time line to the range [startTime, endTime]. The start and end times are added
   * to the list of time points in the time line if they do not already exist.
   * 
   * If endTime < startTime results are undefined
   * 
   * @param fullTimeLine The original time line to be truncated
   * @param startTime The start time to truncate to
   * @param endTime The end time to truncate to
   * @return An array representing the time points in the truncated time line
   */
  public static Double[] truncateTimeLine(final NavigableSet<Double> fullTimeLine, double startTime, double endTime) {
    
    final Set<Double> timePointsInRange = fullTimeLine.subSet(startTime, true, endTime, true);
    return timePointsInRange.toArray(new Double[timePointsInRange.size()]);
  }
  
  /**
   * Return the fraction of a year between two dates according the the ACT365 convention.
   * If date2 < date1 the result will be negative.
   * 
   * @param date1 The first date
   * @param date2 The second date
   * @return Real time value in years
   */
  public static double getTimeBetween(final ZonedDateTime date1, final ZonedDateTime date2) {

    final ZonedDateTime rebasedDate2 = date2.withZoneSameInstant(date1.getZone());
    final boolean timeIsNegative = date1.isAfter(rebasedDate2);

    if (!timeIsNegative) {
      return s_act365.getDayCountFraction(date1, rebasedDate2);
    } else {
      return -1.0 * s_act365.getDayCountFraction(rebasedDate2, date1);
    }
  }
}
