package com.zhbit.cms.service;

import com.alibaba.fastjson.JSON;
import com.zhbit.cms.dao.BuildingDAO;
import com.zhbit.cms.dao.ToolsDAO;
import com.zhbit.cms.infobeans.BuildingInfo;
import com.zhbit.cms.infobeans.FakerMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("building")
public class BuildingService implements BaseService {

    private BuildingDAO buildingDAO;
    private List<String> tableCourse;

    @Autowired
    public void setBuildingDAO(BuildingDAO buildingDAO) {
        this.buildingDAO = buildingDAO;
    }

    @Autowired
    public void setTableCourse(ToolsDAO toolsDAO) {
        tableCourse=toolsDAO.select("BuildingInfo");
    }

    @Override
    public Object index(Map<String, Object> formData) {
        formData.keySet().retainAll(tableCourse);
        return buildingDAO.select(new FakerMap(formData));
    }

    @Override
    public Object save(JSON jsonData) {
        BuildingInfo buildingInfo=jsonData.toJavaObject(BuildingInfo.class);
        buildingInfo.setBuildingID(null);
        buildingDAO.create(buildingInfo);
        System.out.println(buildingInfo);
        return buildingInfo.getBuildingID();
    }

    @Override
    public Object update(JSON jsonData, String id) {
        BuildingInfo buildingInfo=jsonData.toJavaObject(BuildingInfo.class);
        buildingInfo.setBuildingID(Integer.parseInt(id));
        buildingDAO.update(buildingInfo);
        return id;
    }

    @Override
    public Object delete(JSON jsonData, String id) {
        return buildingDAO.delete(Integer.parseInt(id));
    }

    @Override
    public Object read(Map<String, Object> formData, String id) {
        return buildingDAO.selectByID(Integer.parseInt(id));
    }
}
