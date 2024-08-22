package com.yystudio.base.module.support.loginlog.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;

/**
 * 登录查询日志
 *
 * @Author yy-studio
 * @Date 2022/07/22 19:46:23
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class LoginLogQueryForm extends PageParam {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户类型")
    private Integer userType;

    @Schema(description = "开始日期")
    private String startDate;

    @Schema(description = "结束日期")
    private String endDate;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "ip")
    private String ip;

}
