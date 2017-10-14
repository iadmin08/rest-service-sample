package com.iadmintech.sample.module.person.mapper;

import com.iadmintech.sample.module.person.domain.PersonDto;
import com.iadmintech.sample.module.person.domain.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mappings({
        @Mapping(source = "personId", target = "personId"),
        @Mapping(source = "firstName", target = "firstName"),
        @Mapping(source = "lastName", target = "lastName"),
        @Mapping(source = "age", target = "age"),
        @Mapping(source = "birthDate", target = "birthDate")
    })
    PersonDto personEntityToPersonDto(PersonEntity personEntity);

    @Mappings({
        @Mapping(source = "firstName", target = "firstName"),
        @Mapping(source = "lastName", target = "lastName"),
        @Mapping(source = "age", target = "age"),
        @Mapping(source = "birthDate", target = "birthDate")
    })
    PersonEntity PersonDtoToPersonEntity(PersonDto personDto);
}
