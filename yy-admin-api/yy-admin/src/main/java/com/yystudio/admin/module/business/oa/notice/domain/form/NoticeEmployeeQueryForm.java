package com.yystudio.admin.module.business.oa.notice.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;

import java.time.LocalDate;

/**
 * 通知公告 员工查询表单
 *
 * @Author yy-studio
 * @Date 2022-08-12 21:40:39
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class NoticeEmployeeQueryForm extends PageParam {

    @Schema(description = "标题、作者、来源、文号")
    private String keywords;

    @Schema(description = "分类")
    private Long noticeTypeId;

    @Schema(description = "发布-开始时间")
    private LocalDate publishTimeBegin;

    @Schema(description = "未读标识")
    private Boolean notViewFlag;

    @Schema(description = "发布-截止时间")
    private LocalDate publishTimeEnd;
}
