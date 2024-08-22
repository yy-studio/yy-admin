package com.yystudio.base.common.util;

import com.yystudio.base.common.domain.RequestUser;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求用户  工具类
 *
 * @Author yy-studio
 * @Date 2022-05-30 21:22:12
 *
 * 
 * @Copyright  远游工作室
 */
@Slf4j
public class SmartRequestUtil {

    private static final ThreadLocal<RequestUser> REQUEST_THREAD_LOCAL = new ThreadLocal<>();

    public static void setRequestUser(RequestUser requestUser) {
        REQUEST_THREAD_LOCAL.set(requestUser);
    }

    public static RequestUser getRequestUser() {
        return REQUEST_THREAD_LOCAL.get();
    }

    public static Long getRequestUserId() {
        RequestUser requestUser = getRequestUser();
        return null == requestUser ? null : requestUser.getUserId();
    }


    public static void remove() {
        REQUEST_THREAD_LOCAL.remove();
    }


}
