/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.rootfinding.newton;


/**
 * Attempts to find the multi-dimensional root of a series of N equations with N variables, i.e. a square problem. 
 * If the analytic Jacobian is not known, it will be calculated using central difference 
 */
public class NewtonDefaultVectorRootFinder extends NewtonVectorRootFinder {
  private static final double DEF_TOL = 1e-7;
  private static final int MAX_STEPS = 100;

  public NewtonDefaultVectorRootFinder() {
    this(DEF_TOL, DEF_TOL, MAX_STEPS);
  }

  public NewtonDefaultVectorRootFinder(final double absoluteTol, final double relativeTol, final int maxSteps) {
    super(absoluteTol, relativeTol, maxSteps, new JacobianDirectionFunction(), new JacobianEstimateInitializationFunction(), new NewtonDefaultUpdateFunction());
  }

}
