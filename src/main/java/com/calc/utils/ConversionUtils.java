package com.calc.utils;

import com.calc.constants.ErrorConstants;
import com.calc.exception.CalException;

import java.math.BigDecimal;

public class ConversionUtils {
    public static BigDecimal stringToBigDecimal(String toConvert) throws CalException {
        if (null == toConvert || toConvert.length() < 1)
            throw new CalException(ErrorConstants.CODE_INVALID_INPUT, ErrorConstants.CODE_INVALID_INPUT);
        BigDecimal converted = null;
        try {
            toConvert = toConvert.trim();
            toConvert = toConvert.replace("%", "");
            converted = new BigDecimal(toConvert);
        } catch (Exception e) {
            throw new CalException(ErrorConstants.CODE_INVALID_CONVERSION, ErrorConstants.MSG_INVALID_CONVERSION);

        }
        return converted;
    }
}
