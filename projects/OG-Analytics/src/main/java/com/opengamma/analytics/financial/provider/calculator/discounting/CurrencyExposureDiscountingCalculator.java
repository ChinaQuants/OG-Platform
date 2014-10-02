/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.provider.calculator.discounting;

import com.opengamma.analytics.financial.forex.derivative.Forex;
import com.opengamma.analytics.financial.forex.derivative.ForexNonDeliverableForward;
import com.opengamma.analytics.financial.forex.provider.ForexDiscountingMethod;
import com.opengamma.analytics.financial.forex.provider.ForexNonDeliverableForwardDiscountingMethod;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitorDelegate;
import com.opengamma.analytics.financial.interestrate.annuity.derivative.Annuity;
import com.opengamma.analytics.financial.interestrate.payments.derivative.CouponFixedFxReset;
import com.opengamma.analytics.financial.interestrate.payments.derivative.Payment;
import com.opengamma.analytics.financial.interestrate.payments.provider.CouponFixedFxResetDiscountingMethod;
import com.opengamma.analytics.financial.interestrate.swap.derivative.Swap;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapFixedCoupon;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapMultileg;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderInterface;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.MultipleCurrencyAmount;
import com.opengamma.util.money.MultipleCurrencyAmountPricer;

/**
 * Calculates the present value of an inflation instruments by discounting for a given MarketBundle
 */
public final class CurrencyExposureDiscountingCalculator extends InstrumentDerivativeVisitorDelegate<MulticurveProviderInterface, MultipleCurrencyAmount> {

  /**
   * The unique instance of the calculator.
   */
  private static final CurrencyExposureDiscountingCalculator INSTANCE = new CurrencyExposureDiscountingCalculator();

  /**
   * Gets the calculator instance.
   * @return The calculator.
   */
  public static CurrencyExposureDiscountingCalculator getInstance() {
    return INSTANCE;
  }

  /**
   * Constructor.
   */
  private CurrencyExposureDiscountingCalculator() {
    super(PresentValueDiscountingCalculator.getInstance());
  }

  /**
   * The methods used by the different instruments.
   */
  private static final CouponFixedFxResetDiscountingMethod METHOD_CPN_FIXED_FXRESET = 
      CouponFixedFxResetDiscountingMethod.getInstance();
  private static final ForexDiscountingMethod METHOD_FOREX = ForexDiscountingMethod.getInstance();
  private static final ForexNonDeliverableForwardDiscountingMethod METHOD_FOREX_NDF = 
      ForexNonDeliverableForwardDiscountingMethod.getInstance();

  // -----     Coupon     ------

  @Override
  public MultipleCurrencyAmount visitCouponFixedFxReset(final CouponFixedFxReset coupon, 
      final MulticurveProviderInterface multicurve) {
    return METHOD_CPN_FIXED_FXRESET.currencyExposure(coupon, multicurve);
  }

  // -----     Forex     ------

  @Override
  public MultipleCurrencyAmount visitForex(final Forex derivative, final MulticurveProviderInterface multicurves) {
    return METHOD_FOREX.currencyExposure(derivative, multicurves);
  }

  @Override
  public MultipleCurrencyAmount visitForexNonDeliverableForward(final ForexNonDeliverableForward derivative, 
      final MulticurveProviderInterface multicurves) {
    return METHOD_FOREX_NDF.currencyExposure(derivative, multicurves);
  }

  // -----     Annuity     ------

  @Override
  public MultipleCurrencyAmount visitGenericAnnuity(final Annuity<? extends Payment> annuity, final MulticurveProviderInterface multicurve) {
    ArgumentChecker.notNull(annuity, "Annuity");
    ArgumentChecker.notNull(multicurve, "multicurve");
    MultipleCurrencyAmount pv = annuity.getNthPayment(0).accept(this, multicurve);
    MultipleCurrencyAmountPricer pricer = new MultipleCurrencyAmountPricer(pv);
    for (int i = 1; i < annuity.getNumberOfPayments(); i++) {
      pricer.plus(annuity.getNthPayment(i).accept(this, multicurve));
    }
    return pricer.getSum();
  }

  // -----     Swap     ------

  @Override
  public MultipleCurrencyAmount visitSwap(final Swap<?, ?> swap, final MulticurveProviderInterface multicurve) {
    final MultipleCurrencyAmount ce1 = swap.getFirstLeg().accept(this, multicurve);
    final MultipleCurrencyAmount ce2 = swap.getSecondLeg().accept(this, multicurve);
    return ce1.plus(ce2);
  }

  @Override
  public MultipleCurrencyAmount visitFixedCouponSwap(final SwapFixedCoupon<?> swap, final MulticurveProviderInterface multicurves) {
    return visitSwap(swap, multicurves);
  }

  @Override
  public MultipleCurrencyAmount visitSwapMultileg(final SwapMultileg swap, final MulticurveProviderInterface multicurve) {
    final int nbLegs = swap.getLegs().length;
    MultipleCurrencyAmount ce = swap.getLegs()[0].accept(this, multicurve);
    for (int loopleg = 1; loopleg < nbLegs; loopleg++) {
      ce = ce.plus(swap.getLegs()[loopleg].accept(this, multicurve));
    }
    return ce;
  }

}
