package com.zhbit.cms.wechat;


import com.zhbit.cms.infobeans.wechat.WCInfo;
import com.zhbit.cms.wechat.event.WeChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class WeChatEventPool {
    private Map<String, ArrayList<WeChatEvent>> eventMap = new HashMap<>();

    public WeChatEventPool() {
    }

    public WeChatEventPool(List<WeChatEvent> weChatEvents) {
        weChatEvents.forEach(this::addWeChatEvent);
    }

    public boolean addWeChatEvent(WeChatEvent weChatEvent) {
        Stream.of(weChatEvent.getClass().getInterfaces())
                .filter(WeChatEvent.class::isAssignableFrom)
                .forEach(c -> {
                    String clazz = c.getSimpleName();
                    clazz = clazz.substring(2, clazz.length() - 5).toLowerCase();
                    getList(clazz).add(weChatEvent);
                });
        return true;
    }

    public boolean addWeChatEvent(Class<? extends WeChatEvent> weChatEvent) {
        try {
            return addWeChatEvent(weChatEvent.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeWeChatEvent(WeChatEvent weChatEvent) {
        String clazz = weChatEvent.getClass().getSimpleName();
        clazz = clazz.substring(2, clazz.length() - 5).toLowerCase();
        return getList(clazz).remove(weChatEvent);
    }

    public void newMsg(WeChatIO weChatIO) {
        WCInfo wcInfo = weChatIO.getInfo();
        for (WeChatEvent weChatEvent : getList(wcInfo.getMsgType())) {
            Object o = weChatEvent.filter(weChatIO);
            if (o != null) {
                weChatEvent.disposeMag(weChatIO, o);
                break;
            }
        }
        weChatIO.close();
    }

    private List<WeChatEvent> getList(String type) {
        if (!eventMap.containsKey(type)) {
            eventMap.put(type, new ArrayList<>());
        }
        return eventMap.get(type);
    }
}
