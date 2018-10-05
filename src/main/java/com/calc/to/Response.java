package com.calc.to;

public class Response {

    private CalculatorResponse calculatorResponse;
    private String errCode;
    private String errMsg;

    public CalculatorResponse getCalculatorResponse() {
        return calculatorResponse;
    }

    public void setCalculatorResponse(CalculatorResponse calculatorResponse) {
        this.calculatorResponse = calculatorResponse;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
