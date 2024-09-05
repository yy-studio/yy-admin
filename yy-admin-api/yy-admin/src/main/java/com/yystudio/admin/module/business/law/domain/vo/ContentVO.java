package com.yystudio.admin.module.business.law.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yystudio.base.common.json.deserializer.FileKeyVoDeserializer;
import com.yystudio.base.common.json.serializer.DictValueVoSerializer;
import com.yystudio.base.common.json.serializer.FileKeyVoSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 内容表 列表VO
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@Data
public class ContentVO {

    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "副标题")
    private String subtitle;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "分类ID CONTENT_CATEGORY")
    @JsonSerialize(using = DictValueVoSerializer.class)
    private String categoryId;

    @Schema(description = "发布者ID")
    private Long userId;

    @Schema(description = "封面图片")
    @JsonSerialize(using = FileKeyVoSerializer.class)
    @JsonDeserialize(using = FileKeyVoDeserializer.class)
    private String coverImage;

    @Schema(description = "内容正文")
    private String content;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "阅读量")
    private Integer views;

    @Schema(description = "点赞量")
    private Integer likes;

    @Schema(description = "收藏量")
    private Integer collections;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}