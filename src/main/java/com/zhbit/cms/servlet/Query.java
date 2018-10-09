package com.zhbit.cms.servlet;

import com.zhbit.cms.exceptions.CMSException;
import com.zhbit.cms.service.RoomQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/query")
@ResponseBody
public class Query {


    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public Object index(@RequestParam Map<String, String> params,
                        @RequestParam(defaultValue = "1", value = "_page") int page,
                        @RequestParam(value = "_limit", defaultValue = "20") int limit,
                        @Autowired RoomQuery roomQuery) throws CMSException {
        return roomQuery.index(params, page, limit);
    }
}
