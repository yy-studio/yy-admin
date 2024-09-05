package com.yystudio.admin.module.business.law.domain.form;

import com.yystudio.base.common.swagger.SchemaEnum;
import com.yystudio.base.common.util.SmartVerificationUtil;
import com.yystudio.base.common.validator.enumeration.CheckEnum;
import com.yystudio.base.constant.LoginDeviceEnum;
import com.yystudio.base.module.support.captcha.domain.CaptchaForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 员工登录
 *
 * @Author yy-studio
 * @Date 2021-12-19 11:49:45
 *
 * 
 * @Copyright  远游工作室
 */
@Data
public class AppUserLoginForm {

    @Schema(description = "手机号")
    @Pattern(regexp = SmartVerificationUtil.PHONE_REGEXP, message = "手机号格式不正确")
    private String mobile;

    @Schema(description = "邮箱")
    @Pattern(regexp = SmartVerificationUtil.EMAIL, message = "邮箱格式不正确")
    private String email;

    @Schema(description = "登录方式:1验证码登录、2密码登录")
    private Integer loginType;

    @Schema(description = "密码")
    private String password;

    @SchemaEnum(desc = "登录终端", value = LoginDeviceEnum.class)
    @CheckEnum(value = LoginDeviceEnum.class, required = true, message = "此终端不允许登录")
    private Integer loginDevice;
}
