package com.yystudio.admin.module.business.oa.notice.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 通知公告 更新表单
 *
 * @Author yy-studio
 * @Date 2022-08-12 21:40:39
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class NoticeUpdateForm extends NoticeAddForm {

    @Schema(description = "id")
    @NotNull(message = "通知id不能为空")
    private Long noticeId;

}
