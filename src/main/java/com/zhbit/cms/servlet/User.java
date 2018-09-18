package com.zhbit.cms.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zhbit.cms.StatusCode;
import com.zhbit.cms.dao.UserDAO;
import com.zhbit.cms.exceptions.ParamLackException;
import com.zhbit.cms.exceptions.UnknownFailException;
import com.zhbit.cms.frame.LoginUsers;
import com.zhbit.cms.infobeans.PersonInfo;
import com.zhbit.cms.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.zhbit.cms.servlet.IdentifyingCode.IDENTIFYING_CODE;


@Controller
@RequestMapping("/user")
@ResponseBody
public class User {
    private LoginUsers loginUsers;
    private UserDAO userDAO;

    @Autowired
    public void setLoginUsers(LoginUsers loginUsers) {
        this.loginUsers = loginUsers;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/login")
    public JSONObject userLogin(@RequestBody JSONObject data, HttpSession httpSession) throws ParamLackException, UnknownFailException {
        String UserName = Objects.toString(data.getString("user_name"), "");
        String Password = Objects.toString(data.getString("password"), "");
        String loginWay = Objects.toString(data.getString("login_way"), "WEB");
        String sign = Objects.toString(data.getString("sign"), "");
        int personGroup = data.getIntValue("person_group");
        if ("".equals(UserName) || "".equals(Password)) {
            throw new ParamLackException("没有密码或者登录名");
        } else if (!checkSign(UserName, Password, personGroup, sign)) {
            throw new ParamLackException("签名失败");
        } else {
            PersonInfo personInfo = userDAO.loginUser(UserName, Tools.MD5(Password.toLowerCase()), personGroup);
            if (personInfo != null) {
                personInfo.setUserName(UserName);
                personInfo.setPersonGroup(personGroup);
                JSONObject resultJSON = Tools.quickJSON(StatusCode.COMPLETE, "登录成功")
                        .fluentPut("person_id", personInfo.getPersonID());
                resultJSON.put("data", JSONObject.toJSON(personInfo));
                httpSession.setAttribute("userInfo", personInfo);
                loginUsers.login(UserName, httpSession, loginWay);
                return resultJSON;
            } else {
                throw new UnknownFailException("登录炸了");
            }
        }
    }

    @RequestMapping("/logout")
    public @ResponseBody
    JSONObject userLogout(HttpSession httpSession) {
        loginUsers.logout(httpSession);
        return Tools.quickJSON(StatusCode.COMPLETE, "已登出");
    }

    @RequestMapping("/register")
    public JSONObject register(HttpSession httpSession, @RequestBody JSONObject data) throws ParamLackException {
        PersonInfo userInfo = data.toJavaObject(PersonInfo.class);
        if (!Objects.toString(userInfo.getUserName(), "").matches("^[a-zA-Z0-9]{3,16}$")) {
            throw new ParamLackException("非法用户名");
        } else if (!Objects.toString(userInfo.getEMail(), "").matches("^[0-9a-zA-Z]+@[0-9a-zA-Z]+(?:\\.[0-9a-zA-Z]+)+$")) {
            throw new ParamLackException("非法邮箱");
        } else if (!Objects.toString(userInfo.getPassWord(), "").matches("^[a-z0-9]{32}$")) {
            throw new ParamLackException("非法密码");
        } else if (!Objects.toString(userInfo.getName(), "").matches("^[\u4E00-\u9FA5]{2,5}")) {
            throw new ParamLackException("非法名字");
        } else if (!Objects.toString(userInfo.getTelPhone(), "").matches("^1[34578]\\d{9}$")) {
            throw new ParamLackException("非法手机");
        } else if (!data.containsKey(IDENTIFYING_CODE)) {
            throw new ParamLackException("验证码不存在");
        } else if (!Objects.equals(data.getString(IDENTIFYING_CODE), httpSession.getAttribute(IDENTIFYING_CODE))) {
            throw new ParamLackException("验证码错误");
        } else if (userInfo.getClassName() == null) {
            throw new ParamLackException("非法班级");
        } else {
            userInfo.setPassWord(Tools.MD5(userInfo.getPassWord()));
            userDAO.registerUser(userInfo);
        }
        return Tools.quickJSON(StatusCode.COMPLETE, StatusCode.SUCCESS);
    }


    /**
     * String param="UserName="+UserName+"&" +
     * "Password="+Password+"&" +
     * "PersonGroup="+PersonGroup+"&" +
     * "p=cms";
     * param= Tools.MD5(param).substring(12,20)
     *
     * @param UserName
     * @param Password
     * @param PersonGroup
     * @param sign
     * @return
     */
    private boolean checkSign(String UserName, String Password, int PersonGroup, String sign) {
        if ("".equals(sign)) {
            return false;
        }
        String param = "user_name=" + UserName + "&" +
                "password=" + Password + "&" +
                "person_group=" + PersonGroup + "&" +
                "p=cms";
        param = Tools.MD5(param).substring(12, 20);
        return param.equals(sign);
    }
}
