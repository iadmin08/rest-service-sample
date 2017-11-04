package com.iadmintech.sample.module.person.rest;

import com.iadmintech.sample.module.person.domain.dto.PersonDto;
import com.iadmintech.sample.module.person.domain.dto.PersonDtoRequestWrapper;
import com.iadmintech.sample.module.person.domain.dto.PersonDtoResponseWrapper;
import com.iadmintech.sample.module.person.exception.PersonDaoException;
import com.iadmintech.sample.module.person.service.PersonService;
import com.iadmintech.sample.module.person.validation.PersonDtoRequestWrapperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
//TODO: Make all response consistent such that all fields are always returned with appropriate value.
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    PersonDtoResponseWrapper getPerson(){
        List<PersonDto> data = personService.readPersons();
        return new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.OK, null);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public @ResponseBody PersonDtoResponseWrapper getPerson(@PathVariable("personId") Long personId){
        List<PersonDto> data = new ArrayList<>();
        PersonDto personDto = personService.readPersons(personId);
        if(personDto != null) {
            data.add(personDto);
        }
        return new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.OK,  null);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody PersonDtoResponseWrapper putPerson(@Valid @RequestBody PersonDtoRequestWrapper personDtoRequestWrapper) throws PersonDaoException {
        ArrayList<PersonDto> data = new ArrayList<>();
        data.add(personService.putPerson(personDtoRequestWrapper.getData()));
        return new PersonDtoResponseWrapper(data, LocalDateTime.now(), HttpStatus.OK, null);
    }

    @InitBinder("personDtoRequestWrapper")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PersonDtoRequestWrapperValidator());
    }
}
