package com.zhbit.cms.infobeans;

import java.util.Map;

public class FakerMap {

    private Map<String,?> oneMap;

    public FakerMap(Map<String, ?> oneMap) {
        this.oneMap = oneMap;
    }

    public Map<String, ?> getOneMap() {
        return oneMap;
    }

    public void setOneMap(Map<String, ?> oneMap) {
        this.oneMap = oneMap;
    }
}
