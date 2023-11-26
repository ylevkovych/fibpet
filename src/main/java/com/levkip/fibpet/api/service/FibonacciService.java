package com.levkip.fibpet.api.service;

import com.levkip.fibpet.api.exception.ValueErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FibonacciService {

    private static final int MAX_INDEX = 5000;

    private FibonacciRepository fibonacciRepository;

    public FibonacciService(@Autowired FibonacciRepository fibonacciRepository) {
        this.fibonacciRepository = fibonacciRepository;
    }

    public Long countFibonacci(Integer index) {

        if (Objects.isNull(index))
            throw new ValueErrorException("Index is not set.");

        if (index > MAX_INDEX)
            throw new ValueErrorException("Index is to high. Should be less or equal "+MAX_INDEX);

        return fib(index);

    }

    private long fib(int index) {

        if (index < 1)
            return 0;

        if (index == 1)
            return 1;

        Long result = fibonacciRepository.get(index);

        if (result == null) {
            result = fib(index - 1) + fib(index - 2);
            fibonacciRepository.put(index, result);
        }

        return result;
    }

}
