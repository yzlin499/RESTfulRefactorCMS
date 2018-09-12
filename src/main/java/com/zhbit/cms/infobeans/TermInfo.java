package com.zhbit.cms.infobeans;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhbit.cms.infobeans.beaninterface.BasicInterface;

import java.util.Date;

public class TermInfo implements BasicInterface {

    private int TermID=-1;

    private String TermName;

    private Date StartDate;

    @Override
    public boolean isNotNull(){
        return TermID!=-1 && TermName!=null && StartDate!=null;
    }

    @JSONField(name = "term_id")
    public int getTermID() {
        return TermID;
    }

    @JSONField(name = "term_id")
    public void setTermID(int termID) {
        TermID = termID;
    }

    @JSONField(name = "term_name")
    public String getTermName() {
        return TermName;
    }

    @JSONField(name = "term_name")
    public void setTermName(String termName) {
        TermName = termName;
    }

    @JSONField(name = "start_date")
    public Date getStartDate() {
        return StartDate;
    }

    @JSONField(name = "start_date")
    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

}
