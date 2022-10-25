package com.simple.crud.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photo")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhotoEntity {

    private Long _id;

    private Long personId;

    private String photo;
}
