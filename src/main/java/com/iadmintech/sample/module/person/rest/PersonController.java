package com.iadmintech.sample.module.person.rest;

import com.iadmintech.sample.module.common.domain.ErrorDto;
import com.iadmintech.sample.module.common.domain.RequestDtoWrapper;
import com.iadmintech.sample.module.common.domain.ResponseDtoWrapper;
import com.iadmintech.sample.module.person.domain.PersonDto;
import com.iadmintech.sample.module.person.exception.PersonDaoException;
import com.iadmintech.sample.module.person.service.PersonService;
import com.iadmintech.sample.module.person.validation.RequestDtoWrapperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public @ResponseBody ResponseDtoWrapper<List<PersonDto>> getPerson(){
        List<PersonDto> data = personService.readPersons();
        return new ResponseDtoWrapper<>(LocalDateTime.now(), HttpStatus.OK, data, null);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public @ResponseBody ResponseDtoWrapper<List<PersonDto>> getPerson(@PathVariable("personId") Long personId){
        List<PersonDto> data = new ArrayList<>();
        data.add(personService.readPersons(personId));
        return new ResponseDtoWrapper<>(LocalDateTime.now(), HttpStatus.OK, data,  null);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody ResponseDtoWrapper<List<PersonDto>> putPerson(@Valid @RequestBody RequestDtoWrapper<PersonDto> requestDtoWrapper) throws PersonDaoException {
        ArrayList<PersonDto> data = new ArrayList<>();
        data.add(personService.putPerson(requestDtoWrapper.getData()));
        return new ResponseDtoWrapper<>(LocalDateTime.now(), HttpStatus.OK, data, null);
    }

    @InitBinder("requestDtoWrapper")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new RequestDtoWrapperValidator());
    }
}
