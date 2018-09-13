package com.zhbit.cms.service;

import com.alibaba.fastjson.JSON;
import com.zhbit.cms.dao.TermDAO;
import com.zhbit.cms.dao.ToolsDAO;
import com.zhbit.cms.infobeans.FakerMap;
import com.zhbit.cms.infobeans.TermInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("term")
public class TermService implements BaseService{

    private TermDAO termDAO;
    private List<String> tableCourse;

    @Autowired
    public void setTermDAO(TermDAO termDAO) {
        this.termDAO = termDAO;
    }

    @Autowired
    public void setTableCourse(ToolsDAO toolsDAO) {
        tableCourse=toolsDAO.select("TermInfo");
    }

    @Override
    public Object index(Map<String, Object> formData) {
        formData.keySet().retainAll(tableCourse);
        return termDAO.select(new FakerMap(formData));
    }

    @Override
    public Object save(JSON jsonData) {
        return termDAO.create(jsonData.toJavaObject(TermInfo.class));
    }

    @Override
    public Object update(JSON jsonData, String id) {
        TermInfo termInfo=jsonData.toJavaObject(TermInfo.class);
        termInfo.setTermID(Integer.parseInt(id));
        termDAO.update(termInfo);
        return id;
    }

    @Override
    public Object delete(JSON jsonData, String id) {
        return termDAO.delete(Integer.parseInt(id));
    }

    @Override
    public Object read(Map<String, Object> formData, String id) {
        return termDAO.selectByID(Integer.parseInt(id));
    }
}
