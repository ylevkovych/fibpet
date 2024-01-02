package com.levkip.fibpet.api.service;

import com.levkip.fibpet.api.exception.ValueErrorException;
import com.levkip.fibpet.api.repository.FibonacciCacheRepository;
import com.levkip.fibpet.api.repository.FibonacciRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class FibonacciService {

    private static final int MAX_INDEX = 5000;

    private FibonacciCacheRepository fibonacciCacheRepository;
    private FibonacciRepository fibonacciRepository;
    
    public FibonacciService(
    		@Autowired FibonacciRepository fibonacciRepository,
    		@Autowired FibonacciCacheRepository fibonacciCacheRepository) 
    {
    	this.fibonacciRepository = fibonacciRepository;
        this.fibonacciCacheRepository = fibonacciCacheRepository;
    }

    public Long countFibonacci(Integer index) {

    	Map<Integer, Long> exist = fibonacciRepository.getOne(index);

    	if (exist.get(index) != null) {
    		return exist.get(index);
    	}
    	
        return countAndSave(index);

    }

	private Long countAndSave(Integer index)
	{
		validateIndex(index);
        
        Long value = fib(index);
        
        fibonacciRepository.save(index, value);
        
        return value;
	}

	private void validateIndex(Integer index)
	{
		if (Objects.isNull(index))
            throw new ValueErrorException("Index is not set.");

        if (index > MAX_INDEX)
            throw new ValueErrorException("Index is to high. Should be less or equal "+MAX_INDEX);
	}

    private long fib(int index) {

        if (index < 1)
            return 0;

        if (index == 1)
            return 1;

        Long result = fibonacciCacheRepository.get(index);

        if (result == null) {
            result = fib(index - 1) + fib(index - 2);
            fibonacciCacheRepository.put(index, result);
        }

        return result;
    }

}
