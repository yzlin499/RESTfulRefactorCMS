package com.zhbit.cms.interceptor;

import com.zhbit.cms.StatusCode;
import com.zhbit.cms.frame.LoginUsers;
import com.zhbit.cms.frame.PermissionsParsing;
import com.zhbit.cms.infobeans.PersonInfo;
import com.zhbit.cms.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class Permissions extends HandlerInterceptorAdapter {

    private PermissionsParsing parsing;
    private LoginUsers loginUsers;

    @Autowired
    public void setParsing(PermissionsParsing parsing) {
        this.parsing = parsing;
    }

    @Autowired
    public void setLoginUsers(LoginUsers loginUsers) {
        this.loginUsers = loginUsers;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String pathInfo = request.getServletPath();
        HttpSession httpSession = request.getSession();

        if (loginUsers.isLogin(httpSession)) {
            PersonInfo userInfo = (PersonInfo) httpSession.getAttribute("userInfo");
            if (userInfo != null) {
                int level = parsing.getLevel(userInfo.getPersonGroup(), pathInfo);
                if (level > userInfo.getPersonCtrlLevel()) {
                    response.getWriter().print(Tools.quickJSON(StatusCode.FORBIDDEN, "forbidden"));
                    return false;
                } else {
                    return true;
                }
            }
        }
        if (parsing.getLevel(0, pathInfo) == 0) {
            return true;
        } else {
            response.getWriter().print(Tools.quickJSON(StatusCode.FORBIDDEN, "login fail"));
            return false;
        }
    }
}
