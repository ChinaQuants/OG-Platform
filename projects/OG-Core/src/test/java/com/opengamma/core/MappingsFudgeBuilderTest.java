/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.opengamma.core.Mappings;
import com.opengamma.util.test.AbstractFudgeBuilderTestCase;

public class MappingsFudgeBuilderTest extends AbstractFudgeBuilderTestCase {

  @Test
  public void roundTrip() {
    Mappings mappings = new Mappings(ImmutableMap.of("foo", "bar", "baz", "boz"));
    assertEncodeDecodeCycle(Mappings.class, mappings);
  }
}
