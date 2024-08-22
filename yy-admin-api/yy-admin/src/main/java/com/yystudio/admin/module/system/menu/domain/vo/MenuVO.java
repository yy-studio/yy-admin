package com.yystudio.admin.module.system.menu.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.admin.module.system.menu.domain.form.MenuBaseForm;

import java.time.LocalDateTime;

/**
 * 菜单
 *
 * @Author yy-studio
 * @Date 2022-03-06 22:04:37
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class MenuVO extends MenuBaseForm {

    @Schema(description = "菜单ID")
    private Long menuId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建人")
    private Long createUserId;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新人")
    private Long updateUserId;
}
