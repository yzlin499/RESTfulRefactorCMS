package com.zhbit.cms.wechat.event;


import com.zhbit.cms.infobeans.wechat.WCInfo;
import com.zhbit.cms.wechat.WeChatIO;

public interface WeChatEvent<T extends WCInfo, R> {
    R filter(WeChatIO<T> wcInfo);

    void disposeMag(WeChatIO<T> weChatIO, R data);
}
