package com.my.myshop.exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Description:    方法执行了什么操作
* @Author:         aylfq5
* @CreateDate:     2019/3/7 17:02
* @Version:        1.0
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Operation {
    String value() default "";
}
