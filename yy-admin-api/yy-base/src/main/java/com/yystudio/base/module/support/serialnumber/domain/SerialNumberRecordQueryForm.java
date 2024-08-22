package com.yystudio.base.module.support.serialnumber.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;

import javax.validation.constraints.NotNull;

/**
 * 单据序列号 生成记录 查询
 *
 * @Author yy-studio
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class SerialNumberRecordQueryForm extends PageParam {

    @Schema(description = "单号id")
    @NotNull(message = "单号id不能为空")
    private Integer serialNumberId;
}
