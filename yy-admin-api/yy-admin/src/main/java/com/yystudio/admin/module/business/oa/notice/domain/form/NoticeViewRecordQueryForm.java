package com.yystudio.admin.module.business.oa.notice.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;

import javax.validation.constraints.NotNull;

/**
 * 通知公告 阅读记录查询
 *
 * @Author yy-studio
 * @Date 2022-08-12 21:40:39
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class NoticeViewRecordQueryForm extends PageParam {

    @Schema(description = "通知公告id")
    @NotNull(message = "通知公告id不能为空")
    private Long noticeId;

    @Schema(description = "部门id")
    private Long departmentId;

    @Schema(description = "关键字")
    private String keywords;


}
