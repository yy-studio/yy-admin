package com.yystudio.base.module.support.apiencrypt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解密注解
 *
 * @Author yy-studio
 * @Date 2023/10/21 11:41:46
 *
 *
 * @Copyright  远游工作室
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ApiDecrypt {
}