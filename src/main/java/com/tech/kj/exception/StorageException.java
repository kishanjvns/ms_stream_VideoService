package com.tech.kj.exception;

public class StorageException extends RuntimeException {

    public StorageException(Exception ex) {
        super(ex);
    }
}
