package com.zhbit.cms.infobeans;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhbit.cms.infobeans.beaninterface.BasicInterface;

public class RoomInfo implements BasicInterface {
    private int RoomID=-1;
    private String RoomName;
    private int BuildingID=-1;
    private int MaxStudentCount=-1;
    private String UseFor;

    @Override
    public boolean isNotNull(){
        return RoomName != null && BuildingID != -1 && RoomID != -1 && MaxStudentCount != -1 && UseFor != null;
    }

    @JSONField(name = "room_id")
    public int getRoomID() {
        return RoomID;
    }

    @JSONField(name = "room_id")
    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    @JSONField(name = "room_name")
    public String getRoomName() {
        return RoomName;
    }

    @JSONField(name = "room_name")
    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    @JSONField(name = "building_id")
    public int getBuildingID() {
        return BuildingID;
    }

    @JSONField(name = "building_id")
    public void setBuildingID(int buildingID) {
        BuildingID = buildingID;
    }

    @JSONField(name = "max_student_count")
    public int getMaxStudentCount() {
        return MaxStudentCount;
    }

    @JSONField(name = "max_student_count")
    public void setMaxStudentCount(int maxStudentCount) {
        MaxStudentCount = maxStudentCount;
    }

    @JSONField(name = "user_for")
    public String getUseFor() {
        return UseFor;
    }

    @JSONField(name = "user_for")
    public void setUseFor(String useFor) {
        UseFor = useFor;
    }


}
