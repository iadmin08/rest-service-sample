package com.iadmintech.sample.module.person.validation;

import com.iadmintech.sample.module.common.domain.RequestDtoWrapper;
import com.iadmintech.sample.module.person.domain.PersonDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

@Component("beforeCreateRequestDtoWrapperValidator")
public class RequestDtoWrapperValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return RequestDtoWrapper.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RequestDtoWrapper<PersonDto> requestDtoWrapper = (RequestDtoWrapper<PersonDto>) o;
        if(requestDtoWrapper.getData() == null){
            errors.reject("Data is null");
        } else if(!(requestDtoWrapper.getData() instanceof PersonDto)){
            errors.reject("Data is malformed");
        } else {
            PersonDto personDto = requestDtoWrapper.getData();
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
