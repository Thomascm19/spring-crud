package com.simple.crud.domain.config;

import com.simple.crud.domain.PersonServiceImpl;
import com.simple.crud.domain.ports.IPersonPersistencePort;
import com.simple.crud.domain.ports.IPersonServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public IPersonServicePort personService(IPersonPersistencePort personPersistencePort){
        return new PersonServiceImpl(personPersistencePort);
    }
}
