package com.yystudio.admin.module.business.law.domain.form;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户表 分页查询表单
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Data
public class UserQueryForm extends PageParam{

    @Schema(description = "关键字查询")
    private String keywords;

    @Schema(description = "用户类型（2:个人，3:企业）")
    private Integer type;
}