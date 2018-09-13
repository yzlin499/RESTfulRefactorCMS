package com.zhbit.cms.service;

import com.zhbit.cms.dao.BuildingDAO;
import com.zhbit.cms.infobeans.BuildingInfo;

@TableService(value = "building", tableName = "BuildingInfo")
public class BuildingService extends AbstractService<BuildingDAO, BuildingInfo> {
    @Override
    void setID(BuildingInfo data, int id) {
        data.setBuildingID(id);
    }
}
