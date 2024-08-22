package com.yystudio.admin.module.business.oa.enterprise.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * OA企业模块编辑
 *
 * @Author yy-studio
 * @Date 2022/7/28 20:37:15
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class EnterpriseUpdateForm extends EnterpriseCreateForm {

    @Schema(description = "企业ID")
    @NotNull(message = "企业ID不能为空")
    private Long enterpriseId;
}
