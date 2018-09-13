package com.zhbit.cms.service;

import com.zhbit.cms.dao.PersonDAO;
import com.zhbit.cms.infobeans.PersonInfo;

@TableService(value = "person", tableName = "PersonInfo")
public class PersonService extends AbstractService<PersonDAO, PersonInfo> {
    @Override
    void setID(PersonInfo data, int id) {
        data.setPersonID(id);
    }
}
