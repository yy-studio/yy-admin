package com.yystudio.admin.module.system.login.domain;

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
public class LoginResultVO extends RequestEmployee {

    @Schema(description = "token")
    private String token;

    @Schema(description = "菜单列表")
    private List<MenuVO> menuList;

    @Schema(description = "上次登录ip")
    private String lastLoginIp;

    @Schema(description = "上次登录ip地区")
    private String lastLoginIpRegion;

    @Schema(description = "上次登录user-agent")
    private String lastLoginUserAgent;

    @Schema(description = "上次登录时间")
    private LocalDateTime lastLoginTime;

}
