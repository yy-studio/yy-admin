package com.yystudio.admin.module.business.oa.notice.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.admin.module.business.oa.notice.constant.NoticeVisibleRangeDataTypeEnum;
import com.yystudio.base.common.swagger.SchemaEnum;

/**
 * 新闻、公告 可见范围数据 VO
 *
 * @Author yy-studio
 * @Date 2022-08-12 21:40:39
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class NoticeVisibleRangeVO {

    @SchemaEnum(NoticeVisibleRangeDataTypeEnum.class)
    private Integer dataType;

    @Schema(description = "员工/部门id")
    private Long dataId;

    @Schema(description = "员工/部门 名称")
    private String dataName;

}
