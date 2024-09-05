package com.yystudio.admin.module.business.law.domain.form;

import com.yystudio.base.common.util.SmartVerificationUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * 用户表 新建表单
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Data
public class UserAddForm {

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户名 不能为空")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "密码 不能为空")
    @Pattern(regexp = SmartVerificationUtil.PWD_REGEXP, message = "密码格式不正确")
    private String password;

    @Schema(description = "手机号")
    @Pattern(regexp = SmartVerificationUtil.PHONE_REGEXP, message = "手机号格式不正确")
    private String mobile;

    @Schema(description = "邮箱")
    @Pattern(regexp = SmartVerificationUtil.EMAIL, message = "邮箱格式不正确")
    private String email;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "用户类型（2:个人，3:企业）")
    private Integer type;

    @Schema(description = "状态:1正常,0未激活,-1注销")
    private Integer status;

    @Schema(description = "1付费用户")
    private Integer paid;

    @Schema(description = "创建人ID")
    private Long createUserId;

    @Schema(description = "创建人姓名")
    private String createUserName;

}