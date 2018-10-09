package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.RoomInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomDAO extends BaseDAO<RoomInfo> {

    @Select("EXEC F_QueryRoom " +
            "@FindUsing=#{findUsing}," +
            "@QueryWeek=#{queryWeek}," +
            "@QueryClassWeek=#{queryClassWeek}," +
            "@TermID=#{termID}," +
            "@BuildingName=#{buildingName}")
    List<Map<String, Object>> queryRoom(@Param("findUsing") int findUsing,
                                        @Param("queryWeek") int queryWeek,
                                        @Param("queryClassWeek") int queryClassWeek,
                                        @Param("termID") int termID,
                                        @Param("buildingName") String buildingName);
}
