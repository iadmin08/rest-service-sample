package com.iadmintech.sample.module.common.domain;

public abstract class DtoWrapper<D> {

    protected D data;

    public DtoWrapper(){
        //Intentionally empty.
    }

    public DtoWrapper(D data){
        this.data = data;
    }

    public D getData() {
        return data;
    }
}
