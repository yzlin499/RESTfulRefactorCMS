package com.zhbit.cms.exceptions;

public class CMSException extends Exception {
    private int status;

    protected CMSException(int status,String message){
        super(message);
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

}
