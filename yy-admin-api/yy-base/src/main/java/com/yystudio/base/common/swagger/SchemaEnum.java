package com.yystudio.base.common.swagger;

import com.yystudio.base.common.enumeration.BaseEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举类字段属性的 自定义 swagger 注解
 *
 * @Author yy-studio
 * @Date 2019/05/16 23:18
 *
 * 
 * @Copyright  远游工作室
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SchemaEnum {

    /**
     * 枚举类对象
     *
     */
    Class<? extends BaseEnum> value();

    String example() default "";

    boolean hidden() default false;

    boolean required() default true;

    String dataType() default "";

    String desc() default "";

}
