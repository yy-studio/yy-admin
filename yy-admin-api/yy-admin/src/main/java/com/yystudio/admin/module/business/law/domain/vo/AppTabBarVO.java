package com.yystudio.admin.module.business.law.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * APP tabbar标题
 *
 * @Author yy-studio
 * @Date 2021-12-19 11:49:45
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class AppTabBarVO {

    @Schema(description = "id")
    private String id;

    @Schema(description = "name")
    private String name;

}
