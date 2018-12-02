package com.sit.jbc.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorMessage;
    private String errorCode;
    private String errorDetails;

    public CustomException() {
        super();
    }

    public CustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public CustomException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public CustomException(String errorCode, String errorMessage, String errorDetails) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }

    public CustomException(String errorCode, String errorMessage, String errorDetails, Exception e) {
        super(e);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }

    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorDetails='" + errorDetails + '\'' +
                '}';
    }
}