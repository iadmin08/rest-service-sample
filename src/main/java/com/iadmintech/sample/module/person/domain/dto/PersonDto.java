package com.iadmintech.sample.module.person.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class PersonDto {

    private Long personId;
    private String firstName;
    private String lastName;
    private Integer age;
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "EST")
    private Date birthDate;

    public PersonDto(){
        //Intentionally empty.
    }

    public PersonDto(Long personId, String firstName, String lastName, Integer age, Date birthDate) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int hashcode(){
        return new HashCodeBuilder(1,3).
                append(personId).
                append(firstName).
                append(lastName).
                append(age).
                append(birthDate).
                toHashCode();
    }

    public boolean equals(Object obj){
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        PersonDto pdt = (PersonDto) obj;
        return new EqualsBuilder().
                append(firstName, pdt.getFirstName()).
                append(lastName, pdt.getLastName()).
                append(age, pdt.getAge()).
                append(birthDate, pdt.getBirthDate()).
                isEquals();
    }

    public String toString(){
        return new ToStringBuilder(this).
                append("personId", personId).
                append("firstName", firstName).
                append("lastName", lastName).
                append("age", age).
                append("birthDate", birthDate).
                toString();
    }
}
