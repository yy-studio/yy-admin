package com.yystudio.admin.module.business.law.domain;

import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.enumeration.GenderEnum;
import com.yystudio.base.common.enumeration.UserTypeEnum;
import com.yystudio.base.common.swagger.SchemaEnum;
import com.yystudio.base.common.util.SmartEnumUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 请求APP用户登录信息
 *
 * @Author yy-studio
 * @Date 2021/8/4 21:15
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class RequestAppUser implements RequestUser {

    @Schema(description = "用户类型（2:个人，3:企业）")
    private Integer type;

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "用户名")
    private String username;

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


    @Schema(description = "总积分")
    private Integer totalPoints;

    @Schema(description = "状态:1正常,0未激活,-1注销")
    private Integer status;

    @Schema(description = "1付费用户")
    private Integer paid;

    @Schema(description = "请求ip")
    private String ip;

    @Schema(description = "请求user-agent")
    private String userAgent;

    @Schema(description = "登录类型")
    private String loginDevice;

    @Override
    public Long getUserId() {
        return id;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public UserTypeEnum getUserType() {
        return SmartEnumUtil.getEnumByValue(type, UserTypeEnum.class);
    }
}
