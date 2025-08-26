package com.bike.public_bike.common.exception;

public class CommonException extends RuntimeException{

    private final CommonError commonError;

    public CommonException(CommonError commonError){
        super(commonError.errorCode() + ":" + commonError.message());
        this.commonError = commonError;
    }

    public CommonError getError(){
        return commonError;
    }

}
