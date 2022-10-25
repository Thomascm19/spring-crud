package com.simple.crud.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonModel {
    private Long id;

    private Integer documentNumber;

    private Integer typeDocument;

    private Integer age;

    private String name;

    private String lastName;

    private String cityBorn;
}
