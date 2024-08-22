package com.yystudio.admin.module.system.employee.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.util.SmartVerificationUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 修改密码所需参数
 *
 * @Author yy-studio
 * @Date 2021-12-20 21:06:49
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class EmployeeUpdatePasswordForm {

    @Schema(hidden = true)
    private Long employeeId;

    @Schema(description = "原密码")
    @NotBlank(message = "原密码不能为空哦")
    @Pattern(regexp = SmartVerificationUtil.PWD_REGEXP, message = "原密码请输入6-15位(数字|大小写字母|小数点)")
    private String oldPassword;

    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空哦")
    @Pattern(regexp = SmartVerificationUtil.PWD_REGEXP, message = "新密码请输入6-15位(数字|大小写字母|小数点)")
    private String newPassword;
}
