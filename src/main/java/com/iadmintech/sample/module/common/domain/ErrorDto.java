package com.iadmintech.sample.module.common.domain;

public class ErrorDto {

    private String message;

    public ErrorDto() {
        //Intentionally empty.
    }

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
