package com.yystudio.admin.module.system.datascope.constant;


import com.yystudio.base.common.enumeration.BaseEnum;

/**
 * 数据范围 sql where
 *
 * @Author yy-studio
 * @Date 2020/11/28  20:59:17
 *
 *
 * @Copyright  远游工作室
 */
public enum DataScopeWhereInTypeEnum implements BaseEnum {

    /**
     * 以员工IN
     */
    EMPLOYEE(0, "以员工IN"),

    /**
     * 以部门IN
     */
    DEPARTMENT(1, "以部门IN"),

    /**
     * 自定义策略
     */
    CUSTOM_STRATEGY(2, "自定义策略");

    private final Integer value;
    private final String desc;

    DataScopeWhereInTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }


}
