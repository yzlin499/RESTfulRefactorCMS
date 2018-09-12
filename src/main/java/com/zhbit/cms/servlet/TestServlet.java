package com.zhbit.cms.servlet;

import com.zhbit.cms.dao.TermDAO;
import com.zhbit.cms.dao.ToolsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "/test")
public class TestServlet {

    @Autowired
    public TermDAO termDAO;

    @RequestMapping(method = RequestMethod.GET)
    public Object test(@RequestParam Map<String, Object> params){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object test2(@RequestParam Map<String, String> params){
        return params.toString()+56+65445;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Object test3(@RequestParam Map<String, String> params){
        return params.toString()+123123123;
    }
}
