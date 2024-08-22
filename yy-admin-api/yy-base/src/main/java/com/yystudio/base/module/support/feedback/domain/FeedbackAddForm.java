package com.yystudio.base.module.support.feedback.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.json.deserializer.FileKeyVoDeserializer;
import com.yystudio.base.common.json.serializer.FileKeyVoSerializer;

import javax.validation.constraints.NotBlank;

/**
 * 意见反馈 添加表单
 *
 * @Author yy-studio
 * @Date 2022-08-11 20:48:09
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class FeedbackAddForm {

    @Schema(description = "反馈内容")
    @NotBlank(message = "反馈内容不能为空")
    private String feedbackContent;

    @Schema(description = "反馈图片")
    @JsonSerialize(using = FileKeyVoSerializer.class)
    @JsonDeserialize(using = FileKeyVoDeserializer.class)
    private String feedbackAttachment;

}
