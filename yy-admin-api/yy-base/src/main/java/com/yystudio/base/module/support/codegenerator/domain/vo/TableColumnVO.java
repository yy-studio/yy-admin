
package com.yystudio.base.module.support.codegenerator.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 列
 *
 * @Author yy-studio
 * @Date 2022/9/21 21:07:58
 *
 *
 * @Copyright  远游工作室
 */

@Data
public class TableColumnVO {

    @Schema(description = "列名")
    private String columnName;

    @Schema(description = "列描述")
    private String columnComment;

    @Schema(description = "columnKey")
    private String columnKey;

    @Schema(description = "extra")
    private String extra;

    @Schema(description = "是否为空")
    private String isNullable;

    @Schema(description = "数据类型varchar")
    private String dataType;

    @Schema(description = "列类型varchar(50)")
    private String columnType;


}
