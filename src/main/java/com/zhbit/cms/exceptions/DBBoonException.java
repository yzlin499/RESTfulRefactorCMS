package com.zhbit.cms.exceptions;

import com.zhbit.cms.StatusCode;

public class DBBoonException extends CMSException {
    public DBBoonException(String message) {
        super(StatusCode.DB_BOON, message);
    }
}
