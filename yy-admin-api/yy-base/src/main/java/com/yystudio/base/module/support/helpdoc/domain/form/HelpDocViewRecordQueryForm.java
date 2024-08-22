package com.yystudio.base.module.support.helpdoc.domain.form;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 查阅记录 查询
 *
 * @Author yy-studio
 * @Date 2022-08-20 23:11:42
 *
 * 
 * @Copyright  远游工作室
 */
@Data
public class HelpDocViewRecordQueryForm extends PageParam {

    @Schema(description = "帮助文档id")
    @NotNull(message = "帮助文档id不能为空")
    private Long helpDocId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "关键字")
    private String keywords;


}
