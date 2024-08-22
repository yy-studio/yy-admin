package com.yystudio.admin.module.system.position.domain.form;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 职务表 分页查询表单
 *
 * @Author kaiyun
 * @Date 2024-06-23 23:31:38
 * @Copyright 远游工作室
 */

@Data
public class PositionQueryForm extends PageParam{

    @Schema(description = "关键字查询")
    private String keywords;

    @Schema(hidden = true)
    private Boolean deletedFlag;
}