package com.yystudio.admin.module.business.law.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.yystudio.admin.module.business.law.constant.UserStatusEnum;
import com.yystudio.admin.module.business.law.constant.YesNoEnum;
import com.yystudio.admin.module.business.law.domain.RequestAppUser;
import com.yystudio.admin.module.business.law.domain.form.*;
import com.yystudio.admin.module.business.law.domain.vo.AppUserLoginResultVO;
import com.yystudio.admin.module.business.law.domain.vo.ContentCollectVO;
import com.yystudio.admin.module.business.law.domain.vo.ReadingRecordVO;
import com.yystudio.admin.module.business.law.service.AppUserLoginService;
import com.yystudio.admin.module.business.law.service.ContentCollectService;
import com.yystudio.admin.module.business.law.service.ReadingRecordService;
import com.yystudio.admin.module.business.law.service.UserService;
import com.yystudio.admin.module.system.employee.service.EmployeeService;
import com.yystudio.base.common.annoation.NoNeedLogin;
import com.yystudio.base.common.constant.RequestHeaderConst;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.enumeration.UserTypeEnum;
import com.yystudio.base.common.util.SmartRequestUtil;
import com.yystudio.base.constant.RedisKeyConst;
import com.yystudio.base.module.support.file.domain.vo.FileUploadVO;
import com.yystudio.base.module.support.file.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * APP用户接口 Controller
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@RestController
@Tag(name = "APP用户接口")
public class AppUserController {

    @Resource
    private UserService userService;

    @Resource
    private AppUserLoginService appUserLoginService;

    @Resource
    private FileService fileService;

    @Resource
    private ReadingRecordService readingRecordService;

    @Resource
    private ContentCollectService contentCollectService;

    @Operation(summary = "注册 @author yy")
    @PostMapping("/app/user/register")
    @NoNeedLogin
    public ResponseDTO<String> register(@RequestBody @Valid UserAddForm addForm) {

        addForm.setPaid(YesNoEnum.NO.getValue());
        addForm.setStatus(UserStatusEnum.UN_ACTIVE.getValue());
        addForm.setType(UserTypeEnum.USER_PERSONAL.getValue());
        addForm.setPassword(EmployeeService.getEncryptPwd(addForm.getPassword()));
        return userService.add(addForm);
    }

    @Operation(summary = "注册时发送验证码 @author yy")
    @PostMapping("/app/verifyCode")
    @NoNeedLogin
    public ResponseDTO<String> sendRegisterCode(@RequestBody @Valid VerifyCodeForm codeForm) {

        return appUserLoginService.sendCode(codeForm, RedisKeyConst.Support.SMS_REGISTER);
    }

    @Operation(summary = "提交验证码 @author yy")
    @PostMapping("/app/register/verify")
    @NoNeedLogin
    public ResponseDTO<String> verifyCode(@RequestBody @Valid VerifyCodeForm codeForm) {

        return appUserLoginService.verifyCode(codeForm);
    }


    @Operation(summary = "登录时发送手机验证码 @author yy")
    @GetMapping("/app/verifyCode/login/{mobile}")
    @NoNeedLogin
    public ResponseDTO<String> sendLoginCode(@PathVariable String mobile) {
        if(StringUtils.isBlank(mobile)){
            return ResponseDTO.userErrorParam("手机号不能为空");
        }

        VerifyCodeForm codeForm = new VerifyCodeForm();
        codeForm.setMobile(mobile);
        codeForm.setRegisterType(1);

        return appUserLoginService.sendCode(codeForm, RedisKeyConst.Support.SMS_LOGIN);
    }

    @NoNeedLogin
    @PostMapping("/app/user/login")
    @Operation(summary = "用户密码登录 @author yy")
    public ResponseDTO<AppUserLoginResultVO> login(@Valid @RequestBody AppUserLoginForm loginForm, HttpServletRequest request) {
        String ip = ServletUtil.getClientIP(request);
        String userAgent = ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT);
        return appUserLoginService.login(loginForm, ip, userAgent);
    }

    @NoNeedLogin
    @PostMapping("/app/user/loginByVerify")
    @Operation(summary = "用户验证码登录 @author yy")
    public ResponseDTO<AppUserLoginResultVO> loginByVerify(@Valid @RequestBody CodeForm loginForm, HttpServletRequest request) {
        String ip = ServletUtil.getClientIP(request);
        String userAgent = ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT);
        return appUserLoginService.loginByVerify(loginForm, ip, userAgent);
    }

    @GetMapping("/app/login/getLoginInfo")
    @Operation(summary = "获取登录结果信息  @author yy")
    public ResponseDTO<AppUserLoginResultVO> getLoginInfo() {
        RequestAppUser requestAppUser = (RequestAppUser)SmartRequestUtil.getRequestUser();
        AppUserLoginResultVO loginResult = appUserLoginService.getLoginResult(requestAppUser);
        String tokenValue = StpUtil.getTokenValue();
        loginResult.setToken(tokenValue);
        return ResponseDTO.ok(loginResult);
    }

    @Operation(summary = "文件上传 @author 胡克")
    @PostMapping("/app/file/upload")
    public ResponseDTO<FileUploadVO> upload(@RequestParam MultipartFile file, @RequestParam Integer folder) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        ResponseDTO<FileUploadVO> fileRes = fileService.fileUpload(file, folder, requestUser);
        userService.updateAvatar(requestUser.getUserId(), fileRes.getData().getFileUrl());

        return fileRes;
    }

    @Operation(summary = "阅读记录 @author yy")
    @PostMapping("/app/readingRecord")
    public ResponseDTO<PageResult<ReadingRecordVO>> queryPage(@RequestBody @Valid ReadingRecordQueryForm queryForm) {
        RequestAppUser requestAppUser = (RequestAppUser)SmartRequestUtil.getRequestUser();
        queryForm.setUserId(requestAppUser.getUserId());
        return ResponseDTO.ok(readingRecordService.queryPage(queryForm));
    }

    @Operation(summary = "收藏记录")
    @PostMapping("/app/collectRecord")
    public ResponseDTO<PageResult<ContentCollectVO>> queryCollectPage(@RequestBody @Valid CollectRecordQueryForm queryForm) {
        RequestAppUser requestAppUser = (RequestAppUser)SmartRequestUtil.getRequestUser();
        queryForm.setUserId(requestAppUser.getUserId());
        return ResponseDTO.ok(contentCollectService.queryCollectPage(queryForm));
    }
}
