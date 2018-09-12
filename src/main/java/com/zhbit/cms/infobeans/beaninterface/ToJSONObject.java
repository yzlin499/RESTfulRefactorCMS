package com.zhbit.cms.infobeans.beaninterface;

import com.alibaba.fastjson.JSONObject;

public interface ToJSONObject {
    default Object toJSON(){
        return JSONObject.toJSON(this);
    }
}
