package com.iadmintech.sample.module.person.exception;

import com.iadmintech.sample.module.common.domain.ErrorDto;
import com.iadmintech.sample.module.common.domain.ResponseDtoWrapper;
import com.iadmintech.sample.module.person.domain.PersonDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    protected ResponseDtoWrapper<List<PersonDto>> handlePersonDaoException(PersonDaoException pe) {
        ErrorDto.GlobalError globalError = new ErrorDto.GlobalError(pe.getMessage());
        List<ErrorDto.GlobalError> globalErrorList = new ArrayList<>();
        globalErrorList.add(globalError);
        ErrorDto error = new ErrorDto(globalErrorList, Collections.emptyList());
        List<PersonDto> data = Collections.emptyList();

        ResponseDtoWrapper<List<PersonDto>> responseDtoWrapper = new ResponseDtoWrapper<>(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY, data, error);
        return responseDtoWrapper;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @Override
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

        ResponseDtoWrapper<List<PersonDto>> responseDtoWrapper = new ResponseDtoWrapper<>(LocalDateTime.now(), HttpStatus.BAD_REQUEST, data, error);
        return new ResponseEntity<>(responseDtoWrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}