package com.iadmintech.sample.module.common.domain;

public class ResponseDtoWrapper<D> extends DtoWrapper<D> {

    private ErrorDto error;

    public ResponseDtoWrapper(){
        //Intentionally empty.
    }

    public ResponseDtoWrapper(D data, ErrorDto error){
        super(data);
        this.error = error;
    }

    public ErrorDto getError() {
        return error;
    }

    public void setError(ErrorDto error) {
        this.error = error;
    }
}
