package com.lexlabor.exceptions;

public class BusinessException extends Exception {

    public BusinessException() {
        super("Ocorreu um erro na operação");
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
