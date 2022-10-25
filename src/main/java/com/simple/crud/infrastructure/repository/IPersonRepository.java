package com.simple.crud.infrastructure.repository;

import com.simple.crud.infrastructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
}
