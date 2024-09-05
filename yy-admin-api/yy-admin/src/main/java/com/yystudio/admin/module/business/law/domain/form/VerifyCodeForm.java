package com.yystudio.admin.module.business.law.domain.form;

import com.yystudio.base.common.util.SmartVerificationUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * 用户表 新建表单
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Data
public class VerifyCodeForm {

    @Schema(description = "手机号")
    @Pattern(regexp = SmartVerificationUtil.PHONE_REGEXP, message = "手机号格式不正确")
    private String mobile;

    @Schema(description = "邮箱")
    @Pattern(regexp = SmartVerificationUtil.EMAIL, message = "邮箱格式不正确")
    private String email;

    @Schema(description = "验证码类型(1手机2邮箱)")
    private Integer registerType;


    @Schema(description = "验证码")
    private String code;
}