package com.example.atmapplication;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public InMemoryDatabase inMemoryDatabase() {
        return new InMemoryDatabase();
    }

}
