package com.yystudio.base.module.support.codegenerator.constant;

import com.yystudio.base.common.enumeration.BaseEnum;

/**
 * 删除类型
 *
 * @Author yy-studio
 * @Date 2022-06-30 22:15:38
 *
 *
 * @Copyright  远游工作室
 */
public enum CodeDeleteEnum implements BaseEnum {

    SINGLE("Single", "单个删除"),
    BATCH("Batch", "批量删除"),
    SINGLE_AND_BATCH("SingleAndBatch", "单个和批量删除");

    private String value;

    private String desc;

    CodeDeleteEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
