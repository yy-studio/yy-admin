package com.yystudio.admin.module.business.law.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户表 列表VO
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Data
public class UserVO {

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

    @Schema(description = "用户类型（2:个人，3:企业）")
    private Integer type;

    @Schema(description = "总积分")
    private Integer totalPoints;

    @Schema(description = "状态:1正常,0未激活,-1注销")
    private Integer status;

    @Schema(description = "1付费用户")
    private Integer paid;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}