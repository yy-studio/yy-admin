package com.yystudio.admin.module.system.datascope.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 数据范围
 *
 * @Author yy-studio
 * @Date 2020/11/28  20:59:17
 *
 *
 * @Copyright  远游工作室
 */
@Data
@Builder
public class DataScopeDTO {

    @Schema(description = "数据范围类型")
    private Integer dataScopeType;

    @Schema(description = "数据范围名称")
    private String dataScopeTypeName;

    @Schema(description = "描述")
    private String dataScopeTypeDesc;

    @Schema(description = "顺序")
    private Integer dataScopeTypeSort;

}
