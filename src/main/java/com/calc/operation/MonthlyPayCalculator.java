package com.calc.operation;

import com.calc.factory.TaxFactory;
import com.calc.to.CalculatorRequest;
import com.calc.to.CalculatorResponse;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MonthlyPayCalculator implements ICalc {

    private static final BigDecimal YEARLY_TO_MONTHLY = new BigDecimal(12);

    @Override
    public CalculatorResponse compute(CalculatorRequest request) {
        CalculatorResponse response = new CalculatorResponse();
        //Calculate Tax
        ITax tax = TaxFactory.getTaxCalculator(request.getAnnualSalary());
        response.setIncomeTax(tax.calculateTax(request, YEARLY_TO_MONTHLY).round(new MathContext(0, RoundingMode.HALF_UP)).intValue());

        //Gross Income
        response.setGrossIncome(request.getAnnualSalary().divide(YEARLY_TO_MONTHLY, 0, RoundingMode.HALF_UP).intValue());

        //Net Income
        response.setNetIncome(response.getGrossIncome() - response.getIncomeTax());

        //Super
        response.setSuperContribution(new BigDecimal(response.getGrossIncome()).multiply(request.getSuperRate()).divide(new BigDecimal(100), 0, RoundingMode.HALF_UP).intValue());

        //Pay Period
        response.setPayPeriod(request.getPaymentStartDate());

        //Name
        response.setName(request.getFirstName().concat(" ").concat(request.getLastName()));

        return response;
    }
}
