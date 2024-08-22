package com.yystudio.admin.module.system.datascope;


import com.yystudio.admin.module.system.datascope.constant.DataScopeWhereInTypeEnum;
import com.yystudio.admin.module.system.datascope.strategy.AbstractDataScopeStrategy;
import com.yystudio.admin.module.system.datascope.constant.DataScopeTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据范围
 *
 * @Author yy-studio
 * @Date 2022-03-18 20:59:17
 * 
 *
 * @Copyright  远游工作室
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataScope {

    DataScopeTypeEnum dataScopeType() ;

    DataScopeWhereInTypeEnum whereInType() default DataScopeWhereInTypeEnum.EMPLOYEE;

    /**
     * DataScopeWhereInTypeEnum.CUSTOM_STRATEGY类型 才可使用joinSqlImplClazz属性
     */
    Class<? extends AbstractDataScopeStrategy> joinSqlImplClazz()  default AbstractDataScopeStrategy.class;

    /**
     * 多个参数已逗号分隔，本属性主要用于joinSqlImplClazz 实现类跟进参数进行不同的范围控制，如不使用CUSTOM_STRATEGY，可不做配置
     */
    String paramName() default "";
    /**
     *
     * 第几个where 条件 从0开始
     */
    int whereIndex() default 0;

    /**
     * DataScopeWhereInTypeEnum为CUSTOM_STRATEGY类型时，此属性无效
     */
    String joinSql() default "";

}
