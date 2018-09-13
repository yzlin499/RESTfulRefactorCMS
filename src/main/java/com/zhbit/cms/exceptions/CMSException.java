package com.zhbit.cms.exceptions;

import com.alibaba.fastjson.JSONObject;
import com.zhbit.cms.StatusCode;

public class CMSException extends Exception {
    private int status;

    CMSException(int status, String message) {
        super(message);
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public JSONObject toJSON() {
        return new JSONObject()
                .fluentPut(StatusCode.STATUS, getStatus())
                .fluentPut(StatusCode.ERROR, getMessage());
    }

}
