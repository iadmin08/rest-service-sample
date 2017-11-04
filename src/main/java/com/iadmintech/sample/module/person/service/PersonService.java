package com.iadmintech.sample.module.person.service;

import com.iadmintech.sample.module.person.domain.dto.PersonDto;
import com.iadmintech.sample.module.person.exception.PersonDaoException;

import java.util.List;

public interface PersonService {

    List<PersonDto> readPersons();
    PersonDto readPersons(Long personId);
    PersonDto putPerson(PersonDto personDto) throws PersonDaoException;
}
