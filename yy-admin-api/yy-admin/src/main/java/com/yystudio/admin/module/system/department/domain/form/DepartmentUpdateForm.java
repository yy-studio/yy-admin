package com.yystudio.admin.module.system.department.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 部门 更新表单
 *
 * @Author yy-studio
 * @Date 2022-01-12 20:37:48
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class DepartmentUpdateForm extends DepartmentAddForm {

    @Schema(description = "部门id")
    @NotNull(message = "部门id不能为空")
    private Long departmentId;

}
