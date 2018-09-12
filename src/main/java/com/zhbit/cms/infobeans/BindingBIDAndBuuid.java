package com.zhbit.cms.infobeans;

import com.alibaba.fastjson.annotation.JSONField;

public class BindingBIDAndBuuid {
    private String BID;
    private String Buuid;

    @JSONField(serialize = false)
    public boolean isNotNull() {
        return BID!=null && Buuid!=null;
    }

    @JSONField(name = "bid")
    public String getBID() {
        return BID;
    }

    @JSONField(name="bid")
    public void setBID(String BID) {
        this.BID = BID;
    }

    @JSONField(name = "buuid")
    public String getBuuid() {
        return Buuid;
    }

    @JSONField(name = "buuid")
    public void setBuuid(String buuid) {
        Buuid = buuid;
    }
}
