package com.iadmintech.sample.module.person.domain.dto;

import com.iadmintech.sample.module.common.domain.ErrorDto;
import com.iadmintech.sample.module.common.domain.ResponseDtoWrapper;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PersonDtoResponseWrapper extends ResponseDtoWrapper {

    private List<PersonDto> data;

    public PersonDtoResponseWrapper(){

    }

    public PersonDtoResponseWrapper(List<PersonDto> data, LocalDateTime timestamp, HttpStatus status, ErrorDto error){
        super(timestamp, status, error);
        this.data = data;
    }

    public List<PersonDto> getData() {
        return data;
    }
}
