package com.calc.factory;

import com.calc.operation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @description: Factory to return Tax Slab
 * @param: annualSalary - any positive number
 * @return: Tax slab object
 */
public class TaxFactory {
    private static final BigDecimal UMIG = new BigDecimal(18200);
    private static final BigDecimal LIG = new BigDecimal(37000);
    private static final BigDecimal MIG = new BigDecimal(87000);
    private static final BigDecimal HMIG = new BigDecimal(180000);

    public static ITax getTaxCalculator(BigDecimal annualSalary) {
        //UMIG: $0 - $18,200 : Nil
        if (annualSalary.subtract(UMIG, new MathContext(0, RoundingMode.HALF_UP)).intValue() <= 0)
            return new UMIGTax();
            //LIG : $18,201 - $37,000 : 19c for each $1 over $18,200
        else if (annualSalary.subtract(LIG, new MathContext(0, RoundingMode.HALF_UP)).intValue() <= 0)
            return new LIGTax();
            //MIG : $37,001 - $87,000 : $3,572 plus 32.5c for each $1 over $37,000
        else if (annualSalary.subtract(MIG, new MathContext(0, RoundingMode.HALF_UP)).intValue() <= 0)
            return new MIGTax();
            //HMIG: $87,001 - $180,000 : $19,822 plus 37c for each $1 over $87,000
        else if (annualSalary.subtract(HMIG, new MathContext(0, RoundingMode.HALF_UP)).intValue() <= 0)
            return new HMIGTax();
            //HIG : $180,001 and over : $54,232 plus 45c for each $1 over $180,000
        else if (annualSalary.compareTo(HMIG) == 1)
            return new HIGTax();
        else
            return new UMIGTax();
    }
}
