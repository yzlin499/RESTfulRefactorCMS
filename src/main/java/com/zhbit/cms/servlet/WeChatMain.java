package com.zhbit.cms.servlet;

import com.zhbit.cms.infobeans.wechat.SignInfo;
import com.zhbit.cms.wechat.WeChatEventPool;
import com.zhbit.cms.wechat.WeChatIO;
import com.zhbit.cms.wechat.WeChatJSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/wechat")
public class WeChatMain {
    private WeChatEventPool weChatEventPool;
    private WeChatJSUtil weChatJSUtil;

    @Autowired
    public void setWeChatJSUtil(WeChatJSUtil weChatJSUtil) {
        this.weChatJSUtil = weChatJSUtil;
    }

    @Autowired
    public void setWeChatEventPool(WeChatEventPool weChatEventPool) {
        this.weChatEventPool = weChatEventPool;
    }

    @RequestMapping("/main")
    public void doWeChatMain(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WeChatIO weChatIO = new WeChatIO(req, resp);
        weChatEventPool.newMsg(weChatIO);
    }

    @RequestMapping(value = "/jssign")
    public @ResponseBody
    SignInfo jsUtilSign(@RequestParam("url") String req) {
        return weChatJSUtil.sign(req);
    }


//    @RequestMapping("/main")
//    public void bindService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.getWriter().print(req.getParameter("echostr"));
//    }//绑定用
}
