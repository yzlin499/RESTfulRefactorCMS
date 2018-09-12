package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.CourseInfo;
import com.zhbit.cms.infobeans.FakerMap;
import com.zhbit.cms.infobeans.TermInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TermDAO {

    int createTerm(TermInfo termInfo);

    void updateTerm(TermInfo termInfo);

    int deleteTerm(int id);

    TermInfo selectTermByID(int id);

    List<CourseInfo> selectTerm(FakerMap fakerMap);

}
