package com.zhbit.cms.wechat;

import com.zhbit.cms.infobeans.wechat.WCInfo;
import com.zhbit.cms.tools.Tools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.cglib.beans.BeanMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

public class WeChatIO<T extends WCInfo> {
    private static final SAXReader saxReader = new SAXReader();
    private static final WeChatFromIDPool idPool = WeChatFromIDPool.getInstance();
    private WCInfo wcInfo;
    private PrintWriter printWriter;
    private boolean isNotClose = true;

    public WeChatIO(HttpServletRequest request, HttpServletResponse response) {
        try {
            init(Tools.getResultString(request));
            response.setCharacterEncoding("UTF-8");
            printWriter = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = LogManager.getLogger(WeChatIO.class);
            logger.error(e);
        }
    }

    public WeChatIO(String inputXml, PrintWriter printWriter) {
        try {
            init(inputXml);
            this.printWriter = printWriter;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(String inputXml) throws Exception {
        //TODO:大规模需要修复
        Element element = saxReader.read(new ByteArrayInputStream(inputXml.getBytes("UTF-8"))).getRootElement();
        Map<String, String> map = element.elements().stream().collect(Collectors.toMap(Element::getName, Element::getText));

        String type = map.get("MsgType");
        Object o = Class.forName("com.zhbit.cms.infobeans.wechat." +
                "WC" + (char) (type.charAt(0) & 0xDF) + type.substring(1))
                .newInstance();
        BeanMap.create(o).putAll(map);
        wcInfo = (WCInfo) o;
    }

    public T getInfo() {
        return (T) wcInfo;
    }

    public void replyNull() {
        reply("");
    }

    public void replyText(String text) {
        reply((String.format("<xml><ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%d</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content></xml>",
                wcInfo.getFromUserName(), wcInfo.getToUserName(), System.currentTimeMillis() / 1000, text)));
    }

    private void reply(String repXml) {
        if (isNotClose) {
            printWriter.print(repXml);
        }
    }

    public void close() {
        if (isNotClose) {
            replyNull();
            isNotClose = false;
            printWriter.close();
            printWriter = null;
            wcInfo = null;
        }
    }

    public Object setAttribute(Object key, Object value) {
        return idPool.setAttribute(wcInfo.getFromUserName(), key, value);
    }

    public Object getAttribute(Object key) {
        return idPool.getAttribute(getID(), key);
    }

    public Map<Object, Object> getAttributeMap() {
        return idPool.getAttributeMap(wcInfo.getFromUserName());
    }

    public String getID() {
        return wcInfo.getFromUserName();
    }

    public Object removeAttribute(Object key) {
        return idPool.removeAttribute(wcInfo.getFromUserName(), key);
    }

    public void removeID() {
        idPool.removeID(wcInfo.getFromUserName());
    }
}
