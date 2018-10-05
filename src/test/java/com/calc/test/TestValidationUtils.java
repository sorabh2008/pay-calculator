package com.calc.test;

import com.calc.constants.CalcConstants;
import com.calc.utils.ValidationUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TestValidationUtils {
    @Test
    public void testPositiveNumber() {
        Assert.assertTrue(ValidationUtils.isPositive(new BigDecimal(20)));
    }

    @Test
    public void testNegativeNumber() {
        Assert.assertFalse(ValidationUtils.isPositive(new BigDecimal(-20)));
    }

    @Test
    public void testEmpty() {
        Assert.assertTrue(ValidationUtils.isEmpty(""));
    }

    @Test
    public void testNotEmpty() {
        Assert.assertFalse(ValidationUtils.isEmpty("foo"));
    }

    @Test
    public void testValidFrequency() {
        Assert.assertTrue(ValidationUtils.isValidFrequency(CalcConstants.MONTHLY));
    }

    @Test
    public void testInvalidFrequency() {
        Assert.assertFalse(ValidationUtils.isValidFrequency("bar"));
    }
}
