package com.zhbit.cms.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DAO")
public interface ToolsDAO {
    @Select("SELECT Name FROM SysColumns Where id=Object_Id(#{tableName})")
    List<String> select(@Param("tableName")String tableName);

    @Select("select GValue FROM GlobalInfo WHERE GName=#{name}")
    String readGlobal(@Param("name") String name);
}
