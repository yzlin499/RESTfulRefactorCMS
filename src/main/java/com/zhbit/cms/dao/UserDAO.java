package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.PersonInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    PersonInfo loginUser(@Param("UserName") String userName,
                         @Param("PassWord") String passWord,
                         @Param("PersonGroup") int personGroup);

    void registerUser(PersonInfo personInfo);

}
