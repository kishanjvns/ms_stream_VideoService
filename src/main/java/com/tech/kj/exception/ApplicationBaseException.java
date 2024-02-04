package com.tech.kj.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationBaseException extends RuntimeException {
    private String errorCode;//ERROR-USR-001
    public ApplicationBaseException(String errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }
}
