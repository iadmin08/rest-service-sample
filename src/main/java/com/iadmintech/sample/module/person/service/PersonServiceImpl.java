package com.iadmintech.sample.module.person.service;

import com.iadmintech.sample.module.person.dao.PersonRepository;
import com.iadmintech.sample.module.person.domain.PersonDto;
import com.iadmintech.sample.module.person.domain.PersonEntity;
import com.iadmintech.sample.module.person.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private PersonMapper personMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper){
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonDto> readPersons() {
        Iterable<PersonEntity> personEntityIterable = personRepository.findAll();
        ArrayList<PersonDto> personDtoArrayList = new ArrayList<>();
        for(PersonEntity personEntity : personEntityIterable){
            PersonDto personDto = personMapper.personEntityToPersonDto(personEntity);
            personDtoArrayList.add(personDto);
        }
        return personDtoArrayList;
    }

    @Override
    public PersonDto readPersons(Long personId) {
        PersonEntity personEntity = personRepository.findOne(personId);
        PersonDto personDto = personMapper.personEntityToPersonDto(personEntity);
        return personDto;
    }

    @Override
    public PersonDto putPerson(PersonDto personDto) {
        PersonEntity personEntity = personMapper.PersonDtoToPersonEntity(personDto);
        PersonEntity returnPersonEntity = personRepository.save(personEntity);
        PersonDto returnPersonDto = personMapper.personEntityToPersonDto(returnPersonEntity);
        return returnPersonDto;
    }
}
