package com.zhbit.cms.dao;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WCSelectUsedClassDAO {
    @Select("F_QueryRoom @TermID=#{termID}," +
            "@FindUsing=#{findUsing}," +
            "@QueryWeek=#{queryWeek}," +
            "@QueryClassWeek=#{queryClassWeek}," +
            "@BuildingName=#{buildingName}")
    List<Map> selectUserClassDAO(@Param("termID") int termID,
                                 @Param("findUsing") boolean findUsing,
                                 @Param("queryWeek") int queryWeek,
                                 @Param("queryClassWeek") int queryClassWeek,
                                 @Param("buildingName") String buildingName);


}
