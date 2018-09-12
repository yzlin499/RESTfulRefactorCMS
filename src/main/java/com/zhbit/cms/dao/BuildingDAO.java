package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.BuildingInfo;
import com.zhbit.cms.infobeans.FakerMap;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingDAO {

    List<BuildingInfo> selectBuilding(FakerMap fakerMap);

    BuildingInfo selectBuildingByID(int id);

    void createBuilding(BuildingInfo roomInfo);

    void updateBuilding(BuildingInfo roomInfo);

    int deleteBuilding(int id);
}
