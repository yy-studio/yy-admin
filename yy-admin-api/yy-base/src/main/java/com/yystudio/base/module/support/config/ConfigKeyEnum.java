package com.yystudio.base.module.support.config;

import com.yystudio.base.common.enumeration.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统配置常量类
 *
 * @Author yy-studio
 * @Date 2022-03-14 20:46:27
 * 
 *
 * @Copyright  远游工作室
 */
@Getter
@AllArgsConstructor
public enum ConfigKeyEnum implements BaseEnum {

    /**
     * 万能密码
     */
    SUPER_PASSWORD("super_password", "万能密码"),

    ;

    private final String value;

    private final String desc;
}
