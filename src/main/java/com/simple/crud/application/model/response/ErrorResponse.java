package com.simple.crud.application.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    private int code;

    private String cause;
}
