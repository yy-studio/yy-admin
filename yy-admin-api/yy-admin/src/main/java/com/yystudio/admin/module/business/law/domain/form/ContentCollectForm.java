package com.yystudio.admin.module.business.law.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户收藏表单
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@Data
public class ContentCollectForm {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 内容ID
     */
    @Schema(description = "文章ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文章ID 不能为空")
    private Long contentId;

}