package com.calc.impl;

import com.calc.constants.ErrorConstants;
import com.calc.exception.CalException;
import com.calc.manager.CalculatorManager;
import com.calc.to.CalculatorRequest;
import com.calc.to.CalculatorResponse;
import com.calc.to.Response;

/**
 * @description: Implementation for Pay Calculator service
 */
public class PayCalculatorImpl implements Calculator {
    private CalculatorManager manager;

    public PayCalculatorImpl() {
        manager = new CalculatorManager();
    }

    /**
     * @param request: Calculator Request
     * @description: Method to calculate pay.
     * @return: Application response
     */
    @Override
    public Response calculate(CalculatorRequest request) {
        CalculatorResponse calcResponse = null;
        Response response = new Response();
        try {
            calcResponse = this.manager.calculate(request);
            response.setCalculatorResponse(calcResponse);
        } catch (CalException cex) {
            response.setErrCode(cex.getErrorCode());
            response.setErrMsg(cex.getMessage());
        } catch (Exception ex) {
            response.setErrCode(ErrorConstants.CODE_APP_ERROR);
            response.setErrMsg(ErrorConstants.MSG_APP_ERROR);
        }
        return response;
    }
}
