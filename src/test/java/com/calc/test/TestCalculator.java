package com.calc.test;

import com.calc.constants.CalcConstants;
import com.calc.constants.ErrorConstants;
import com.calc.impl.Calculator;
import com.calc.impl.PayCalculatorImpl;
import com.calc.to.CalculatorRequest;
import com.calc.to.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TestCalculator {
    private Calculator impl;
    private CalculatorRequest request;
    private Response response;

    public TestCalculator() {
        impl = new PayCalculatorImpl();
    }

    @Before
    public void setUp() {
        request = new CalculatorRequest();
        request.setPayFrequency(CalcConstants.MONTHLY);
        request.setFirstName("foo");
        request.setLastName("bar");
        request.setPaymentStartDate("01 March - 31 March");
        request.setSuperRate(new BigDecimal(10));
    }

    @After
    public void destruct() {
        impl = null;
        request = null;
        response = null;
    }

    @Test
    public void testUnknownPayFrequency() {

        CalculatorRequest request = new CalculatorRequest();
        request.setPayFrequency("foobar");
        response = impl.calculate(request);
        Assert.assertEquals(ErrorConstants.CODE_INVALID_FREQUENCY, response.getErrCode());

    }

    @Test
    public void testUMIGTax() {
        request.setAnnualSalary(new BigDecimal("18000"));
        response = impl.calculate(request);
        Assert.assertEquals(new Integer(1500), response.getCalculatorResponse().getGrossIncome());
        Assert.assertEquals("foo bar", response.getCalculatorResponse().getName());
        Assert.assertEquals("01 March - 31 March", response.getCalculatorResponse().getPayPeriod());
        Assert.assertEquals(new Integer(150), response.getCalculatorResponse().getSuperContribution());
        Assert.assertEquals(new Integer(1500), response.getCalculatorResponse().getNetIncome());
        Assert.assertEquals(new Integer(0), response.getCalculatorResponse().getIncomeTax());
    }

    @Test
    public void testLIGTax() {
        request.setAnnualSalary(new BigDecimal("30000"));
        response = impl.calculate(request);
        Assert.assertEquals(new Integer(2500), response.getCalculatorResponse().getGrossIncome());
        Assert.assertEquals("foo bar", response.getCalculatorResponse().getName());
        Assert.assertEquals("01 March - 31 March", response.getCalculatorResponse().getPayPeriod());
        Assert.assertEquals(new Integer(250), response.getCalculatorResponse().getSuperContribution());
        Assert.assertEquals(new Integer(2313), response.getCalculatorResponse().getNetIncome());
        Assert.assertEquals(new Integer(187), response.getCalculatorResponse().getIncomeTax());
    }

    @Test
    public void testMIGTax() {
        request.setAnnualSalary(new BigDecimal("90000"));
        response = impl.calculate(request);
        Assert.assertEquals(new Integer(7500), response.getCalculatorResponse().getGrossIncome());
        Assert.assertEquals("foo bar", response.getCalculatorResponse().getName());
        Assert.assertEquals("01 March - 31 March", response.getCalculatorResponse().getPayPeriod());
        Assert.assertEquals(new Integer(750), response.getCalculatorResponse().getSuperContribution());
        Assert.assertEquals(new Integer(5756), response.getCalculatorResponse().getNetIncome());
        Assert.assertEquals(new Integer(1744), response.getCalculatorResponse().getIncomeTax());
    }

    @Test
    public void testHMIGTax() {
        request.setAnnualSalary(new BigDecimal("166000"));
        response = impl.calculate(request);
        Assert.assertEquals(new Integer(13833), response.getCalculatorResponse().getGrossIncome());
        Assert.assertEquals("foo bar", response.getCalculatorResponse().getName());
        Assert.assertEquals("01 March - 31 March", response.getCalculatorResponse().getPayPeriod());
        Assert.assertEquals(new Integer(1383), response.getCalculatorResponse().getSuperContribution());
        Assert.assertEquals(new Integer(9745), response.getCalculatorResponse().getNetIncome());
        Assert.assertEquals(new Integer(4088), response.getCalculatorResponse().getIncomeTax());
    }

    @Test
    public void testHIGTax() {
        request.setAnnualSalary(new BigDecimal("240000"));
        response = impl.calculate(request);
        Assert.assertEquals(new Integer(20000), response.getCalculatorResponse().getGrossIncome());
        Assert.assertEquals("foo bar", response.getCalculatorResponse().getName());
        Assert.assertEquals("01 March - 31 March", response.getCalculatorResponse().getPayPeriod());
        Assert.assertEquals(new Integer(2000), response.getCalculatorResponse().getSuperContribution());
        Assert.assertEquals(new Integer(13231), response.getCalculatorResponse().getNetIncome());
        Assert.assertEquals(new Integer(6769), response.getCalculatorResponse().getIncomeTax());
    }

}
