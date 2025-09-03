package com.bike.public_bike.common.exception;

import com.bike.public_bike.auth.exception.AuthErrorCode;

public class AuthException extends RuntimeException{

    private final AuthErrorCode authErrorCode;

    public AuthException(AuthErrorCode authErrorCode){
        super(authErrorCode.errorCode() + ":" + authErrorCode.message());
        this.authErrorCode = authErrorCode;
    }

    public AuthErrorCode getAuthErrorCode(){
        return authErrorCode;
    }

}
