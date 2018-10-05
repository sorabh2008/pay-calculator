package com.calc.operation;

import com.calc.to.CalculatorRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * High Income Group - Tax Calculator
 * $180,001 and over
 * $54,232 plus 45c for each $1 over $180,000
 */
public class HIGTax implements ITax {
    private static final BigDecimal TAX_PERCENT = new BigDecimal(0.45);
    private static final BigDecimal EXEMPTION = new BigDecimal(180000);
    private static final BigDecimal MIN_TAX = new BigDecimal(54232);

    @Override
    public BigDecimal calculateTax(CalculatorRequest request, BigDecimal taxDuration) {
        return request.getAnnualSalary().subtract(EXEMPTION).multiply(TAX_PERCENT).add(MIN_TAX).divide(taxDuration, 0, RoundingMode.HALF_UP);

    }
}
