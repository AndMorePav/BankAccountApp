package com.bank.reckoning.controller;

import com.bank.reckoning.exception.RepeatPasswordNotSameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RepeatPasswordNotSameException.class)
    public ResponseEntity<Object> exception(RepeatPasswordNotSameException exception) {
        return new ResponseEntity<>("RepeatPassword not same", HttpStatus.BAD_REQUEST);
    }
}
