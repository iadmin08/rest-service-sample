package com.iadmintech.sample.module.person.configuration;

import com.iadmintech.sample.module.person.dao.PersonRepository;
import com.iadmintech.sample.module.person.mapper.PersonMapper;
import com.iadmintech.sample.module.person.service.PersonService;
import com.iadmintech.sample.module.person.service.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonServiceConfiguration {

    @Bean
    public PersonService personService(PersonRepository personRepository, PersonMapper personMapper){
        return new PersonServiceImpl(personRepository, personMapper);
    }
}
