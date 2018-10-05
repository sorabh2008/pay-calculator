package com.calc.to;

public class CalculatorResponse {
    String name, payPeriod;
    Integer grossIncome, incomeTax, netIncome, superContribution;

    public Integer getSuperContribution() {
        return superContribution;
    }

    public void setSuperContribution(Integer superContribution) {
        this.superContribution = superContribution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public Integer getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(Integer grossIncome) {
        this.grossIncome = grossIncome;
    }

    public Integer getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Integer incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Integer getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(Integer netIncome) {
        this.netIncome = netIncome;
    }
}
