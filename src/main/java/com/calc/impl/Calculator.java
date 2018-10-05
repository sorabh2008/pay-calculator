package com.calc.impl;

import com.calc.to.CalculatorRequest;
import com.calc.to.Response;

public interface Calculator {
    Response calculate(CalculatorRequest request);
}
