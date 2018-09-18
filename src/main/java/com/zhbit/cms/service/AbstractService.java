package com.zhbit.cms.service;

import com.alibaba.fastjson.JSON;
import com.zhbit.cms.dao.BaseDAO;
import com.zhbit.cms.dao.ToolsDAO;
import com.zhbit.cms.infobeans.FakerMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

abstract class AbstractService<DAO extends BaseDAO<T>, T> implements BaseService {

    private List<String> tableField;
    protected DAO dao;
    private Class<T> genericClass = getGenericClass();


    @Autowired
    public void setTableCourse(ToolsDAO toolsDAO) {
        tableField = toolsDAO.select(this.getClass().getAnnotation(TableService.class).tableName());
    }

    @Autowired
    public void setDAO(DAO dao) {
        this.dao = dao;
    }

    @Override
    public Object index(Map<String, Object> formData) {
        formData.keySet().retainAll(tableField);
        return dao.select(new FakerMap(formData));
    }

    @Override
    public Object save(JSON jsonData) {
        return dao.create(jsonData.toJavaObject(genericClass));
    }

    @Override
    public Object update(JSON jsonData, String id) {
        T t = jsonData.toJavaObject(genericClass);
        setID(t, Integer.parseInt(id));
        dao.update(t);
        return id;
    }

    @Override
    public Object delete(JSON jsonData, String id) {
        return dao.delete(Integer.parseInt(id));
    }

    @Override
    public Object read(Map<String, Object> formData, String id) {
        return dao.selectByID(Integer.parseInt(id));
    }

    /**
     * 获取信息的Class
     *
     * @return 信息的Class
     */
    private Class<T> getGenericClass() {
        try {
            Type[] params = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
            return (Class<T>) params[1];
        } catch (ClassCastException e) {
            System.out.println("获取泛型失败");
            return null;
        }
    }

    abstract void setID(T data, int id);

}
