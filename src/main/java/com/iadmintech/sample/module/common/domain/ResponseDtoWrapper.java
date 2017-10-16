package com.iadmintech.sample.module.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseDtoWrapper<D> extends DtoWrapper<D> {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private List<ErrorDto> errors;

    public ResponseDtoWrapper(){
        //Intentionally empty.
    }

    public ResponseDtoWrapper(LocalDateTime timestamp, HttpStatus status, D data, List<ErrorDto> errors){
        super(data);
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }
}
