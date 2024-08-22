package com.yystudio.base.module.support.dict.domain.form;

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
public class DictValueUpdateForm extends DictValueAddForm {

    @Schema(description = "valueId")
    @NotNull(message = "valueId不能为空")
    private Long dictValueId;
}