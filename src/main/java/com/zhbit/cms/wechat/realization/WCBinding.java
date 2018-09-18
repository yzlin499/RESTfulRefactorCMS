package com.zhbit.cms.wechat.realization;

import com.zhbit.cms.infobeans.wechat.WCText;
import com.zhbit.cms.wechat.WeChatIO;
import com.zhbit.cms.wechat.event.WCTextEvent;

public class WCBinding implements WCTextEvent<String[]> {
    @Override
    public String[] filter(WeChatIO<WCText> wcInfo) {
        return null;
//        return wcInfo.getInfo().getContent().matches("查询[\\u4E00-\\u9FA5]{2}楼[\\u4E00-\\u9FA5]{2}课室");
    }

    @Override
    public void disposeMag(WeChatIO<WCText> weChatIO, String[] data) {

    }
}
