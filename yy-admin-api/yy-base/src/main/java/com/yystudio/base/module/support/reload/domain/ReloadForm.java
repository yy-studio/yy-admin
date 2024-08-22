package com.yystudio.base.module.support.reload.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * reload (内存热加载、钩子等)
 *
 * @Author yy-studio
 * @Date 2015-03-02 19:11:52
 * 
 * 
 * @Copyright  远游工作室
 */
@Data
public class ReloadForm {

    @Schema(description = "标签")
    @NotBlank(message = "标签不能为空")
    private String tag;

    @Schema(description = "状态标识")
    @NotBlank(message = "状态标识不能为空")
    private String identification;

    @Schema(description = "参数")
    private String args;

}
