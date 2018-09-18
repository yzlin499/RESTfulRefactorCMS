package com.zhbit.cms.dao;

import org.apache.ibatis.annotations.Param;

public interface CheckInDAO {
    int generateVerifyCode(@Param("name") String name);

    String checkIn(@Param("verifyCode") String verifyCode, @Param("buildingFlag") String buildingFlag);
}
