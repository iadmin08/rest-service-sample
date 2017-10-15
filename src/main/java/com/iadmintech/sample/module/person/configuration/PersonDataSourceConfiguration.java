package com.iadmintech.sample.module.person.configuration;

import com.iadmintech.sample.module.person.dao.PersonRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses=PersonRepository.class)
@EntityScan(basePackages = "com.iadmintech.sample.module.person.domain")
public class PersonDataSourceConfiguration {

}
