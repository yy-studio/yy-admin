package com.yystudio.admin.module.system.role.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 选择角色
 *
 * @Author yy-studio
 * @Date 2022-04-08 21:53:04
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class RoleSelectedVO extends RoleVO {

    @Schema(description = "角色名称")
    private Boolean selected;
}
