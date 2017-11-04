package com.iadmintech.sample.module.person.domain.dto;

public class PersonDtoRequestWrapper {

    private PersonDto data;

    public PersonDtoRequestWrapper(){

    }

    public PersonDtoRequestWrapper(PersonDto data){
        this.data = data;
    }

    public PersonDto getData() {
        return data;
    }
}
