package com.zhbit.cms.service;

import com.zhbit.cms.dao.TermDAO;
import com.zhbit.cms.infobeans.TermInfo;

@TableService(value = "term", tableName = "TermInfo")
public class TermService extends AbstractService<TermDAO, TermInfo> {

    @Override
    void setID(TermInfo data, int id) {
        data.setTermID(id);
    }
}
