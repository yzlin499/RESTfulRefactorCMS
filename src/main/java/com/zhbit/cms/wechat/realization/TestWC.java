package com.zhbit.cms.wechat.realization;

import com.zhbit.cms.infobeans.wechat.WCText;
import com.zhbit.cms.wechat.WeChatIO;
import com.zhbit.cms.wechat.event.WCTextEvent;

public class TestWC implements WCTextEvent<String> {
    @Override
    public String filter(WeChatIO<WCText> wcInfo) {
        return "";
    }

    @Override
    public void disposeMag(WeChatIO<WCText> weChatIO, String data) {
        weChatIO.replyText(weChatIO.getInfo().getContent());
    }
}
