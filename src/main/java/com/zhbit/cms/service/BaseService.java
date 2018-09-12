package com.zhbit.cms.service;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public interface BaseService {

    Object index(Map<String, Object> formData);

    Object save(JSON jsonData);

    Object update(JSON jsonData,String id);

    Object delete(JSON jsonData,String id);

    Object read(Map<String, Object> formData,String id);

}
