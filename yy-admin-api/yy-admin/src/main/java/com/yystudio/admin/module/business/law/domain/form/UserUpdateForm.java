package com.yystudio.admin.module.business.law.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户表 更新表单
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Data
public class UserUpdateForm {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户ID 不能为空")
    private Long id;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户名 不能为空")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "微信openid")
    private String wechatOpenid;

    @Schema(description = "微博uid")
    private String weiboUid;

    @Schema(description = "用户类型（2:个人，3:企业）")
    private Integer type;

    @Schema(description = "总积分")
    private Integer totalPoints;

    @Schema(description = "状态:1正常,0未激活,-1注销")
    private Integer status;

    @Schema(description = "1付费用户")
    private Integer paid;

    @Schema(description = "修改人ID")
    private Long updateUserId;

    @Schema(description = "修改人姓名")
    private String updateUserName;

}