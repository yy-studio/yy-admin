package com.yystudio.admin.module.system.employee.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改登录人头像
 *
 * @Author yy-studio
 * @Date 2024年6月30日00:26:35
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class EmployeeUpdateAvatarForm {

    @Schema(hidden = true)
    private Long employeeId;

    @Schema(description = "头像")
    @NotBlank(message = "头像不能为空哦")
    private String avatar;
}
