package com.yystudio.base.module.support.dict.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典
 *
 * @Author yy-studio
 * @Date 2022/5/26 19:40:55
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class DictKeyVO {

    @Schema(description = "dictKeyId")
    private Long dictKeyId;

    @Schema(description = "编码")
    private String keyCode;

    @Schema(description = "名称")
    private String keyName;

    @Schema(description = "备注")
    private String remark;
}