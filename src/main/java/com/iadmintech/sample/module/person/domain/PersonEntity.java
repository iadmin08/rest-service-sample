package com.iadmintech.sample.module.person.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Date birthDate;
    private String creationUserId;
    private Timestamp creationTimestamp;
    private String updateUserId;
    private Timestamp updateTimestamp;

    public PersonEntity(){
        //Intentionally empty.
    }

    public PersonEntity(Long personId, String firstName, String lastName, Integer age, Date birthDate, String creationUserId, Timestamp creationTimestamp, String updateUserId, Timestamp updateTimestamp) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.creationUserId = creationUserId;
        this.creationTimestamp = creationTimestamp;
        this.updateUserId = updateUserId;
        this.updateTimestamp = updateTimestamp;
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

    public String getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(String creationUserId) {
        this.creationUserId = creationUserId;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public int hashcode(){
        return new HashCodeBuilder(5,7).
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
                append("creationUserId", creationUserId).
                append("creationTimestamp", creationTimestamp).
                append("updateUserId", updateUserId).
                append("updateTimestamp", updateTimestamp).
                toString();
    }
}
