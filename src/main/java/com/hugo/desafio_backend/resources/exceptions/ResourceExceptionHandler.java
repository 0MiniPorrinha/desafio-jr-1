package com.hugo.desafio_backend.resources.exceptions;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hugo.desafio_backend.services.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
