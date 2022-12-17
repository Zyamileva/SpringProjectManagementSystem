package com.goit.homeworkspring.controller;

import com.goit.homeworkspring.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Error {

    @ExceptionHandler({UsernameNotFoundException.class, InternalAuthenticationServiceException.class})
    public ResponseEntity<Response> handleException(InternalAuthenticationServiceException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}