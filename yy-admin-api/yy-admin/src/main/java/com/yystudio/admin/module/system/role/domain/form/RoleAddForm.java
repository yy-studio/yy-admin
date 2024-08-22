package com.yystudio.admin.module.system.role.domain.form;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 角色 添加表单
 *
 * @Author yy-studio
 * @Date 2022-02-26 19:09:42
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class RoleAddForm {

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @NotNull(message = "角色名称不能为空")
    @Length(min = 1, max = 20, message = "角色名称(1-20)个字符")
    private String roleName;

    @Schema(description = "角色编码")
    @NotNull(message = "角色编码 不能为空")
    @Length(min = 1, max = 20, message = "角色编码(1-20)个字符")
    private String roleCode;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    @Length(max = 255, message = "角色描述最多255个字符")
    private String remark;


}
