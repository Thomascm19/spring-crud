package com.simple.crud.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhotoModel {

    private Long _id;

    private Long personId;

    private String photo;
}
