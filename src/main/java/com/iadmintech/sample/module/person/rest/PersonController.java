package com.iadmintech.sample.module.person.rest;

import com.iadmintech.sample.module.common.domain.ErrorDto;
import com.iadmintech.sample.module.person.domain.dto.PersonDto;
import com.iadmintech.sample.module.person.domain.dto.PersonDtoRequestWrapper;
import com.iadmintech.sample.module.person.domain.dto.PersonDtoResponseWrapper;
import com.iadmintech.sample.module.person.exception.PersonDaoException;
import com.iadmintech.sample.module.person.service.PersonService;
import com.iadmintech.sample.module.person.validation.PersonDtoRequestWrapperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<PersonDtoResponseWrapper> getPerson(){
        List<PersonDto> data = personService.readPersons();
        PersonDtoResponseWrapper personDtoResponseWrapper = new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.OK, null);
        return new ResponseEntity<>(personDtoResponseWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public ResponseEntity<PersonDtoResponseWrapper> getPerson(@PathVariable("personId") Long personId){
        List<PersonDto> data = new ArrayList<>();
        PersonDto personDto = personService.readPersons(personId);
        if(personDto != null) {
            data.add(personDto);
        }
        PersonDtoResponseWrapper personDtoResponseWrapper = new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.OK,  null);
        return new ResponseEntity<>(personDtoResponseWrapper, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<PersonDtoResponseWrapper> putPerson(@Valid @RequestBody PersonDtoRequestWrapper personDtoRequestWrapper) throws PersonDaoException {
        ArrayList<PersonDto> data = new ArrayList<>();
        data.add(personService.putPerson(personDtoRequestWrapper.getData()));
        PersonDtoResponseWrapper personDtoResponseWrapper =  new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.CREATED, null);
        return new ResponseEntity<>(personDtoResponseWrapper, HttpStatus.CREATED);
    }

    @InitBinder("personDtoRequestWrapper")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PersonDtoRequestWrapperValidator());
    }

    @ExceptionHandler(PersonDaoException.class)
    protected ResponseEntity<PersonDtoResponseWrapper> handlePersonDaoException(PersonDaoException pe) {
        ErrorDto.GlobalError globalError = new ErrorDto.GlobalError(pe.getMessage());
        List<ErrorDto.GlobalError> globalErrorList = new ArrayList<>();
        globalErrorList.add(globalError);
        ErrorDto error = new ErrorDto(globalErrorList, Collections.emptyList());
        List<PersonDto> data = Collections.emptyList();

        PersonDtoResponseWrapper personDtoResponseWrapper = new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY, error);
        return new ResponseEntity<>(personDtoResponseWrapper, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
