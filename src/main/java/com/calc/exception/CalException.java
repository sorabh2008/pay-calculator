package com.calc.exception;

public class CalException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String errorCode, message;

    public CalException(String errorCode, String message) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
