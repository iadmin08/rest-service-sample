package com.iadmintech.sample.module.person.dao;

import com.iadmintech.sample.module.person.domain.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long>{

    PersonEntity findByFirstNameAndLastName(String firstName, String lastName);

}
