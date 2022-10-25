package com.simple.crud.domain.config;

import com.simple.crud.domain.PhotoServiceImpl;
import com.simple.crud.domain.ports.IPhotoPersistencePort;
import com.simple.crud.domain.ports.IPhotoServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhotoConfig {

    @Bean
    public IPhotoServicePort photoService(IPhotoPersistencePort photoPersistencePort){
        return new PhotoServiceImpl(photoPersistencePort);
    }
}
