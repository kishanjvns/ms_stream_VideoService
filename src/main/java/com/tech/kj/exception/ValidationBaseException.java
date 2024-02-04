package com.tech.kj.exception;

public class ValidationBaseException extends ApplicationBaseException {
    private String errorCode;//ERROR-USR-001
    public ValidationBaseException(String errorCode, String message){
        super(errorCode, message);
    }
}
