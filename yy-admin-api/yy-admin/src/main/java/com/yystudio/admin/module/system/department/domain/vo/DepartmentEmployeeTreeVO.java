package com.yystudio.admin.module.system.department.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.admin.module.system.employee.domain.vo.EmployeeVO;

import java.util.List;

/**
 * 部门
 *
 * @Author yy-studio
 * @Date 2022-01-12 20:37:48
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class DepartmentEmployeeTreeVO extends DepartmentVO {

    @Schema(description = "部门员工列表")
    private List<EmployeeVO> employees;

    @Schema(description = "子部门")
    private List<DepartmentEmployeeTreeVO> children;

}
