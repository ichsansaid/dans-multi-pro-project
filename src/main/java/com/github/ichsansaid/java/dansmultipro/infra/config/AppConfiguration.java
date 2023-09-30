package com.github.ichsansaid.java.dansmultipro.infra.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Qualifier("jobUrl")
    public String jobUrl(){
        return "https://dev6.dansmultipro.com/api/recruitment/positions";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
