package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.FakerMap;
import com.zhbit.cms.infobeans.RoomInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDAO {

    List<RoomInfo> selectRoom(FakerMap fakerMap);

    RoomInfo selectRoomByID(int id);

    int createRoom(RoomInfo roomInfo);

    void updateRoom(RoomInfo roomInfo);

    int deleteRoom(int id);

}
