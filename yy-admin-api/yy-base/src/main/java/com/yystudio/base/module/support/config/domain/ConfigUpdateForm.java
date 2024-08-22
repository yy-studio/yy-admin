package com.yystudio.base.module.support.config.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 配置更新表单
 *
 * @Author yy-studio
 * @Date 2022-03-14 20:46:27
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class ConfigUpdateForm extends ConfigAddForm {

    @Schema(description = "configId")
    @NotNull(message = "configId不能为空")
    private Long configId;
}
