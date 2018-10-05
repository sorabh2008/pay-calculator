package com.calc.factory;

import com.calc.constants.CalcConstants;
import com.calc.constants.ErrorConstants;
import com.calc.exception.CalException;
import com.calc.operation.ICalc;
import com.calc.operation.MonthlyPayCalculator;

/**
 * @description: Factory to get calculator operation based on pay frequency
 * @param: frequency - payment frequency
 * @return: Calculator object
 */
public class CalculatorFactory {
    public static ICalc getCalculator(String frequency) throws CalException {
        switch (frequency.toLowerCase()) {
            case CalcConstants.MONTHLY:
                return new MonthlyPayCalculator();
            default:
                throw new CalException(ErrorConstants.CODE_INVALID_FREQUENCY, ErrorConstants.MSG_INVALID_FREQUENCY);
        }
    }
}
