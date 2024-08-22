package com.yystudio.base.module.support.helpdoc.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 帮助文档 目录
 *
 * @Author yy-studio
 * @Date 2022-08-20 23:11:42
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class HelpDocCatalogAddForm {

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 200, message = "名称最多200字符")
    private String name;

    @Schema(description = "父级")
    private Long parentId;

    @Schema(description = "排序")
    private Integer sort;
}
