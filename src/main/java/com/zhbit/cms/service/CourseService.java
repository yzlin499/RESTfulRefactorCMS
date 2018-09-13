package com.zhbit.cms.service;

import com.zhbit.cms.dao.CourseDAO;
import com.zhbit.cms.infobeans.CourseInfo;

@TableService(value = "course", tableName = "CourseInfo")
public class CourseService extends AbstractService<CourseDAO, CourseInfo> {
    @Override
    void setID(CourseInfo data, int id) {
        data.setCourseID(id);
    }
}
