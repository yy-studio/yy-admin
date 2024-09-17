package com.yystudio.admin.module.business.law.domain.form;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReadingRecordQueryForm extends PageParam {
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 内容ID
     */
    @Schema(description = "内容ID")
    private Long contentId;

    /**
     * 阅读类型（1付费阅读，0免费阅读）
     */
    @Schema(description = "阅读类型（1付费阅读，0免费阅读）")
    private Integer readType;
}
