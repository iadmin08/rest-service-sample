package com.iadmintech.sample.module.person.exception;

import com.iadmintech.sample.module.common.domain.ErrorDto;
import com.iadmintech.sample.module.common.domain.ResponseDtoWrapper;
import com.iadmintech.sample.module.person.domain.dto.PersonDto;
import com.iadmintech.sample.module.person.domain.dto.PersonDtoResponseWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class PersonRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonDaoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    //TODO: Migrate this handler to the corresponding controller since it is controller specific. Or generalize this to work for any DAO Exception
    //TODO: Return ResponseEntity
    protected PersonDtoResponseWrapper handlePersonDaoException(PersonDaoException pe) {
        ErrorDto.GlobalError globalError = new ErrorDto.GlobalError(pe.getMessage());
        List<ErrorDto.GlobalError> globalErrorList = new ArrayList<>();
        globalErrorList.add(globalError);
        ErrorDto error = new ErrorDto(globalErrorList, Collections.emptyList());
        List<PersonDto> data = Collections.emptyList();

        PersonDtoResponseWrapper personDtoResponseWrapper = new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY, error);
        return personDtoResponseWrapper;
    }

    @Override
    //TODO: make more generic and move to common
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
        List<ErrorDto.GlobalError> globalErrorList = new ArrayList<>();
        List<ErrorDto.FieldError> fieldErrorList = new ArrayList<>();
        for(ObjectError objectError : objectErrorList){
            if(objectError instanceof FieldError){
                String field = ((FieldError) objectError).getField();
                String code = objectError.getCode();
                Object rejectedValue = ((FieldError) objectError).getRejectedValue();
                fieldErrorList.add(new ErrorDto.FieldError(field, code, rejectedValue));
            } else {
                globalErrorList.add(new ErrorDto.GlobalError(objectError.getDefaultMessage()));
            }
        }
        ErrorDto error = new ErrorDto(globalErrorList, fieldErrorList);
        List<PersonDto> data = Collections.emptyList();

        PersonDtoResponseWrapper personDtoResponseWrapper = new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(personDtoResponseWrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    //TODO: prepare a better json response message for this scenario
    //TODO: make more generic and move to common
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getRootCause().getMessage();
        List<ErrorDto.GlobalError> globalErrorList = new ArrayList<>();
        globalErrorList.add(new ErrorDto.GlobalError(message));
        List<ErrorDto.FieldError> fieldErrorList = Collections.emptyList();
        ErrorDto error = new ErrorDto(globalErrorList, fieldErrorList);
        List<PersonDto> data = Collections.emptyList();

        PersonDtoResponseWrapper personDtoResponseWrapper = new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(personDtoResponseWrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}