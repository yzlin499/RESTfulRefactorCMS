package com.zhbit.cms.dao;

import com.zhbit.cms.infobeans.FakerMap;

import java.util.List;

public interface BaseDAO<T> {
    List<T> select(FakerMap fakerMap);

    T selectByID(int id);

    int create(T roomInfo);

    int update(T roomInfo);

    int delete(int id);
}
