package com.levkip.fibpet.api.controller;

import com.levkip.fibpet.api.exception.ValueErrorException;
import com.levkip.fibpet.api.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = ValueErrorException.class)
    public ResponseEntity<Response<String>> handleValueErrorException(ValueErrorException e) {

        return new ResponseEntity<>(new Response<>(false, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception e) {
    	
    	return new ResponseEntity<>(new Response<>(false, "Error occured. Please contact admininstrator for details."), HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
}
