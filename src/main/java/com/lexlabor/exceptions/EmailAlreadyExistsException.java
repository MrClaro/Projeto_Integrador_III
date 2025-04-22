package com.lexlabor.exceptions;

public class EmailAlreadyExistsException extends Exception{

    public EmailAlreadyExistsException() {
        super("O e-mail informado já está cadastrado");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
