package com.yystudio.admin.module.system.employee.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 员工列表
 *
 * @Author yy-studio
 * @Date 2021-12-20 21:06:49
 *
 *
 * @Copyright 远游工作室
 */
@Data
public class EmployeeQueryForm extends PageParam {

    @Schema(description = "搜索词")
    @Length(max = 20, message = "搜索词最多20字符")
    private String keyword;

    @Schema(description = "部门id")
    private Long departmentId;

    @Schema(description = "是否禁用")
    private Boolean disabledFlag;

    @Schema(description = "员工id集合")
    @Size(max = 99, message = "最多查询99个员工")
    private List<Long> employeeIdList;

    @Schema(description = "删除标识", hidden = true)
    private Boolean deletedFlag;

}
