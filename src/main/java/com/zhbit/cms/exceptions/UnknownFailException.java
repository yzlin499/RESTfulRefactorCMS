package com.zhbit.cms.exceptions;

import com.zhbit.cms.StatusCode;

public class UnknownFailException extends CMSException {
    public UnknownFailException(String message){
        super(StatusCode.UNKNOWN_FAIL,message);
    }
}
