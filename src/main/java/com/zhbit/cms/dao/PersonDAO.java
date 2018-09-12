package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.BuildingInfo;
import com.zhbit.cms.infobeans.FakerMap;
import com.zhbit.cms.infobeans.PersonInfo;

import java.util.List;

public interface PersonDAO {
    List<PersonInfo> selectBPerson(FakerMap fakerMap);

    BuildingInfo selectPersonByID(int id);

    void createPerson(PersonInfo personInfo);

    void updatePerson(PersonInfo personInfo);

    int deletePerson(int id);
}
