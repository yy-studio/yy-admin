package com.yystudio.admin.module.system.role.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;

/**
 * 角色 查询
 *
 * @Author yy-studio
 * @Date 2022-02-26 19:09:42
 * 
 * 
 * @Copyright  远游工作室
 */
@Data
public class RoleQueryForm extends PageParam {

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色id")
    private String roleId;
}
