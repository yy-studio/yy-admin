package com.yystudio.base.module.support.datatracer.annoation;

import com.yystudio.base.common.enumeration.BaseEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段枚举
 *
 * @Author yy-studio
 * @Date 2022-07-23 19:38:52
 *
 *
 * @Copyright  远游工作室
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataTracerFieldEnum {

    Class<? extends BaseEnum> enumClass() default BaseEnum.class;

}
