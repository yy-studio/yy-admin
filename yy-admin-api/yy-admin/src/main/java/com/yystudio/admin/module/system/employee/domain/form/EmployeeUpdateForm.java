package com.yystudio.admin.module.system.employee.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 更新员工
 *
 * @Author yy-studio
 * @Date 2021-12-20 21:06:49
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class EmployeeUpdateForm extends EmployeeAddForm {

    @Schema(description = "员工id")
    @NotNull(message = "员工id不能为空")
    private Long employeeId;
}
