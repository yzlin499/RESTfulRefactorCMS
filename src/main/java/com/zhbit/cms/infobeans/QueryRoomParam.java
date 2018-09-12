package com.zhbit.cms.infobeans;


import com.alibaba.fastjson.annotation.JSONField;

public class QueryRoomParam {
    private boolean FindUsing=true;
    private int QueryWeek= 0x80000000;
    private int QueryClassWeek=0x80000000;
    private int TermID=0;
    private String BuildingName=null;

    @JSONField(deserialize = false)
    public boolean isNotNull() {
        return QueryWeek!=0x80000000 && QueryClassWeek!=0x80000000;
    }

    @JSONField(name = "find_using")
    public boolean isFindUsing() {
        return FindUsing;
    }

    @JSONField(name = "find_using")
    public void setFindUsing(boolean findUsing) {
        FindUsing = findUsing;
    }

    @JSONField(name = "query_week")
    public int getQueryWeek() {
        return QueryWeek;
    }

    @JSONField(name = "query_week")
    public void setQueryWeek(int queryWeek) {
        QueryWeek = queryWeek;
    }

    @JSONField(name = "query_class_week")
    public int getQueryClassWeek() {
        return QueryClassWeek;
    }

    @JSONField(name = "query_class_week")
    public void setQueryClassWeek(int queryClassWeek) {
        QueryClassWeek = queryClassWeek;
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
