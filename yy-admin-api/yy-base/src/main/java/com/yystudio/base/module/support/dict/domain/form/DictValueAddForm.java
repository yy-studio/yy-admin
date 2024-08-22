package com.yystudio.base.module.support.dict.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
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
public class DictValueAddForm {

    @Schema(description = "dictKeyId")
    @NotNull(message = "dictKeyId不能为空")
    private Long dictKeyId;

    @Schema(description = "编码")
    @NotBlank(message = "编码不能为空")
    @Length(max = 50,message = "编码太长了，不能超过50字符")
    private String valueCode;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 50,message = "名称太长了，不能超过50字符")
    private String valueName;

    @Schema(description = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "备注")
    @Length(max = 500,message = "备注太长了")
    private String remark;

}