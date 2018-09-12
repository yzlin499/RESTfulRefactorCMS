package com.zhbit.cms.exceptions;

import com.zhbit.cms.StatusCode;

public class DBException extends CMSException {
    public DBException(String message){
        super(StatusCode.DB_ERROR,message);
    }
}
