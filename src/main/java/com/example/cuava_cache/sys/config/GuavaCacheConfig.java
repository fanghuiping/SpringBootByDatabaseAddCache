package com.example.cuava_cache.sys.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.guava.GuavaCacheManager;


import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class GuavaCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(
                CacheBuilder.newBuilder().
                        expireAfterWrite(10*10, TimeUnit.SECONDS).
                        maximumSize(1000));
        return cacheManager;
    }
}
