package com.zhbit.cms.service;

import com.alibaba.fastjson.JSON;
import com.zhbit.cms.dao.RoomDAO;
import com.zhbit.cms.dao.ToolsDAO;
import com.zhbit.cms.infobeans.FakerMap;
import com.zhbit.cms.infobeans.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("room")
public class RoomService implements BaseService {

    private RoomDAO roomDAO;
    private List<String> tableCourse;

    @Autowired
    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Autowired
    public void setTableCourse(ToolsDAO toolsDAO) {
        tableCourse=toolsDAO.select("RoomInfo");
    }


    @Override
    public Object index(Map<String, Object> formData) {
        formData.keySet().retainAll(tableCourse);
        return roomDAO.selectRoom(new FakerMap(formData));
    }

    @Override
    public Object save(JSON jsonData) {
        return roomDAO.createRoom(jsonData.toJavaObject(RoomInfo.class));
    }

    @Override
    public Object update(JSON jsonData, String id) {
        RoomInfo roomInfo=jsonData.toJavaObject(RoomInfo.class);
        roomInfo.setRoomID(Integer.parseInt(id));
        roomDAO.updateRoom(roomInfo);
        return id;
    }

    @Override
    public Object delete(JSON jsonData, String id) {
        return roomDAO.deleteRoom(Integer.parseInt(id));
    }

    @Override
    public Object read(Map<String, Object> formData, String id) {
        return roomDAO.selectRoomByID(Integer.parseInt(id));
    }
}
