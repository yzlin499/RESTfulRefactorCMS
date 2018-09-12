package com.zhbit.cms.exceptions;

import com.zhbit.cms.StatusCode;

public class NoJSONException extends CMSException {
    public NoJSONException(String message){
        super(StatusCode.NO_JSON,message);
    }
}
