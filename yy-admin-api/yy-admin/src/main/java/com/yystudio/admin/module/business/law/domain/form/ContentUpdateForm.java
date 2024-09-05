package com.yystudio.admin.module.business.law.domain.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yystudio.base.common.json.deserializer.DictValueVoDeserializer;
import com.yystudio.base.common.json.deserializer.FileKeyVoDeserializer;
import com.yystudio.base.common.json.serializer.FileKeyVoSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 内容表 更新表单
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@Data
public class ContentUpdateForm {

    @Schema(description = "内容ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "内容ID 不能为空")
    private Long id;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "标题 不能为空")
    private String title;

    @Schema(description = "副标题")
    private String subtitle;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "类型 不能为空")
    private Integer type;

    @Schema(description = "分类ID")
    @JsonDeserialize(using = DictValueVoDeserializer.class)
    private String categoryId;

    @Schema(description = "封面图片")
    @JsonSerialize(using = FileKeyVoSerializer.class)
    @JsonDeserialize(using = FileKeyVoDeserializer.class)
    private String coverImage;

    @Schema(description = "内容正文", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "内容正文 不能为空")
    private String content;

    @Schema(description = "标签")
    private String tags;

}