package com.yystudio.admin.module.business.goods.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商品 更新表单
 *
 * @Author yy-studio
 * @Date 2021-10-25 20:26:54
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class GoodsUpdateForm extends GoodsAddForm {

    @Schema(description = "商品id")
    @NotNull(message = "商品id不能为空")
    private Long goodsId;
}
