package com.yystudio.admin.module.business.oa.enterprise.constant;


import com.yystudio.base.common.enumeration.BaseEnum;

/**
 * 企业类型
 *
 * @Author yy-studio
 * @Date 2022/7/28 20:37:15
 * 
 *
 * @Copyright  远游工作室
 */
public enum EnterpriseTypeEnum implements BaseEnum {

    /**
     * 有限企业
     */
    NORMAL(1, "有限企业"),

    /**
     * 外资企业
     */
    FOREIGN(2, "外资企业"),
    ;

    private Integer value;
    private String desc;

    EnterpriseTypeEnum(Integer value, String desc) {
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
