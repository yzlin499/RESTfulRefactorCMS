package com.zhbit.cms.servlet;

import com.alibaba.fastjson.JSON;
import com.zhbit.cms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller("Dispose")
@RequestMapping("/{resource}")
@ResponseBody
public class Dispose {

    private ApplicationContext context;

    @Autowired
    public void setEnvironment(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object index(@PathVariable("resource")String resource,
                        @RequestParam Map<String, Object> params){
        return context.getBean(resource,BaseService.class).index(params);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object save(@PathVariable("resource")String resource,
                                     @RequestBody JSON param){
        return context.getBean(resource,BaseService.class).save(param);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Object update(@PathVariable("resource")String resource,
                                       @PathVariable("id")String id,
                                       @RequestBody JSON param){
        return context.getBean(resource,BaseService.class).update(param,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("resource")String resource,
                                       @PathVariable("id")String id,
                                       @RequestBody JSON param){
        return context.getBean(resource,BaseService.class).delete(param,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object read(@PathVariable("resource")String resource,
                       @PathVariable("id")String id,
                       @RequestParam Map<String, Object> params){
        return context.getBean(resource,BaseService.class).read(params,id);
    }

}
