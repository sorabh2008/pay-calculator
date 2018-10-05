package com.calc.operation;

import com.calc.to.CalculatorRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Low Income Group - Tax Calculator
 * $18,201 - $37,000
 * 19c for each $1 over $18,200
 */
public class LIGTax implements ITax {
    private static final BigDecimal TAX_PERCENT = new BigDecimal(0.19);
    private static final BigDecimal EXEMPTION = new BigDecimal(18201);

    @Override
    public BigDecimal calculateTax(CalculatorRequest request, BigDecimal taxDuration) {
        return request.getAnnualSalary().subtract(EXEMPTION).multiply(TAX_PERCENT).divide(taxDuration, 0, RoundingMode.HALF_UP);
    }
}
