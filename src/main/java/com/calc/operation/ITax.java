package com.calc.operation;

import com.calc.to.CalculatorRequest;

import java.math.BigDecimal;

public interface ITax {
    BigDecimal calculateTax(CalculatorRequest request, BigDecimal taxDuration);
}
