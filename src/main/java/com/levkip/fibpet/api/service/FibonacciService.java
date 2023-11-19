package com.levkip.fibpet.api.service;

import com.levkip.fibpet.api.exception.ValueErrorException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FibonacciService {

    private static final int MAX_INDEX = 5000;

    public Long countFibonacci(Integer index) {

        if (Objects.isNull(index))
            throw new ValueErrorException("Index is not set.");

        if (index > MAX_INDEX)
            throw new ValueErrorException("Index is to high. Should be less or equal "+MAX_INDEX);

        long result = fib(index);

        return result;

    }

    private long fib(int index) {
        return fib(index, new HashMap<>());
    }

    private long fib(int index, Map<Integer, Long> hashedIndex) {

        if (index < 1)
            return 0;

        if (index == 1)
            return 1;

        if (hashedIndex.containsKey(index)) {
            return hashedIndex.get(index);
        }

        Long result = fib(index - 1, hashedIndex) + fib(index - 2, hashedIndex);

        hashedIndex.put(index, result);

        return result;
    }

}
