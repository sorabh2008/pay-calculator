package com.calc.test;

import com.calc.constants.ErrorConstants;
import com.calc.exception.CalException;
import com.calc.utils.ConversionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TestConversionUtils {

    @Test
    public void testInvalidInput() {
        String toConvert = "";
        BigDecimal converted = null;
        try {
            converted = ConversionUtils.stringToBigDecimal(toConvert);
        } catch (CalException cex) {
            Assert.assertEquals(ErrorConstants.CODE_INVALID_INPUT, cex.getErrorCode());
        }
        Assert.assertNull(converted);
    }

    @Test
    public void testNullInput() {
        String toConvert = null;
        BigDecimal converted = null;
        try {
            converted = ConversionUtils.stringToBigDecimal(toConvert);
        } catch (CalException cex) {
            Assert.assertEquals(ErrorConstants.CODE_INVALID_INPUT, cex.getErrorCode());
        }
        Assert.assertNull(converted);
    }

    @Test
    public void testInvalidConversion() {
        String toConvert = "123asd";
        BigDecimal converted = null;
        try {
            converted = ConversionUtils.stringToBigDecimal(toConvert);
        } catch (CalException cex) {
            Assert.assertEquals(ErrorConstants.CODE_INVALID_CONVERSION, cex.getErrorCode());
        }
        Assert.assertNull(converted);
    }

    @Test
    public void testAnnualSalary() throws CalException {
        String annualSalary = "120000";
        BigDecimal converted = null;
        converted = ConversionUtils.stringToBigDecimal(annualSalary);
        Assert.assertNotNull(converted);
        Assert.assertEquals(0, converted.compareTo(new BigDecimal(120000)));
    }

    @Test
    public void testSuperRate() throws CalException {
        String annualSalary = "10%";
        BigDecimal converted = null;
        converted = ConversionUtils.stringToBigDecimal(annualSalary);
        Assert.assertNotNull(converted);
        Assert.assertEquals(0, converted.compareTo(new BigDecimal(10)));
    }
}
