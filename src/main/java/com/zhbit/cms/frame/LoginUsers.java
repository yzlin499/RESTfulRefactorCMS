package com.zhbit.cms.frame;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class LoginUsers {
    public static final String LOGIN_TYPE_CLIENT = "CLIENT";
    public static final String LOGIN_TYPE_PHONE = "PHONE";
    public static final String LOGIN_TYPE_WEB = "WEB";
    private static final String LOGIN_STATUS = "loginStatus";
    private static final String USER_NAME = "userName";
    private static final String LOGIN_TYPE = "loginType";
    private Map<String, HttpSession> webLoginMap = new HashMap<>();
    private Map<String, HttpSession> phoneLoginMap = new HashMap<>();
    private Map<String, HttpSession> clientLoginMap = new HashMap<>();

    public void login(String user, HttpSession httpSession, String type) {
        switch (type.toUpperCase()) {
            case LOGIN_TYPE_CLIENT:
                newLogin(user, httpSession, webLoginMap, LOGIN_TYPE_CLIENT);
                break;
            case LOGIN_TYPE_PHONE:
                newLogin(user, httpSession, phoneLoginMap, LOGIN_TYPE_PHONE);
                break;
            default:
                newLogin(user, httpSession, clientLoginMap, LOGIN_TYPE_WEB);
        }
    }

    private void newLogin(String user, HttpSession httpSession, Map<String, HttpSession> loginMap, String loginType) {
        httpSession.setMaxInactiveInterval(36000);
        httpSession.setAttribute(LOGIN_STATUS, true);
        httpSession.setAttribute(USER_NAME, user);
        httpSession.setAttribute(LOGIN_TYPE, loginType);

        HttpSession httpSession2 = loginMap.put(user, httpSession);
        if (httpSession2 != null && (!Objects.equals(httpSession, httpSession2))) {
            try {
                logout(httpSession2, loginMap);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isLogin(HttpSession httpSession) {
        Boolean b = (Boolean) httpSession.getAttribute(LOGIN_STATUS);
        return b == null ? false : b;
    }

    public void logout(HttpSession httpSession) {
        switch (httpSession.getAttribute(USER_NAME).toString()) {
            case LOGIN_TYPE_CLIENT:
                logout(httpSession, clientLoginMap);
                break;
            case LOGIN_TYPE_PHONE:
                logout(httpSession, phoneLoginMap);
                break;
            case LOGIN_TYPE_WEB:
                logout(httpSession, webLoginMap);
                break;
        }
    }

    private void logout(HttpSession httpSession, Map<String, HttpSession> loginMap) {
        loginMap.remove(httpSession.getAttribute(USER_NAME));
        httpSession.setAttribute(LOGIN_STATUS, false);
        httpSession.removeAttribute(USER_NAME);
        httpSession.removeAttribute(LOGIN_TYPE);
        httpSession.invalidate();
    }
}