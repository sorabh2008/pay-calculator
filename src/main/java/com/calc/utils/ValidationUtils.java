package com.calc.utils;

import com.calc.constants.CalcConstants;

import java.math.BigDecimal;

public class ValidationUtils {

    public static boolean isPositive(BigDecimal toCheck) {
        return (toCheck.compareTo(BigDecimal.ZERO) > 0);
    }

    public static boolean isEmpty(String toCheck) {
        return toCheck == null || toCheck.length() < 1;
    }

    public static boolean isValidFrequency(String frequency) {
        switch (frequency.toLowerCase()) {
            case CalcConstants.MONTHLY:
//            case "WEEKLY":
//            case "FORTNIGHTLY":
                return true;
            default:
                return false;
        }
    }
}
