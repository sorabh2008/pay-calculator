package com.calc.operation;

import com.calc.to.CalculatorRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * High Medium Income Group - Tax Calculator
 * $87,001 - $180,000
 * $19,822 plus 37c for each $1 over $87,000
 */
public class HMIGTax implements ITax {
    private static final BigDecimal TAX_PERCENT = new BigDecimal(0.37);
    private static final BigDecimal EXEMPTION = new BigDecimal(87000);
    private static final BigDecimal MIN_TAX = new BigDecimal(19822);

    @Override
    public BigDecimal calculateTax(CalculatorRequest request, BigDecimal taxDuration) {
        return request.getAnnualSalary().subtract(EXEMPTION).multiply(TAX_PERCENT).add(MIN_TAX).divide(taxDuration, 0, RoundingMode.HALF_UP);

    }
}
