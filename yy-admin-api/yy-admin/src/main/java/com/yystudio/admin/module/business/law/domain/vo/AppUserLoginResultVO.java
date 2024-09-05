package com.yystudio.admin.module.business.law.domain.vo;

import com.yystudio.admin.module.business.law.domain.RequestAppUser;
import com.yystudio.admin.module.system.login.domain.RequestEmployee;
import com.yystudio.admin.module.system.menu.domain.vo.MenuVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录结果信息
 *
 * @Author yy-studio
 * @Date 2021-12-19 11:49:45
 *
 *
 * @Copyright  远游工作室
 */
@Data
public class AppUserLoginResultVO extends RequestAppUser {

    @Schema(description = "token")
    private String token;

    @Schema(description = "上次登录ip")
    private String lastLoginIp;

    @Schema(description = "上次登录ip地区")
    private String lastLoginIpRegion;

    @Schema(description = "上次登录user-agent")
    private String lastLoginUserAgent;

    @Schema(description = "上次登录时间")
    private LocalDateTime lastLoginTime;

}
