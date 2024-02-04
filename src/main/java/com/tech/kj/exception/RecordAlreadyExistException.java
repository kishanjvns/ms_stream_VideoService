package com.tech.kj.exception;


public class RecordAlreadyExistException extends ValidationBaseException {
    private String errorCode;//ERROR-USR-001

    public RecordAlreadyExistException(String errorCode, String message) {
        super(errorCode, message);
    }
}
