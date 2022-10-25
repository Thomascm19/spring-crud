package com.simple.crud.application.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {

    private Long id;

    private Integer documentNumber;

    private Integer typeDocument;

    private Integer age;

    private String name;

    private String lastName;

    private String cityBorn;
}
