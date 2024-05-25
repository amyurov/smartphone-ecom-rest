package com.amyurov.smartphoneecom.response;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErrorResponse {

    private Integer status;
    private String message;
    private List<String> errors;

    public ApiErrorResponse(Integer status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorResponse(Integer status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
