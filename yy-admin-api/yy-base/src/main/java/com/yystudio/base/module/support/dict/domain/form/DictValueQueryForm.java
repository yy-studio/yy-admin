package com.yystudio.base.module.support.dict.domain.form;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

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
public class DictValueQueryForm extends PageParam {

    @Schema(description = "dictKeyId")
    @NotNull(message = "dictKeyId不能为空")
    private Long dictKeyId;

    @Schema(description = "搜索词")
    private String searchWord;

    @Schema(description = "删除标识",hidden = true)
    private Boolean deletedFlag;
}