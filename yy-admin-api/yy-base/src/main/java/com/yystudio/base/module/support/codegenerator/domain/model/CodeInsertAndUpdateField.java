package com.yystudio.base.module.support.codegenerator.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.swagger.SchemaEnum;
import com.yystudio.base.common.validator.enumeration.CheckEnum;
import com.yystudio.base.module.support.codegenerator.constant.CodeFrontComponentEnum;
import com.yystudio.base.module.support.codegenerator.constant.CodeGeneratorPageTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 代码生成 增加、修改的字段 模型
 *
 * @Author yy-studio
 * @Date 2022-06-30 22:15:38
 *
 *
 * @Copyright  远游工作室
 */

@Data
public class CodeInsertAndUpdateField {

    @NotBlank(message = "3.增加、修改 列名 不能为空")
    @Schema(description = "列名")
    private String columnName;

    @NotNull(message = "3.增加、修改  必须 不能为空")
    @Schema(description = "必须")
    private Boolean requiredFlag;

    @NotNull(message = "3.增加、修改  插入标识 不能为空")
    @Schema(description = "插入标识")
    private Boolean insertFlag;

    @NotNull(message = "3.增加、修改  更新标识 不能为空")
    @Schema(description = "更新标识")
    private Boolean updateFlag;

    @SchemaEnum(value = CodeGeneratorPageTypeEnum.class)
    @CheckEnum(value = CodeFrontComponentEnum.class, message = "3.增加、修改  增加、修改 组件类型 枚举值错误", required = true)
    private String frontComponent;

}
