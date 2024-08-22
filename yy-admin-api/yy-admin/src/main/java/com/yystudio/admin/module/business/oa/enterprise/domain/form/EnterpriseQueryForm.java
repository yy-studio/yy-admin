package com.yystudio.admin.module.business.oa.enterprise.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

/**
 * OA企业模块分页查询
 *
 * @Author yy-studio
 * @Date 2022/7/28 20:37:15
 *
 * 
 * @Copyright 远游工作室
 */
@Data
public class EnterpriseQueryForm extends PageParam {

    @Schema(description = "关键字")
    @Length(max = 200, message = "关键字最多200字符")
    private String keywords;

    @Schema(description = "开始时间")
    private LocalDate startTime;

    @Schema(description = "结束时间")
    private LocalDate endTime;

    @Schema(description = "禁用状态")
    private Boolean disabledFlag;

    @Schema(description = "删除状态", hidden = true)
    private Boolean deletedFlag;

}
