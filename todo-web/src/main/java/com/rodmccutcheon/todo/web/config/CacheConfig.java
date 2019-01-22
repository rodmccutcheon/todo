package com.rodmccutcheon.todo.web.config;

import com.rodmccutcheon.todo.web.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Configuration
public class CacheConfig {

    @Autowired(required = false)
    private RedisCacheManager redisCacheManager;

    @PostConstruct
    public void postConstruct() {
        if (redisCacheManager != null) {
            redisCacheManager.setUsePrefix(true);
        }
    }

    @Bean
    @Profile("development")
    public CacheManager developmentCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        cacheManager.setCaches(Collections.singletonList(
                new ConcurrentMapCache(TodoService.CACHE_KEY)
        ));

        return cacheManager;
    }

}
