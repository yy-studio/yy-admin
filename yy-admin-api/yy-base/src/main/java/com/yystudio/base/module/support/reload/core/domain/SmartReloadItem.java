package com.yystudio.base.module.support.reload.core.domain;

import lombok.Data;

/**
 * reload项目
 *
 * @Author yy-studio
 * @Date 2015-03-02 19:11:52
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class SmartReloadItem {

    /**
     * 项名称
     */
    private String tag;

    /**
     * 参数
     */
    private String args;

    /**
     * 标识
     */
    private String identification;

}
