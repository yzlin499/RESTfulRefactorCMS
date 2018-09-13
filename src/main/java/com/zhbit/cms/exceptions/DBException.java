package com.zhbit.cms.exceptions;

import com.zhbit.cms.StatusCode;
import org.apache.ibatis.exceptions.PersistenceException;

public class DBException extends CMSException {
    public DBException(PersistenceException p) {
        this(p.getCause().getMessage());
    }

    public DBException(String message){
        super(StatusCode.DB_ERROR,message);
    }
}
