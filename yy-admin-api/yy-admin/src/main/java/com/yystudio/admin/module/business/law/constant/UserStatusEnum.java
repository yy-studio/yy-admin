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
public enum UserStatusEnum implements BaseEnum {


    NORMAL(1, "正常"),

    UN_ACTIVE(0, "未激活"),

    CANCEL(-1, "注销"),
    ;

    private final Integer value;

    private final String desc;
}
