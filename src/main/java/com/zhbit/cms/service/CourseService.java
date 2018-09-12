package com.zhbit.cms.service;

import com.alibaba.fastjson.JSON;
import com.zhbit.cms.dao.CourseDAO;
import com.zhbit.cms.dao.ToolsDAO;
import com.zhbit.cms.infobeans.CourseInfo;
import com.zhbit.cms.infobeans.FakerMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("course")
public class CourseService implements BaseService{

    private CourseDAO courseDAO;
    private List<String> tableCourse;

    @Autowired
    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Autowired
    public void setTableCourse(ToolsDAO toolsDAO) {
        tableCourse=toolsDAO.select("CourseInfo");
    }

    @Override
    public Object index(Map<String, Object> formData) {
        formData.keySet().retainAll(tableCourse);
        return courseDAO.selectCourse(new FakerMap(formData));
    }

    @Override
    public Object save(JSON jsonData) {
        return courseDAO.createCourse(jsonData.toJavaObject(CourseInfo.class));
    }

    @Override
    public Object update(JSON jsonData, String id) {
        courseDAO.updateCourse(jsonData.toJavaObject(CourseInfo.class),id);
        return id;
    }

    @Override
    public Object delete(JSON jsonData, String id) {
        return courseDAO.deleteCourse(id);
    }

    @Override
    public Object read(Map<String, Object> formData, String id) {
        return courseDAO.selectCourseByID(Integer.parseInt(id));
    }

}
