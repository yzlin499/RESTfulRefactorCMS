package com.zhbit.cms.servlet;

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

    @RequestMapping(method = RequestMethod.GET)
    public Object test(@RequestParam Map<String, String> params) {
        StringBuilder a = new StringBuilder();
        params.forEach((k, v) -> {
            a.append(k)
                    .append("....")
                    .append(v)
                    .append("....")
                    .append(v.getClass());
        });
        return a.toString();
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
