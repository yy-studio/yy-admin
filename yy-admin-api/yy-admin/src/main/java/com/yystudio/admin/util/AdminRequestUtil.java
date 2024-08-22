package com.yystudio.admin.util;

import com.yystudio.admin.module.system.login.domain.RequestEmployee;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.util.SmartRequestUtil;

/**
 * admin 端的请求工具类
 *
 * @Author yy-studio
 * @Date 2023/7/28 19:39:21
 *
 *
 * @Copyright  远游工作室，Since 2012
 */
public final class AdminRequestUtil {


    public static RequestEmployee getRequestUser() {
        return (RequestEmployee) SmartRequestUtil.getRequestUser();
    }

    public static Long getRequestUserId() {
        RequestUser requestUser = getRequestUser();
        return null == requestUser ? null : requestUser.getUserId();
    }


}
