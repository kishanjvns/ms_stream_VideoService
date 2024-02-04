package com.tech.kj.controller.exception.handler;

import com.tech.kj.exception.ApplicationConstant;
import com.tech.kj.exception.StorageException;
import com.tech.kj.exception.ValidationBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ValidationBaseException.class})
    public ResponseEntity<Map<String,String>> handleRecordAlreadyExistException(ValidationBaseException exception){
        Map<String,String> errorMap= Map.of("errorCode",exception.getErrorCode(), "errorMessage",exception.getMessage());
        return  ResponseEntity.badRequest().body(errorMap);
    }
    @ExceptionHandler({StorageException.class})
    public ResponseEntity<Map<String,String>> handleRecordAlreadyExistException(StorageException exception){
        Map<String,String> errorMap= Map.of("errorCode", exception.getErrorCode(), "errorMessage",exception.getMessage());
        return  ResponseEntity.badRequest().body(errorMap);
    }
}
