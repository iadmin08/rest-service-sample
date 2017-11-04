package com.iadmintech.sample.module.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ErrorDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GlobalError> globalErrors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> fieldErrors;

    public ErrorDto() {
        //Intentionally empty.
    }

    public ErrorDto(List<GlobalError> globalErrors, List<FieldError> fieldErrors) {
        this.globalErrors = globalErrors;
        this.fieldErrors = fieldErrors;
    }

    public List<GlobalError> getGlobalErrors() {
        return globalErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public static class FieldError {

        private String field;
        private String code;
        private Object rejectedValue;

        public FieldError() {
        }

        public FieldError(String field, String code, Object rejectedValue) {
            this.field = field;
            this.code = code;
            this.rejectedValue = rejectedValue;
        }

        public String getField() {
            return field;
        }

        public String getCode() {
            return code;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }
    }

    public static class GlobalError{

        private String message;

        public GlobalError() {

        }

        public GlobalError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }
}
