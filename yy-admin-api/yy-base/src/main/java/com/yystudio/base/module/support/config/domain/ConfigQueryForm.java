package com.yystudio.base.module.support.config.domain;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 分页查询 系统配置
 *
 * @Author yy-studio
 * @Date 2022-03-14 20:46:27
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class ConfigQueryForm extends PageParam {

    @Schema(description = "参数KEY")
    @Length(max = 50, message = "参数Key最多50字符")
    private String configKey;
}
