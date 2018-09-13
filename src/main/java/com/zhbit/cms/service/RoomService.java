package com.zhbit.cms.service;

import com.zhbit.cms.dao.RoomDAO;
import com.zhbit.cms.infobeans.RoomInfo;

@TableService(value = "room", tableName = "RoomInfo")
public class RoomService extends AbstractService<RoomDAO, RoomInfo> {
    @Override
    void setID(RoomInfo data, int id) {
        data.setRoomID(id);
    }
}
