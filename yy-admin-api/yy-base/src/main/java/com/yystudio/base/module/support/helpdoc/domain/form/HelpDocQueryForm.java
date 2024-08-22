package com.yystudio.base.module.support.helpdoc.domain.form;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 帮助文档 分页查询
 *
 * @Author yy-studio
 * @Date 2022-08-20 23:11:42
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class HelpDocQueryForm extends PageParam {

    @Schema(description = "分类")
    private Long helpDocCatalogId;

    @Schema(description = "标题")
    private String keywords;

    @Schema(description = "创建-开始时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建-截止时间")
    private LocalDate createTimeEnd;

}
