package com.simple.crud.infrastructure.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer documentNumber;

    private Integer typeDocument;

    private Integer age;

    private String name;

    private String lastName;

    private String cityBorn;
}
