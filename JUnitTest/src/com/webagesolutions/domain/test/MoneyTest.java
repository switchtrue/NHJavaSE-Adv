package com.webagesolutions.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.webagesolutions.domain.Money;

public class MoneyTest
{
  @Test
  public void testCompareTo()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testEqualsObject()
  {
    fail("Not yet implemented");
  }

  @Test
  public void testToString()
  {
    assertEquals("$7.32", new Money(7, 32, "$").toString());
  }
  
  @Test
  public void testToString_SingleDigitCents()
  {
    assertEquals("$7.02", new Money(7, 2, "$").toString());
  }
  
  @Test
  public void testToString_ZeroCents()
  {
    assertEquals("$7", new Money(7, 0, "$").toString());
  }

}
