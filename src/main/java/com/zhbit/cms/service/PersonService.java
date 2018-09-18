package com.zhbit.cms.service;

import com.alibaba.fastjson.JSON;
import com.zhbit.cms.dao.PersonDAO;
import com.zhbit.cms.infobeans.PersonInfo;

@TableService(value = "person", tableName = "PersonInfo")
public class PersonService extends AbstractService<PersonDAO, PersonInfo> {

    @Override
    public Object save(JSON jsonData) {
        PersonInfo personInfo = jsonData.toJavaObject(PersonInfo.class);
        dao.create(personInfo);
        return personInfo.getPersonID();
    }

    @Override
    void setID(PersonInfo data, int id) {
        data.setPersonID(id);
    }
}
