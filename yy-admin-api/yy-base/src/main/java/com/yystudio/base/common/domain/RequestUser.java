package com.yystudio.base.common.domain;

import com.yystudio.base.common.enumeration.UserTypeEnum;

/**
 * 请求用户
 *
 * @Author yy-studio
 * @Date 2021-12-21 19:55:07
 * 
 *
 * @Copyright  远游工作室
 */
public interface RequestUser {

    /**
     * 请求用户id
     *
     * @return
     */
    Long getUserId();

    /**
     * 请求用户名称
     *
     * @return
     */
    String getUserName();

    /**
     * 获取用户类型
     */
    UserTypeEnum getUserType();

    /**
     * 获取请求的IP
     *
     * @return
     */
    String getIp();

    /**
     * 获取请求 user-agent
     *
     * @return
     */
    String getUserAgent();

}
