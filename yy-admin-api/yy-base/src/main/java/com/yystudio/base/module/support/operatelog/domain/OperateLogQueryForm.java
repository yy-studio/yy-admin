package com.yystudio.base.module.support.operatelog.domain;

import com.yystudio.base.common.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *  操作日志查询 表单
 *
 * @Author yy-studio
 * @Date 2021-12-08 20:48:52
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class OperateLogQueryForm extends PageParam {

    @Schema(description = "用户ID")
    private Long operateUserId;

    @Schema(description = "用户类型")
    private Integer operateUserType;

    @Schema(description = "开始日期")
    private String startDate;

    @Schema(description = "结束日期")
    private String endDate;


    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "请求结果 false失败 true成功")
    private Boolean successFlag;

}
