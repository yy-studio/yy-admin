package com.yystudio.base.module.support.codegenerator.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;


/**
 * 查询表数据
 *
 * @Author yy-studio
 * @Date 2022-06-30 22:15:38
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class TableQueryForm extends PageParam {

    @Schema(description = "表名关键字")
    private String tableNameKeywords;

}
