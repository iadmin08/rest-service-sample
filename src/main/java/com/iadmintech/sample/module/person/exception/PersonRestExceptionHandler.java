package com.iadmintech.sample.module.person.exception;

import com.iadmintech.sample.module.common.domain.ErrorDto;
import com.iadmintech.sample.module.common.domain.ResponseDtoWrapper;
import com.iadmintech.sample.module.person.domain.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class PersonRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonDaoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseDtoWrapper<List<PersonDto>> handlePersonDaoException(PersonDaoException pe) {
        ErrorDto errorDto = new ErrorDto(pe.getMessage());
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(errorDto);
        List<PersonDto> data = Collections.emptyList();

        ResponseDtoWrapper<List<PersonDto>> responseDtoWrapper = new ResponseDtoWrapper<>(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY, data, errors);
        return responseDtoWrapper;
    }

}
