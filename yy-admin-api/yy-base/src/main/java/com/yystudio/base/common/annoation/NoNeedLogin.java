package com.yystudio.base.common.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不需要登录注解
 *
 * @Author yy-studio
 * @Date 2022-05-30 21:22:12
 *
 * 
 * @Copyright  远游工作室
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoNeedLogin {
}
