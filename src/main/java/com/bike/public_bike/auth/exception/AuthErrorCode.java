package com.bike.public_bike.auth.exception;

import com.bike.public_bike.common.exception.CommonError;

public enum AuthErrorCode implements CommonError {

    TOKEN_NOT_FOUND("A1", "해당 토큰을 찾을 수 없습니다."),
    TOKEN_NULL_OR_INVALID("A2", "요청 헤더에서 토큰을 찾을 수 없습니다."),
    TOKEN_EXPIRED("A3", "토큰이 만료돼었습니다."),
    TOKEN_INVALID_SIGNATURE("A4", "토큰 서명 오류"),
    INVALID_PASSWORD("A5", "비밀번호 오류");

    private String errorName;
    private String message;

    AuthErrorCode(String errorName, String message){
        this.errorName = errorName;
        this.message = message;
    }

    @Override
    public String errorCode(){
        return errorName;
    }

    @Override
    public String message(){
        return message;
    }

}
