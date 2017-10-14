package com.iadmintech.sample.module.common.domain;

public class ResponseDtoWrapper<D> extends DtoWrapper<D> {

    public ResponseDtoWrapper(){
        //Intentionally empty.
    }

    public ResponseDtoWrapper(D data){
        super(data);
    }
}
