package com.zhbit.cms.service;

import com.zhbit.cms.exceptions.CMSException;

import java.util.List;
import java.util.Map;

public interface BaseQuery {
    List<Map<String, Object>> index(Map<String, String> data, int page, int limit) throws CMSException;
}
