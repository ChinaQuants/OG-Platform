/**
 * Copyright (C) 2009 - 2011 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.model.option.pricing.analytic.formula;

import org.apache.activemq.util.BitArray;
import org.apache.commons.lang.Validate;

import com.opengamma.math.function.ParameterizedFunction;
import com.opengamma.math.matrix.DoubleMatrix1D;
import com.opengamma.math.matrix.DoubleMatrix2D;
import com.opengamma.math.minimization.NullTransform;
import com.opengamma.math.minimization.ParameterLimitsTransform;
import com.opengamma.math.minimization.SingleRangeLimitTransform;
import com.opengamma.math.minimization.TransformParameters;
import com.opengamma.math.statistics.leastsquare.LeastSquareResults;
import com.opengamma.math.statistics.leastsquare.NonLinearLeastSquare;

/**
 * 
 */
public class SVIFitter {

  private static final NonLinearLeastSquare SOLVER = new NonLinearLeastSquare();

  private static final int N_PARAMETERS = 5;
  private final SVIFormula _formula = new SVIFormula();

  private static final ParameterLimitsTransform[] TRANSFORMS;

  static {
    TRANSFORMS = new ParameterLimitsTransform[N_PARAMETERS];

    TRANSFORMS[0] = new NullTransform();
    TRANSFORMS[1] = new NullTransform();
    TRANSFORMS[2] = new NullTransform();// DoubleRangeLimitTransform(-1.0, 1.0);
    TRANSFORMS[3] = new SingleRangeLimitTransform(0, true);
    TRANSFORMS[4] = new SingleRangeLimitTransform(0, true);
  }

  public LeastSquareResults solve(final double[] strikes, final double[] blackVols, final double[] errors, final double[] initialValues, final BitArray fixed) {

    int n = strikes.length;
    Validate.isTrue(n == blackVols.length, "strikes and vols must be same length");
    Validate.isTrue(n == errors.length, "errors and vols must be same length");

    final TransformParameters transforms = new TransformParameters(new DoubleMatrix1D(initialValues), TRANSFORMS, fixed);

    final ParameterizedFunction<Double, DoubleMatrix1D, Double> function = new ParameterizedFunction<Double, DoubleMatrix1D, Double>() {
      @SuppressWarnings("synthetic-access")
      @Override
      public Double evaluate(Double strike, DoubleMatrix1D fp) {
        DoubleMatrix1D mp = transforms.inverseTransform(fp);
        final double a = mp.getEntry(0);
        final double b = mp.getEntry(1);
        final double rho = mp.getEntry(2);
        final double sigma = mp.getEntry(3);
        final double m = mp.getEntry(4);

        return _formula.impliedVolatility(strike, a, b, rho, sigma, m);
      }
    };

    DoubleMatrix1D fp = transforms.transform(new DoubleMatrix1D(initialValues));
    LeastSquareResults lsRes = SOLVER.solve(new DoubleMatrix1D(strikes), new DoubleMatrix1D(blackVols), new DoubleMatrix1D(errors), function, fp);
    DoubleMatrix1D mp = transforms.inverseTransform(lsRes.getParameters());

    return new LeastSquareResults(lsRes.getChiSq(), mp, new DoubleMatrix2D(new double[N_PARAMETERS][N_PARAMETERS]));

  }

}
