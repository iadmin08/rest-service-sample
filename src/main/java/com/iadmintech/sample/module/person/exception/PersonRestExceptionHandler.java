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

@RestControllerAdvice
public class PersonRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonDaoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseDtoWrapper<PersonDto> handlePersonDaoException(PersonDaoException pe) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(pe.getMessage());
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseDtoWrapper<PersonDto> responseDtoWrapper = new ResponseDtoWrapper<>();
        responseDtoWrapper.setError(errorDto);
        return responseDtoWrapper;
    }

}
