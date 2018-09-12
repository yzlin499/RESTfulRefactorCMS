package com.zhbit.cms.infobeans.beaninterface;

import com.alibaba.fastjson.annotation.JSONField;

public interface CheckNullPoint {
    @JSONField(serialize=false)
    boolean isNotNull();
}
