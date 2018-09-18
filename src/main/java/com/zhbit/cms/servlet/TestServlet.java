package com.zhbit.cms.servlet;

import com.zhbit.cms.dao.CourseDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping(value = "/test")
public class TestServlet {

    @Autowired
    public CourseDAO termDAO;



    @RequestMapping(method = RequestMethod.GET)
    public Object test(@RequestParam Map<String, Object> params){
        Logger logger = LogManager.getLogger(TestServlet.class);
        logger.error("adsdasdasdsadas");
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
