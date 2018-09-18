package com.zhbit.cms.wechat;

import com.alibaba.fastjson.JSONObject;
import com.zhbit.cms.infobeans.wechat.SignInfo;
import com.zhbit.cms.tools.NetTools;
import com.zhbit.cms.tools.Tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WeChatJSUtil {

    private String accessToken;
    private String JSTicket;

    public WeChatJSUtil() {
        init();
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private void init() {
        new Thread(() -> {
            while (true) {
                updateToken();
                updateJSTicket();
                Tools.sleep(1000 * 7000);
            }
        }).start();
    }

    public String getAccessToken() {
        while (accessToken == null) {
            Tools.sleep(300);
        }
        return accessToken;
    }

    public String getJSTicket() {
        while (accessToken == null) {
            Tools.sleep(300);
        }
        return JSTicket;
    }

    private void updateToken() {
        JSONObject result = JSONObject.parseObject(NetTools.sendGet(W.ApiUrl.AccessToken,
                "grant_type=client_credential" +
                        "&appid=" + W.Configure.AppID +
                        "&secret=" + W.Configure.AppSecret));
        accessToken = result.getString("access_token");
    }

    private void updateJSTicket() {
        JSONObject result = JSONObject.parseObject(NetTools.sendGet(W.ApiUrl.GetTicket,
                "access_token=" + accessToken +
                        "&type=jsapi"));
        JSTicket = result.getString("ticket");
    }

    public SignInfo sign(String url) {
        Map<String, String> ret = new HashMap<>();
        //这里的jsapi_ticket是获取的jsapi_ticket。
        String nonceStr = UUID.randomUUID().toString();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String signature = null;

        //注意这里参数名必须全部小写，且必须有序
        String str = "jsapi_ticket=" + JSTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        SignInfo signInfo = new SignInfo();
        signInfo.setUrl(url);
        signInfo.setJsTicket(JSTicket);
        signInfo.setNonceStr(nonceStr);
        signInfo.setSignature(signature);
        signInfo.setTimestamp(timestamp);
        signInfo.setAppID(W.Configure.AppID);
        return signInfo;
    }

}
