package com.yystudio.admin.module.system.role.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 角色
 *
 * @Author yy-studio
 * @Date 2022-04-08 21:53:04
 *
 * 
 * @Copyright  远游工作室
 */
@Data
public class RoleVO {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色备注")
    private String remark;
}
