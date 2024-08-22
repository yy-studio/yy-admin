package com.yystudio.admin.module.system.datascope.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

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
public class DataScopeAndViewTypeVO {

    @Schema(description = "数据范围类型")
    private Integer dataScopeType;

    @Schema(description = "数据范围名称")
    private String dataScopeTypeName;

    @Schema(description = "描述")
    private String dataScopeTypeDesc;

    @Schema(description = "顺序")
    private Integer dataScopeTypeSort;

    @Schema(description = "可见范围列表")
    private List<DataScopeViewTypeVO> viewTypeList;

}
