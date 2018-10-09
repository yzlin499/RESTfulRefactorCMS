package com.zhbit.cms.wechat;

import com.zhbit.cms.infobeans.wechat.WCInfo;
import com.zhbit.cms.infobeans.wechat.WCText;
import com.zhbit.cms.tools.Tools;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WeChatIO<T extends WCInfo> {
    private static final Logger logger = LogManager.getLogger(WeChatIO.class);
    private static final SAXReader SAX_READER = new SAXReader();
    private static final WeChatFromIDPool ID_POOL = WeChatFromIDPool.getInstance();
    private WCInfo wcInfo;
    private PrintWriter printWriter;
    private boolean isNotClose = true;

    public WeChatIO(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String inputXml = Tools.getResultString(request);
        try {
            init(inputXml);
        } catch (UnsupportedEncodingException |
                DocumentException |
                IllegalAccessException |
                ClassNotFoundException |
                InstantiationException e) {
            logger.warn(e);
            logger.warn(inputXml);
            replyText("服务器发生未知错误");
            close();
        }
        response.setCharacterEncoding("UTF-8");
        this.printWriter = response.getWriter();
    }

    private void init(String inputXml) throws UnsupportedEncodingException, DocumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Element element = SAX_READER.read(new ByteArrayInputStream(inputXml.getBytes("UTF-8"))).getRootElement();
        String type = element.elementText("MsgType");
        wcInfo = (WCText) Class.forName("com.zhbit.cms.infobeans.wechat.WC" + Tools.firstWordUpCase(type))
                .newInstance();
        element.elements().forEach(i -> {
            try {
                BeanUtils.setProperty(wcInfo, Tools.firstWordLowerCase(i.getName()), i.getText());
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.warn(e);
            }
        });
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
        return ID_POOL.setAttribute(wcInfo.getFromUserName(), key, value);
    }

    public Object getAttribute(Object key) {
        return ID_POOL.getAttribute(getID(), key);
    }

    public Map<Object, Object> getAttributeMap() {
        return ID_POOL.getAttributeMap(wcInfo.getFromUserName());
    }

    public String getID() {
        return wcInfo.getFromUserName();
    }

    public Object removeAttribute(Object key) {
        return ID_POOL.removeAttribute(wcInfo.getFromUserName(), key);
    }

    public void removeID() {
        ID_POOL.removeID(wcInfo.getFromUserName());
    }
}
