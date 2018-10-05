package com.calc.manager;

import com.calc.exception.CalException;
import com.calc.factory.CalculatorFactory;
import com.calc.operation.ICalc;
import com.calc.to.CalculatorRequest;
import com.calc.to.CalculatorResponse;

public class CalculatorManager {

    public CalculatorResponse calculate(CalculatorRequest req) throws CalException {
        ICalc executor = CalculatorFactory.getCalculator(req.getPayFrequency());
        CalculatorResponse resp = executor.compute(req);
        return resp;
    }
}
