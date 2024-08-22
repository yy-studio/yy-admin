package com.yystudio.base.module.support.codegenerator.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 代码生成 预览 表单
 *
 * @Author yy-studio
 * @Date 2022/6/23 23:20:46
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class CodeGeneratorPreviewForm {

    @NotBlank(message = "模板文件 不能为空")
    @Schema(description = "模板文件")
    private String templateFile;

    @NotBlank(message = "表名 不能为空")
    @Schema(description = "表名")
    private String tableName;

}
