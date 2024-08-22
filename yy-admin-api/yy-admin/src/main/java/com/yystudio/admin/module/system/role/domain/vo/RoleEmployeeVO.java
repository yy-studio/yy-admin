package com.yystudio.admin.module.system.role.domain.vo;

import lombok.Data;

/**
 * 角色的员工
 *
 * @Author yy-studio
 * @Date 2022-04-08 21:53:04
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class RoleEmployeeVO {

    private Long roleId;

    private Long employeeId;

    private String roleName;
}
