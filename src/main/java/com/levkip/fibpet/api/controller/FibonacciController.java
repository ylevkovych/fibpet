package com.levkip.fibpet.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.levkip.fibpet.api.model.Fib;
import com.levkip.fibpet.api.model.Response;
import com.levkip.fibpet.api.service.FibonacciService;

@RestController
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

    @GetMapping("api/v1.0/fibonacci/{index}")
    public ResponseEntity<Response<Long>> countFibonacci(@PathVariable Integer index) {

        return new ResponseEntity<>(
                new Response<Long>(true, fibonacciService.countFibonacci(index)),
                HttpStatus.OK);

    }
    
    @GetMapping("api/v1.0/fibonacci")
    public ResponseEntity<Response<List<Fib>>> getSavedFibonacciValues() {
    	
    	return new ResponseEntity<>(
    			new Response<List<Fib>>(true, fibonacciService.getSavedFibonacciValues()),
    			HttpStatus.OK);
    	
    }


}
