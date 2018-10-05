package com.calc.operation;

import com.calc.to.CalculatorRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Medium Income Group - Tax Calculator
 * $37,001 - $87,000
 * $3,572 plus 32.5c for each $1 over $37,000
 */
public class MIGTax implements ITax {
    private static final BigDecimal TAX_PERCENT = new BigDecimal(0.325);
    private static final BigDecimal EXEMPTION = new BigDecimal(37000);
    private static final BigDecimal MIN_TAX = new BigDecimal(3572);

    @Override
    public BigDecimal calculateTax(CalculatorRequest request, BigDecimal taxDuration) {
        return request.getAnnualSalary().subtract(EXEMPTION).multiply(TAX_PERCENT).add(MIN_TAX).divide(taxDuration, 0, RoundingMode.HALF_UP);
    }
}
