package com.simple.crud.application.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Photo {

    private Long _id;

    private Long personId;

    private String photo;
}
