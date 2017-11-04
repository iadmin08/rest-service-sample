package com.iadmintech.sample.module.person.validation;

import com.iadmintech.sample.module.person.domain.dto.PersonDto;
import com.iadmintech.sample.module.person.domain.dto.PersonDtoRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

@Component("beforeCreatePersonDtoRequestWrapperValidator")
public class PersonDtoRequestWrapperValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return PersonDtoRequestWrapper.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDtoRequestWrapper personDtoRequestWrapper = (PersonDtoRequestWrapper) o;
        if(personDtoRequestWrapper.getData() == null){
            errors.reject("Data is null", "The data attribute cannot be null.");
        } else if(!(personDtoRequestWrapper.getData() instanceof PersonDto)){
            errors.reject("Data is malformed", "The data attribute is malformed.");
        } else {
            PersonDto personDto = personDtoRequestWrapper.getData();
            if (StringUtils.isEmpty(personDto.getFirstName())){
                errors.rejectValue("data.firstName", "Cannot be null, empty string, or blank");
            }
            if (StringUtils.isEmpty(personDto.getLastName())){
                errors.rejectValue("data.lastName", "Cannot be null, empty string, or blank");
            }
            if (personDto.getAge()== null || personDto.getAge() < 0){
                errors.rejectValue("data.age", "Cannot be null, less than 0, or blank");
            }
            if (!(personDto.getBirthDate() instanceof Date)){
                errors.rejectValue("data.birthDate", "Cannot be null, empty string, or blank");
            }
        }
    }
}
