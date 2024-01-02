package com.levkip.fibpet.api.repository;

import com.levkip.fibpet.ConfigRedis;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;


//@ExtendWith(MockitoExtension.class)
class FibonacciCacheRepositoryTest {

    public static GenericContainer redis =
            new GenericContainer(
                    DockerImageName.parse("redis:7.0.9-alpine")).withExposedPorts(6379);

    private static FibonacciCacheRepository fibonacciRepository;

    private static RedisTemplate<String, Object> redisTemplate;

//    @BeforeAll
    public static void setup() {
//        redis.start();
//
//        JedisConnectionFactory cf = new JedisConnectionFactory();
//
//        if (cf.getStandaloneConfiguration() == null)
//            throw new IllegalArgumentException("Wrong redis configuration.");
//
//        cf.getStandaloneConfiguration().setHostName(redis.getHost());
//        cf.getStandaloneConfiguration().setPort(redis.getMappedPort(6379));
//        cf.afterPropertiesSet();
//
//        redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(cf);
//        redisTemplate.afterPropertiesSet();
//        fibonacciRepository = new FibonacciCacheRepository(redisTemplate);
    }

//    @AfterAll
    public static void tearDown() {
        redis.close();
    }

//    @Test
    void get() {
        fibonacciRepository.put(1, 100L);
        assertEquals(100L, fibonacciRepository.get(1));
    }

//    @Test
    void put() {
        fibonacciRepository.put(3, 300L);
        assertEquals(300L, fibonacciRepository.get(3));
    }
}