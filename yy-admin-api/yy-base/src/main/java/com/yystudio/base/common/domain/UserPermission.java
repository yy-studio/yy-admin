package com.yystudio.base.common.domain;

import lombok.Data;

import java.util.List;

/**
 * sa-token 所需的权限信息
 *
 * @Author yy-studio
 * @Date 2023/8/26 15:23:10
 *
 *
 * @Copyright  远游工作室，Since 2012
 */

@Data
public class UserPermission {

    /**
     * 权限列表
     */
    private List<String> permissionList;

    /**
     * 角色列表
     */
    private List<String> roleList;


}
