package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.TermInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TermDAO extends BaseDAO<TermInfo> {
    @Select("SELECT TStartDate FROM TermInfo WHERE TID IN (select GValue FROM GlobalInfo WHERE GName='CurTermID')")
    Date nowTerm();
}
