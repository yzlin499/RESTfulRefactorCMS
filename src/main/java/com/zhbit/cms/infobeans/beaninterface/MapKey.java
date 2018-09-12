package com.zhbit.cms.infobeans.beaninterface;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface MapKey {
    String value() default "";
    boolean create() default true;
}
