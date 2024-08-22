package com.yystudio.admin.module.business.category.domain.form;

import com.yystudio.admin.module.business.category.constant.CategoryTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.swagger.SchemaEnum;

/**
 * 类目 层级树查询
 *
 * @Author yy-studio
 * @Date 2021/08/05 21:26:58
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class CategoryTreeQueryForm {

    @SchemaEnum(desc = "分类类型|可选", value = CategoryTypeEnum.class)
    private Integer categoryType;

    @Schema(description = "父级类目id|可选")
    private Long parentId;
}
