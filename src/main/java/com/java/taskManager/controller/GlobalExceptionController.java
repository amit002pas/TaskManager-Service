package com.java.taskManager.controller;

import com.java.taskManager.exceptions.InvalidRequest;
import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = {InvalidRequest.class})
    ResponseEntity InvalidRequestException(Exception e) {
        return createResponseEntity(e, e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {MongoDBException.class})
    ResponseEntity MongoDBException(Exception e){
        return createResponseEntity(e, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    ResponseEntity unknownException(Exception e)
    {
        return createResponseEntity(e,e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ParseException.class})
    ResponseEntity unknownException(ParseException e)
    {
        return createResponseEntity(e,e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity createResponseEntity(Exception exception, String message, HttpStatus httpStatus) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorResponse(httpStatus);
        error.setMessage(message);
        return new ResponseEntity<ExceptionResponse>(error, httpStatus);
    }
}
