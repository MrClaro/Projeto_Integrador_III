package com.lexlabor.exceptions;

public class ValidaçãoSenhaException extends BusinessException {

    public ValidaçãoSenhaException() {
        super("A senha não atende aos requisitos mínimos");
    }

    public ValidaçãoSenhaException(String message) {
        super(message);
    }

    public ValidaçãoSenhaException(String message, Throwable cause) {
        super(message, cause);
    }
}
