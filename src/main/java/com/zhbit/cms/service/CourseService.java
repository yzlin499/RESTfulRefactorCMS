package com.zhbit.cms.service;

import com.zhbit.cms.dao.CourseDAO;
import com.zhbit.cms.infobeans.CourseInfo;
import com.zhbit.cms.infobeans.FakerMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TableService(value = "course", tableName = "CourseInfo")
public class CourseService extends AbstractService<CourseDAO, CourseInfo> {
    private static final String ONE_WEEK = "queryCClWeek";
    private static final String ONE_TIME = "queryCClTime";

    @Override
    public Object index(Map<String, String> formData) {
        String order = formData.getOrDefault("_order", "");
        order = tableField.contains(order) ? order : null;
        int limit = Integer.parseInt(formData.getOrDefault("_limit", "20"));
        int page = Integer.parseInt(formData.getOrDefault("_page", "1"));

        int oneW = formData.containsKey(ONE_WEEK) ? Integer.parseInt(formData.get(ONE_WEEK)) : 0;
        int oneT = formData.containsKey(ONE_TIME) ? Integer.parseInt(formData.get(ONE_TIME)) : 0;
        formData.keySet().retainAll(tableField);
        FakerMap fakerMap = new FakerMap(formData);
        fakerMap.setOrder(order);
        fakerMap.setLimit(limit);
        fakerMap.setPage(page);
        List<CourseInfo> courseInfos = dao.select(fakerMap);
        if (oneW == 0 && oneT == 0) {
            return courseInfos;
        } else {
            Stream<CourseInfo> courseInfoStream = courseInfos.stream();
            if (oneW != 0) {
                courseInfoStream = courseInfoStream.filter(c -> bitmapParse(c.getClassWeek(), oneW));
            }
            if (oneT != 0) {
                courseInfoStream = courseInfoStream.filter(c -> bitmapParse(c.getClassTime(), oneT));
            }
            return courseInfoStream.collect(Collectors.toList());
        }
    }

    @Override
    void setID(CourseInfo data, int id) {
        data.setCourseID(id);
    }

    private boolean bitmapParse(int data, int bits) {
        return (data >> (bits - 1)) % 2 > 0;
    }


}