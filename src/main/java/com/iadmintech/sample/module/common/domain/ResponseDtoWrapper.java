package com.iadmintech.sample.module.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseDtoWrapper<D> extends DtoWrapper<D> {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDto error;

    public ResponseDtoWrapper(){
        //Intentionally empty.
    }

    public ResponseDtoWrapper(LocalDateTime timestamp, HttpStatus status, D data, ErrorDto error){
        super(data);
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ErrorDto getError() {
        return error;
    }
}
