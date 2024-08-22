package com.yystudio.admin.module.system.role.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色的菜单更新
 *
 * @Author yy-studio
 * @Date 2022-04-08 21:53:04
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class RoleMenuUpdateForm {

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    /**
     * 菜单ID 集合
     */
    @Schema(description = "菜单ID集合")
    @NotNull(message = "菜单ID不能为空")
    private List<Long> menuIdList;

}
