package com.zhbit.cms.exceptions;

import com.zhbit.cms.StatusCode;

public class ForbiddenException extends CMSException {
    public ForbiddenException(String message){
        super(StatusCode.FORBIDDEN,message);
    }
}
