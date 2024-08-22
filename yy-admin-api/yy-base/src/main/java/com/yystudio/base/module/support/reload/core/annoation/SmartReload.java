package com.yystudio.base.module.support.reload.core.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义 SmartReload 注解
 *
 * @Author yy-studio
 * @Date 2015-03-02 19:11:52
 *
 * 
 * @Copyright  远游工作室
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmartReload {

    String value();
}
