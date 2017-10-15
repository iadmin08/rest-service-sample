package com.iadmintech.sample.module.person.exception;

public class PersonDaoException extends Throwable {

    public PersonDaoException(String msg) {
        super(msg);
    }

    public PersonDaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
