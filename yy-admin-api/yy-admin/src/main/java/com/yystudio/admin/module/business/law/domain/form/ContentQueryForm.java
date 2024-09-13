package com.yystudio.admin.module.business.law.domain.form;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * 内容表 分页查询表单
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@Data
public class ContentQueryForm extends PageParam{

    @Schema(description = "关键字查询")
    private String keywords;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "创建时间")
    private LocalDate createTimeBegin;

    @Schema(description = "创建时间")
    private LocalDate createTimeEnd;

    @Schema(description = "分类")
    private String categoryId;

    @Schema(description = "状态（1:发布，0:草稿）")
    private Integer status;

}