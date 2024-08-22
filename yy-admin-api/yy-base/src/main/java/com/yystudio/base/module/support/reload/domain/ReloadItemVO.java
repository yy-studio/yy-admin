package com.yystudio.base.module.support.reload.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

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
public class ReloadItemVO {

    @Schema(description = "加载项标签")
    private String tag;

    @Schema(description = "参数")
    private String args;

    @Schema(description = "运行标识")
    private String identification;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}
