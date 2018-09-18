package com.zhbit.cms.service;

import com.alibaba.fastjson.JSON;
import com.zhbit.cms.dao.BuildingDAO;
import com.zhbit.cms.infobeans.BuildingInfo;

@TableService(value = "building", tableName = "BuildingInfo")
public class BuildingService extends AbstractService<BuildingDAO, BuildingInfo> {
    @Override
    public Object save(JSON jsonData) {
        BuildingInfo buildingInfo = jsonData.toJavaObject(BuildingInfo.class);
        dao.create(buildingInfo);
        return buildingInfo.getBuildingID();
    }

    @Override
    void setID(BuildingInfo data, int id) {
        data.setBuildingID(id);
    }
}
