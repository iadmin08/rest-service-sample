package com.iadmintech.sample.module.person.rest;

import com.iadmintech.sample.module.common.domain.RequestDtoWrapper;
import com.iadmintech.sample.module.common.domain.ResponseDtoWrapper;
import com.iadmintech.sample.module.person.domain.PersonDto;
import com.iadmintech.sample.module.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody ResponseDtoWrapper<List<PersonDto>> getPerson(){
        List<PersonDto> personDtoList = personService.readPersons();
        return new ResponseDtoWrapper<>(personDtoList);
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public @ResponseBody ResponseDtoWrapper<PersonDto> getPerson(@PathVariable("personId") Long personId){
        PersonDto personDto = personService.readPersons(personId);
        return new ResponseDtoWrapper<>(personDto);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody ResponseDtoWrapper<PersonDto> putPerson(@RequestBody RequestDtoWrapper<PersonDto> requestDtoWrapper){
        PersonDto returnPersonDto = personService.putPerson(requestDtoWrapper.getData());
        return new ResponseDtoWrapper<>(returnPersonDto);
    }
}
