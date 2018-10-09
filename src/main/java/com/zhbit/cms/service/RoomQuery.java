package com.zhbit.cms.service;

import com.zhbit.cms.dao.RoomDAO;
import com.zhbit.cms.exceptions.ParamLackException;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class RoomQuery implements BaseQuery {

    private RoomDAO roomDAO;

    @Autowired
    public RoomQuery(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }


    @Override
    public List<Map<String, Object>> index(Map<String, String> data, int page, int limit) throws ParamLackException {
        LogManager.getLogger(RoomQuery.class).error(roomDAO == null);

        if ((!data.containsKey("queryWeek")) || (!data.containsKey("queryClassWeek"))) {
            throw new ParamLackException("缺少参数");
        }
        return roomDAO.queryRoom(
                Integer.parseInt(data.getOrDefault("findUsing", "1")),
                Integer.parseInt(data.get("queryWeek")),
                Integer.parseInt(data.get("queryClassWeek")),
                Integer.parseInt(data.getOrDefault("termID", "0")),
                data.getOrDefault("buildingName", "%")
        );
    }
}
