package com.zhbit.cms.infobeans;

import com.alibaba.fastjson.annotation.JSONField;

public class BuildingInfo {
    private Integer BuildingID;
    private String BuildingName;
    private String BuildingUUID;

    @JSONField(name = "building_id")
    public Integer getBuildingID() {
        return BuildingID;
    }

    @JSONField(name = "building_id")
    public void setBuildingID(Integer buildingID) {
        BuildingID = buildingID;
    }

    @JSONField(name = "building_name")
    public String getBuildingName() {
        return BuildingName;
    }

    @JSONField(name = "building_name")
    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }

    @JSONField(name = "building_uuid")
    public String getBuildingUUID() {
        return BuildingUUID;
    }

    @JSONField(name = "building_uuid")
    public void setBuildingUUID(String buildingUUID) {
        this.BuildingUUID = buildingUUID;
    }

    @Override
    public String toString() {
        return "BuildingInfo{" +
                "BuildingID=" + BuildingID +
                ", BuildingName='" + BuildingName + '\'' +
                ", BuildingUUID='" + BuildingUUID + '\'' +
                '}';
    }
}
