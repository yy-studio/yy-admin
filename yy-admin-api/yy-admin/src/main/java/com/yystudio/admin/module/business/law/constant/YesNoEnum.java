package com.yystudio.admin.module.business.law.constant;


import com.yystudio.base.common.enumeration.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 *
 * @Author yy-studio
 * @Date 2021-10-25 20:26:54
 *
 *
 * @Copyright  远游工作室
 */
@AllArgsConstructor
@Getter
public enum YesNoEnum implements BaseEnum {


    YES(1, "是"),

    NO(0, "否"),
    ;

    private final Integer value;

    private final String desc;
}
