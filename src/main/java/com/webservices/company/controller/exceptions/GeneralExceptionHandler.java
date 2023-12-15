package com.webservices.company.controller.exceptions;

import com.webservices.company.exceptions.IllegalArgException;
import com.webservices.company.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler({IllegalArgException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessage handleArgumentException(IllegalArgException exception) {
        return new ErrorMessage(exception.getMessage());
    }

}
