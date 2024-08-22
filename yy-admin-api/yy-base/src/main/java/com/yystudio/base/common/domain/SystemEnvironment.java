package com.yystudio.base.common.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import com.yystudio.base.common.enumeration.SystemEnvironmentEnum;

/**
 * 系统环境
 *
 * @Author yy-studio
 * @Date 2021/8/13 21:06:11
 * 
 *
 * @Copyright  远游工作室
 */
@AllArgsConstructor
@Getter
public class SystemEnvironment {

    /**
     * 是否位生产环境
     */
    private boolean isProd;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 当前环境
     */
    private SystemEnvironmentEnum currentEnvironment;
}
