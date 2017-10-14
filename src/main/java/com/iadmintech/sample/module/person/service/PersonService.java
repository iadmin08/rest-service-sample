package com.iadmintech.sample.module.person.service;

import com.iadmintech.sample.module.person.domain.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> readPersons();
    PersonDto readPersons(Long personId);
    PersonDto putPerson(PersonDto personDto);
}
