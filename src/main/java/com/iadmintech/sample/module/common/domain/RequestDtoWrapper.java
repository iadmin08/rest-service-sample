package com.iadmintech.sample.module.common.domain;

public class RequestDtoWrapper<D> extends DtoWrapper<D> {

    public RequestDtoWrapper(){
        //Intentionally empty.
    }

    public RequestDtoWrapper(D data){
        super(data);
    }
}
