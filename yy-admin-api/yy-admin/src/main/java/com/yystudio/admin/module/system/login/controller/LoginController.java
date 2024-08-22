package com.yystudio.admin.module.system.login.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.yystudio.admin.constant.AdminSwaggerTagConst;
import com.yystudio.admin.module.system.login.domain.LoginForm;
import com.yystudio.admin.module.system.login.service.LoginService;
import com.yystudio.admin.util.AdminRequestUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.admin.module.system.login.domain.LoginResultVO;
import com.yystudio.base.common.annoation.NoNeedLogin;
import com.yystudio.base.common.constant.RequestHeaderConst;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartRequestUtil;
import com.yystudio.base.module.support.captcha.domain.CaptchaVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 员工登录
 *
 * @Author yy-studio
 * @Date 2021-12-15 21:05:46
 *
 *
 * @Copyright  远游工作室
 */
@RestController
@Tag(name = AdminSwaggerTagConst.System.SYSTEM_LOGIN)
public class LoginController {

    @Resource
    private LoginService loginService;

    @NoNeedLogin
    @PostMapping("/login")
    @Operation(summary = "登录 @author 卓大")
    public ResponseDTO<LoginResultVO> login(@Valid @RequestBody LoginForm loginForm, HttpServletRequest request) {
        String ip = ServletUtil.getClientIP(request);
        String userAgent = ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT);
        return loginService.login(loginForm, ip, userAgent);
    }

    @GetMapping("/login/getLoginInfo")
    @Operation(summary = "获取登录结果信息  @author 卓大")
    public ResponseDTO<LoginResultVO> getLoginInfo() {
        LoginResultVO loginResult = loginService.getLoginResult(AdminRequestUtil.getRequestUser());
        String tokenValue = StpUtil.getTokenValue();
        loginResult.setToken(tokenValue);
        return ResponseDTO.ok(loginResult);
    }

    @Operation(summary = "退出登陆  @author 卓大")
    @GetMapping("/login/logout")
    public ResponseDTO<String> logout(@RequestHeader(value = RequestHeaderConst.TOKEN, required = false) String token) {
        return loginService.logout(token, SmartRequestUtil.getRequestUser());
    }

    @Operation(summary = "获取验证码  @author 卓大")
    @GetMapping("/login/getCaptcha")
    @NoNeedLogin
    public ResponseDTO<CaptchaVO> getCaptcha() {
        return loginService.getCaptcha();
    }

}
