package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeingError feingError(){
        return new FeingError();
    }
}
