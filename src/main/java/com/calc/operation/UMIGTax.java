package com.calc.operation;

import com.calc.to.CalculatorRequest;

import java.math.BigDecimal;

/**
 * Under Minimum Income Group - Tax Calculator
 * $0 - $18,200
 * Nil
 */
public class UMIGTax implements ITax {

    @Override
    public BigDecimal calculateTax(CalculatorRequest request, BigDecimal taxDuration) {
        return new BigDecimal(0);
    }
}
