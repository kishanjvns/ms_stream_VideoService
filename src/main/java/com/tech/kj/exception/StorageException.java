package com.tech.kj.exception;

public class StorageException extends ApplicationBaseException {


    public StorageException(String errorCode, String message) {
        super(errorCode, message);
    }
}
