package com.zhbit.cms.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("DAO")
public interface ToolsDAO {
    @Select("Select Name FROM SysColumns Where id=Object_Id(#{tableName})")
    List<String> select(@Param("tableName")String tableName);
}
