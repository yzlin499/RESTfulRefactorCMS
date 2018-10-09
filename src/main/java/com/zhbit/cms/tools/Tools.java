package com.zhbit.cms.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zhbit.cms.StatusCode;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public final class Tools {
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String cnToUnicode(String text) {
        StringBuilder sb = new StringBuilder();
        char[] chars = text.toCharArray();
        for (char c : chars) {
            if (c >= 0x4E00) {
                sb.append("\\u");
                sb.append(Integer.toString(c, 16));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String MD5(String str) {
        try {
            md.update(str.getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder num = new StringBuilder(new BigInteger(1, md.digest()).toString(16));
        while (num.length() < 32) {
            num.insert(0, "0");
        }
        return num.toString();
    }

    /**
     * 将传入的数据给转换为JSONObject
     *
     * @param request
     * @return
     */
    public static JSON dataToJSON(HttpServletRequest request) {
        if (request.getContentType() == null) {
            return null;
        }
        String result = "";
        try {
            result = getResultString(request);
            if ("application/json".equals(request.getContentType().split(";")[0].toLowerCase())) {
                return (JSON) JSONObject.parse(result);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        } catch (JSONException e) {
            return JSONArray.parseArray(result);
        }
    }

    /**
     * 将post过来的数据文本全部加载出来
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getResultString(HttpServletRequest request) throws IOException {
        return IOUtils.toString(request.getInputStream(), Objects.toString(request.getCharacterEncoding(), "UTF-8"));
    }

    /**
     * 生成验证码
     * 返回数组的第一个参数是验证码答案
     * 第二个是验证码的base64
     *
     * @return
     */


    public static void print(Object a) {
        System.out.println(a.toString());
    }

    /**
     * 简易时间停止
     *
     * @param millis
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject quickJSON(int status, String message) {
        return new JSONObject()
                .fluentPut(StatusCode.STATUS, status)
                .fluentPut(StatusCode.MESSAGE, message);
    }

    public static String firstWordLowerCase(String word) {
        byte[] key = word.getBytes();
        key[0] |= 32;
        return new String(key);
    }

    public static String firstWordUpCase(String word) {
        byte[] key = word.getBytes();
        key[0] &= 0xDF;
        return new String(key);
    }

}
