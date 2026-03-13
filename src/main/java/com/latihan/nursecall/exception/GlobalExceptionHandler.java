package com.latihan.nursecall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CallNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCallNotFound(CallNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(InvalidCallStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidState(InvalidCallStateException ex){
        return new ErrorResponse(ex.getMessage());
    }

}