package com.yystudio.base.module.support.datatracer.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段标签
 *
 * @Author yy-studio
 * @Date 2022-07-23 19:38:52
 *
 * 
 * @Copyright  远游工作室
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataTracerFieldLabel {
    /**
     * 本属性的注释信息
     * @return
     */
    String value() default "";

}
