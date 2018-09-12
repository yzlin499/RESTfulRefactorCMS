package com.zhbit.cms.infobeans;

import com.alibaba.fastjson.annotation.JSONField;

public class FilterRoomParam {
    private boolean FindUsing=true;
    private int Week=128;
    private int ClassWeek= 0xfffff;
    private int ClassTime= 0x7fff;
    private int TermID=0;
    private String BuildingName=null;

    @JSONField(name = "find_using")
    public boolean isFindUsing() {
        return FindUsing;
    }

    @JSONField(name = "find_using")
    public void setFindUsing(boolean findUsing) {
        FindUsing = findUsing;
    }

    @JSONField(name = "week")
    public int getWeek() {
        return Week;
    }

    @JSONField(name = "week")
    public void setWeek(int week) {
        Week = week;
    }

    @JSONField(name = "class_week")
    public int getClassWeek() {
        return ClassWeek;
    }

    @JSONField(name = "class_week")
    public void setClassWeek(int classWeek) {
        ClassWeek = classWeek;
    }

    @JSONField(name = "class_time")
    public int getClassTime() {
        return ClassTime;
    }

    @JSONField(name = "class_time")
    public void setClassTime(int classTime) {
        ClassTime = classTime;
    }

    @JSONField(name = "term_id")
    public int getTermID() {
        return TermID;
    }

    @JSONField(name = "term_id")
    public void setTermID(int termID) {
        TermID = termID;
    }

    @JSONField(name = "building_name")
    public String getBuildingName() {
        return BuildingName;
    }

    @JSONField(name = "building_name")
    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }
}
