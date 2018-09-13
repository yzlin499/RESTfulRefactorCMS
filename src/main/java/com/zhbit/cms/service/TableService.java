package com.zhbit.cms.service;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TableService {
    String value() default "";

    String tableName();
}
