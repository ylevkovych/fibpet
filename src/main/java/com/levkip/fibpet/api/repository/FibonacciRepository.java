package com.levkip.fibpet.api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FibonacciRepository {

    private final Map<Integer, Long> hashedIndex = new HashMap<>();


    public Long get(Integer index) {
        return hashedIndex.get(index);
    }

    public void put(int index, Long value) {

        hashedIndex.put(index, value);

    }
}
