package com.bike.public_bike.domain.member.exception;

import com.bike.public_bike.common.exception.CommonError;

public enum MemberException implements CommonError {
    NOT_FOUND_BY_EMAIL("MERROR1", "이메일에 해당하는 유저가 없습니다."),
    NOT_FOUND_BY_NICKNAME("MERROR2", "닉네임에 해당하는 유저가 없습니다."),
    NOT_FOUND_BY_ID("MERROR3", "ID에 해당하는 유저가 없습니다."),
    DUPLICATE_NICKNAME("MERROR4", "중복되는 닉네임이 존재합니다"),
    DUPLICATE_EMAIL("MERROR5", "중복되는 이메일 주소가 존재합니다.");

    private String errorCode;
    private String message;

    MemberException(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String errorCode(){
        return errorCode;
    }

    @Override
    public String message(){
        return message;
    }

}
