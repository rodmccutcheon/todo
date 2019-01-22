package com.rodmccutcheon.todo.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.nio.file.Paths;
import java.util.function.Function;

@Configuration
public class FrontendConfig {

    @Bean(name = "frontendResourceResolver")
    @Profile("development")
    public Function<String, Resource> devResourceResolver() {
        String temporaryPath = System.getProperty("java.io.tmpdir");
        return path -> new FileSystemResource(Paths.get(temporaryPath, path).toFile());
    }

    @Bean(name = "frontendResourceResolver")
    @Profile("!development")
    public Function<String, Resource> nonDevResourceResolver() {
        return ClassPathResource::new;
    }

}
