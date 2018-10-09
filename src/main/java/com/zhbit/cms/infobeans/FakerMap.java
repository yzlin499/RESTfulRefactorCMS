package com.zhbit.cms.infobeans;

import java.util.Map;

public class FakerMap {

    private Map<String,?> oneMap;

    private int page = 1;

    private int limit = 20;

    private String order = "(select 1)";


    public FakerMap(Map<String, ?> oneMap) {
        this.oneMap = oneMap;
    }

    public Map<String, ?> getOneMap() {
        return oneMap;
    }

    public void setOneMap(Map<String, ?> oneMap) {
        this.oneMap = oneMap;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order == null ? "(select 1)" : order;
    }
}
