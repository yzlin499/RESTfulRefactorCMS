package com.zhbit.cms.aop;

import com.alibaba.fastjson.JSONObject;
import com.zhbit.cms.StatusCode;
import com.zhbit.cms.exceptions.CMSException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DisposeAOP {

    @Pointcut("execution(* com.zhbit.cms.servlet.Dispose.*(..)) && !execution(* com.zhbit.cms.servlet.Dispose.setEnvironment())")
    public void pointCut() {
    }

    @Around("pointCut()")
    public JSONObject aou(ProceedingJoinPoint pjp) {
        try {
            Object data = pjp.proceed();
            return new JSONObject()
                    .fluentPut(StatusCode.STATUS, StatusCode.COMPLETE)
                    .fluentPut(StatusCode.DATA, data);
        } catch (CMSException e) {
            return e.toJSON();
        } catch (PersistenceException e) {
            return new JSONObject()
                    .fluentPut(StatusCode.STATUS, StatusCode.DB_ERROR)
                    .fluentPut(StatusCode.ERROR, e.getCause().getMessage());
        } catch (NumberFormatException e) {
            return new JSONObject()
                    .fluentPut(StatusCode.STATUS, StatusCode.PARAM_ERROR)
                    .fluentPut(StatusCode.ERROR, "参数转换错误");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return new JSONObject()
                    .fluentPut(StatusCode.STATUS, StatusCode.UNKNOWN_FAIL)
                    .fluentPut(StatusCode.ERROR, throwable.getMessage());
        }
    }
}