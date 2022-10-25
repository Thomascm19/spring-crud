package com.simple.crud.infrastructure.repository;

import com.simple.crud.infrastructure.entity.PhotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPhotoRepository extends MongoRepository<PhotoEntity, Long> {
}
