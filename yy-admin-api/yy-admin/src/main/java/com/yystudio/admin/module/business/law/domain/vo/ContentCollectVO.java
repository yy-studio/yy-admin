package com.yystudio.admin.module.business.law.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yystudio.base.common.json.deserializer.FileKeyVoDeserializer;
import com.yystudio.base.common.json.serializer.DictValueVoSerializer;
import com.yystudio.base.common.json.serializer.FileKeyVoSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContentCollectVO {
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    private String username;

    /**
     * 内容ID
     */
    private Long contentId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "副标题")
    private String subtitle;

    @Schema(description = "类型（0:免费，1:付费）")
    private Integer type;

    @Schema(description = "分类ID CONTENT_CATEGORY")
    @JsonSerialize(using = DictValueVoSerializer.class)
    private String categoryId;

    @Schema(description = "封面图片")
    @JsonSerialize(using = FileKeyVoSerializer.class)
    @JsonDeserialize(using = FileKeyVoDeserializer.class)
    private String coverImage;

    @Schema(description = "标签")
    private String tags;
}
