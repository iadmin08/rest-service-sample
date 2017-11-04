package com.iadmintech.sample.module.person.service;

import com.iadmintech.sample.module.person.dao.PersonRepository;
import com.iadmintech.sample.module.person.domain.dto.PersonDto;
import com.iadmintech.sample.module.person.domain.entity.PersonEntity;
import com.iadmintech.sample.module.person.exception.PersonDaoException;
import com.iadmintech.sample.module.person.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private PersonMapper personMapper;

    private final static String DAO_SAVE_EXCEPTION_MESSAGE = "An error occurred while attempting to persist your data.";

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
    public PersonDto putPerson(PersonDto personDto) throws PersonDaoException{
        try {
            PersonEntity personEntity = personMapper.PersonDtoToPersonEntity(personDto);
            personEntity.setCreationUserId("dummy");//TODO: Remove this and pull username from requestWrapper.
            PersonEntity returnPersonEntity = personRepository.save(personEntity);
            PersonDto returnPersonDto = personMapper.personEntityToPersonDto(returnPersonEntity);
            return returnPersonDto;
        } catch(DataAccessException e){
            throw new PersonDaoException(DAO_SAVE_EXCEPTION_MESSAGE, e);
        }
    }
}