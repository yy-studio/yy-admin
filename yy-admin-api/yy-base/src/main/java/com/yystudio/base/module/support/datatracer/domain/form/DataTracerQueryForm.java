package com.yystudio.base.module.support.datatracer.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;
import com.yystudio.base.common.swagger.SchemaEnum;
import com.yystudio.base.module.support.datatracer.constant.DataTracerTypeEnum;

import javax.validation.constraints.NotNull;

/**
 * 查询表单
 *
 * @Author yy-studio
 * @Date 2022-07-23 19:38:52
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class DataTracerQueryForm extends PageParam {

    @SchemaEnum(DataTracerTypeEnum.class)
    private Integer type;

    @Schema(description = "业务id")
    @NotNull(message = "业务id不能为空")
    private Long dataId;

    @Schema(description = "关键字")
    private String keywords;
}
