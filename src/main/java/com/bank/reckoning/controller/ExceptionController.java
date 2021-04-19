package com.bank.reckoning.controller;

import com.bank.reckoning.exception.RepeatPasswordNotSameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exceptions handler.
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Method for creating new user account.
     *
     * @param repeatPasswordNotSameException for work with exception
     * @return response object
     */
    @ExceptionHandler(value = RepeatPasswordNotSameException.class)
    public ResponseEntity<Object> exception(RepeatPasswordNotSameException repeatPasswordNotSameException) {
        return new ResponseEntity<>(repeatPasswordNotSameException.getCause(), HttpStatus.BAD_REQUEST);
    }
}
