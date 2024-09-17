package com.yystudio.admin.module.business.law.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.yystudio.admin.module.business.law.constant.LawConst;
import com.yystudio.admin.module.business.law.constant.UserStatusEnum;
import com.yystudio.admin.module.business.law.dao.UserDao;
import com.yystudio.admin.module.business.law.domain.RequestAppUser;
import com.yystudio.admin.module.business.law.domain.entity.UserEntity;
import com.yystudio.admin.module.business.law.domain.form.AppUserLoginForm;
import com.yystudio.admin.module.business.law.domain.form.CodeForm;
import com.yystudio.admin.module.business.law.domain.form.VerifyCodeForm;
import com.yystudio.admin.module.business.law.domain.vo.AppUserLoginResultVO;
import com.yystudio.admin.module.system.employee.domain.entity.EmployeeEntity;
import com.yystudio.admin.module.system.employee.service.EmployeeService;
import com.yystudio.admin.module.system.login.domain.LoginResultVO;
import com.yystudio.admin.module.system.login.domain.RequestEmployee;
import com.yystudio.base.common.code.UserErrorCode;
import com.yystudio.base.common.constant.RequestHeaderConst;
import com.yystudio.base.common.constant.StringConst;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.enumeration.UserTypeEnum;
import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.common.util.SmartEnumUtil;
import com.yystudio.base.common.util.SmartIpUtil;
import com.yystudio.base.constant.LoginDeviceEnum;
import com.yystudio.base.constant.RedisKeyConst;
import com.yystudio.base.module.support.loginlog.LoginLogResultEnum;
import com.yystudio.base.module.support.loginlog.LoginLogService;
import com.yystudio.base.module.support.loginlog.domain.LoginLogEntity;
import com.yystudio.base.module.support.loginlog.domain.LoginLogVO;
import com.yystudio.base.module.support.redis.RedisService;
import com.yystudio.base.module.support.sms.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentMap;

/**
 * APP用户登录 Service
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Slf4j
@Service
public class AppUserLoginService {


    /**
     * 最大在线缓存人数
     */
    private static final long CACHE_MAX_ONLINE_PERSON_COUNT = 1000L;

    /**
     * 登录信息二级缓存
     */
    private final ConcurrentMap<Long, RequestAppUser> loginUserCache = new ConcurrentLinkedHashMap.Builder<Long, RequestAppUser>().maximumWeightedCapacity(CACHE_MAX_ONLINE_PERSON_COUNT).build();


    @Resource
    private UserDao userDao;

    @Resource
    private SmsService smsService;

    @Resource
    private RedisService redisService;

    @Resource
    private LoginLogService loginLogService;

    /**
     * 发送手机验证码
     */
    public ResponseDTO<String> sendCode(VerifyCodeForm codeForm, String preKey) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        boolean res = false;
        String redisKey = "";
        if(codeForm.getRegisterType() == 1){
            // 手机验证码
            res = smsService.sendVerificationCode(codeForm.getMobile(), code);
            redisKey = preKey + codeForm.getMobile();
        } else if(codeForm.getRegisterType() == 2) {
            // TODO 发送邮箱验证码
            redisKey = RedisKeyConst.Support.EMAIL_REGISTER + codeForm.getEmail();
            res = true;
        }
        if(res){
            // 保存验证码
            log.error("key:{},验证码：{}", redisKey, code);
            redisService.set(redisKey, code, 300);
        }
        return res ? ResponseDTO.ok("验证码发送成功") : ResponseDTO.error(UserErrorCode.CODE_SEND_ERROR);
    }

    /**
     * 注册时验证验证码，激活用户
     */
    public ResponseDTO<String> verifyCode(VerifyCodeForm codeForm) {

        String redisKey = "";
        UserEntity userEntity = null;
        if(codeForm.getRegisterType() == 1){
            redisKey = RedisKeyConst.Support.SMS_REGISTER + codeForm.getMobile();
            userEntity = userDao.getByMobile(codeForm.getMobile(), null);
        } else if(codeForm.getRegisterType() == 2) {
            redisKey = RedisKeyConst.Support.EMAIL_REGISTER + codeForm.getEmail();
            userEntity = userDao.getByEmail(codeForm.getEmail(), null);
        } else {
            return ResponseDTO.error(UserErrorCode.CODE_VERIFY_ERROR);
        }
        if (userEntity == null){
            return ResponseDTO.error(UserErrorCode.USER_NOT_EXIST);
        }
        String code = redisService.get(redisKey);
        if (codeForm.getCode().equals(code)){
            userDao.updateStatus(userEntity.getId(), UserStatusEnum.NORMAL.getValue());
            return ResponseDTO.ok("验证成功");
        }
        return ResponseDTO.error(UserErrorCode.CODE_VERIFY_ERROR);
    }

    // 用户密码登录
    public ResponseDTO<AppUserLoginResultVO> login(AppUserLoginForm loginForm, String ip, String userAgent) {

        LoginDeviceEnum loginDeviceEnum = SmartEnumUtil.getEnumByValue(loginForm.getLoginDevice(), LoginDeviceEnum.class);
        if (loginDeviceEnum == null) {
            return ResponseDTO.userErrorParam("登录设备暂不支持！");
        }
        if(StringUtils.isBlank(loginForm.getMobile()) && StringUtils.isBlank(loginForm.getEmail())){
            return ResponseDTO.userErrorParam("手机号/邮箱不能为空");
        }
        UserEntity userEntity = null;
        if(StringUtils.isNotBlank(loginForm.getMobile())){
            userEntity = userDao.getByMobile(loginForm.getMobile(), null);
        }
        if(StringUtils.isNotBlank(loginForm.getEmail())){
            userEntity = userDao.getByEmail(loginForm.getEmail(), null);
        }

        if (userEntity == null){
            return ResponseDTO.error(UserErrorCode.USER_NOT_EXIST);
        }
        if (userEntity.getStatus().equals(UserStatusEnum.UN_ACTIVE.getValue())){
            // 记录登录失败
            saveLoginLog(userEntity, ip, userAgent, "用户未激活", LoginLogResultEnum.LOGIN_FAIL);
            return ResponseDTO.error(UserErrorCode.USER_UN_ACTIVE);
        }
        if (userEntity.getStatus().equals(UserStatusEnum.CANCEL.getValue())){
            // 记录登录失败
            saveLoginLog(userEntity, ip, userAgent, "用户已注销", LoginLogResultEnum.LOGIN_FAIL);
            return ResponseDTO.error(UserErrorCode.USER_STATUS_ERROR);
        }
        if (loginForm.getLoginType() == 1){
            String code = redisService.get(RedisKeyConst.Support.SMS_LOGIN + loginForm.getMobile());
            if (code == null || !code.equals(loginForm.getPassword())){
                // 记录登录失败
                saveLoginLog(userEntity, ip, userAgent, "手机验证码错误", LoginLogResultEnum.LOGIN_FAIL);
                return ResponseDTO.error(UserErrorCode.CODE_VERIFY_ERROR);
            }
        } else {
            if(EmployeeService.getEncryptPwd(loginForm.getPassword()).equals(EmployeeService.getEncryptPwd(LawConst.DEFAULT_PASSWORD))){
                // 初始密码登录，跳转到修改密码页面
                return ResponseDTO.error(UserErrorCode.PASSWORD_ERROR);
            }
            if (!userEntity.getPassword().equals(EmployeeService.getEncryptPwd(loginForm.getPassword()))){
                // 记录登录失败
                saveLoginLog(userEntity, ip, userAgent, "密码错误", LoginLogResultEnum.LOGIN_FAIL);
                return ResponseDTO.error(UserErrorCode.PASSWORD_ERROR);
            }
        }

        AppUserLoginResultVO loginResultVO = getLoginInfo(userEntity, ip, userAgent, loginDeviceEnum.getDesc());
        return ResponseDTO.ok(loginResultVO);
    }

    // 用户验证码登录
    public ResponseDTO<AppUserLoginResultVO> loginByVerify(CodeForm loginForm, String ip, String userAgent) {

        UserEntity userEntity = null;
        if(StringUtils.isNotBlank(loginForm.getMobile())){
            userEntity = userDao.getByMobile(loginForm.getMobile(), null);
        }

        if (userEntity == null){
            return ResponseDTO.error(UserErrorCode.USER_NOT_EXIST);
        }
        if (userEntity.getStatus().equals(UserStatusEnum.CANCEL.getValue())){
            // 记录登录失败
            saveLoginLog(userEntity, ip, userAgent, "用户已注销", LoginLogResultEnum.LOGIN_FAIL);
            return ResponseDTO.error(UserErrorCode.USER_STATUS_ERROR);
        }

        if (userEntity.getStatus().equals(UserStatusEnum.UN_ACTIVE.getValue())){
            // 手机号验证码登录时，如果用户未激活，直接激活

        }
        String code = redisService.get(RedisKeyConst.Support.SMS_LOGIN + loginForm.getMobile());
        if (code == null ){
            // 记录登录失败
            saveLoginLog(userEntity, ip, userAgent, UserErrorCode.CODE_VERIFY_TIMEOUT_ERROR.getMsg(), LoginLogResultEnum.LOGIN_FAIL);
            return ResponseDTO.error(UserErrorCode.CODE_VERIFY_TIMEOUT_ERROR);
        }
        if (!code.equals(loginForm.getCode())){
            // 记录登录失败
            saveLoginLog(userEntity, ip, userAgent, "手机验证码错误", LoginLogResultEnum.LOGIN_FAIL);
            return ResponseDTO.error(UserErrorCode.CODE_VERIFY_ERROR);
        }

        LoginDeviceEnum loginDeviceEnum = SmartEnumUtil.getEnumByValue(loginForm.getLoginDevice(), LoginDeviceEnum.class);
        AppUserLoginResultVO loginResultVO = getLoginInfo(userEntity, ip, userAgent, loginDeviceEnum.getDesc());

        return ResponseDTO.ok(loginResultVO);
    }

    private AppUserLoginResultVO getLoginInfo(UserEntity userEntity, String ip, String userAgent, String desc) {

        String saTokenLoginId = userEntity.getType() + StringConst.COLON + userEntity.getId();
        // 登录
        StpUtil.login(saTokenLoginId, String.valueOf(desc));

        //保存登录记录
        saveLoginLog(userEntity, ip, userAgent, desc, LoginLogResultEnum.LOGIN_SUCCESS);

        // 返回登录结果
        AppUserLoginResultVO loginResultVO = SmartBeanUtil.copy(userEntity, AppUserLoginResultVO.class);

        loginResultVO.setToken(StpUtil.getTokenValue());
        loginResultVO.setIp(ip);
        loginResultVO.setUserAgent(userAgent);

        return loginResultVO;
    }

    /**
     * 根据登陆token 获取请求用户信息
     */
    public RequestAppUser getLoginAppUser(String loginId, HttpServletRequest request) {
        if (loginId == null) {
            return null;
        }

        Long appUserId = getUserIdByLoginId(loginId);
        if (appUserId == null) {
            return null;
        }

        RequestAppUser requestAppUser = loginUserCache.get(appUserId);
        if (requestAppUser == null) {
            // 用户基本信息
            UserEntity userEntity = userDao.selectById(appUserId);
            if (userEntity == null) {
                return null;
            }

            requestAppUser = SmartBeanUtil.copy(userEntity, RequestAppUser.class);
            loginUserCache.put(appUserId, requestAppUser);
        }

        // 更新请求ip和user agent
        requestAppUser.setUserAgent(ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT));
        requestAppUser.setIp(ServletUtil.getClientIP(request));

        return requestAppUser;
    }

    private RequestAppUser loadLoginInfo(UserEntity userEntity) {
        RequestAppUser requestAppUser = new RequestAppUser();
        requestAppUser.setId(userEntity.getId());
        requestAppUser.setUsername(userEntity.getUsername());
        requestAppUser.setMobile(userEntity.getMobile());
        requestAppUser.setEmail(userEntity.getEmail());
        requestAppUser.setAvatar(userEntity.getAvatar());
        requestAppUser.setRealName(userEntity.getRealName());
        requestAppUser.setWechatOpenid(userEntity.getWechatOpenid());
        requestAppUser.setWeiboUid(userEntity.getWeiboUid());
        requestAppUser.setTotalPoints(userEntity.getTotalPoints());
        requestAppUser.setStatus(userEntity.getStatus());
        requestAppUser.setPaid(userEntity.getPaid());
        return requestAppUser;
    }

    /**
     * 根据 loginId 获取 用户id
     */
    Long getUserIdByLoginId(String loginId) {

        if (loginId == null) {
            return null;
        }

        try {
            String userIdStr = loginId.substring(2);

            return Long.parseLong(userIdStr);
        } catch (Exception e) {
            log.error("loginId parse error , loginId : {}", loginId, e);
            return null;
        }
    }

    /**
     * 保存登录日志
     */
    private void saveLoginLog(UserEntity userEntity, String ip, String userAgent, String remark, LoginLogResultEnum result) {
        LoginLogEntity loginEntity = LoginLogEntity.builder()
                .userId(userEntity.getId())
                .userType(userEntity.getType())
                .userName(userEntity.getUsername())
                .userAgent(userAgent)
                .loginIp(ip)
                .loginIpRegion(SmartIpUtil.getRegion(ip))
                .remark(remark)
                .loginResult(result.getValue())
                .createTime(LocalDateTime.now())
                .build();
        loginLogService.log(loginEntity);
    }

    /**
     * 退出登录
     */
    public ResponseDTO<String> logout(String token, RequestUser requestUser) {

        // sa token 登出
        StpUtil.logoutByTokenValue(token);

        // 清空登录信息缓存
        loginUserCache.remove(requestUser.getUserId());

        //保存登出日志
        LoginLogEntity loginEntity = LoginLogEntity.builder()
                .userId(requestUser.getUserId())
                .userType(requestUser.getUserType().getValue())
                .userName(requestUser.getUserName())
                .userAgent(requestUser.getUserAgent())
                .loginIp(requestUser.getIp())
                .loginIpRegion(SmartIpUtil.getRegion(requestUser.getIp()))
                .loginResult(LoginLogResultEnum.LOGIN_OUT.getValue())
                .createTime(LocalDateTime.now())
                .build();
        loginLogService.log(loginEntity);

        return ResponseDTO.ok();
    }

    public AppUserLoginResultVO getLoginResult(RequestAppUser requestAppUser) {

        // 基础信息
        UserEntity userEntity = userDao.selectById(requestAppUser.getUserId());
        AppUserLoginResultVO loginResultVO = getLoginInfo(userEntity, requestAppUser.getIp(), requestAppUser.getUserAgent(), requestAppUser.getLoginDevice());

        // 上次登录信息
        LoginLogVO loginLogVO = loginLogService.queryLastByUserId(requestAppUser.getUserId(), SmartEnumUtil.getEnumByValue(requestAppUser.getType(), UserTypeEnum.class), LoginLogResultEnum.LOGIN_SUCCESS);
        if (loginLogVO != null) {
            loginResultVO.setLastLoginIp(loginLogVO.getLoginIp());
            loginResultVO.setLastLoginIpRegion(loginLogVO.getLoginIpRegion());
            loginResultVO.setLastLoginTime(loginLogVO.getCreateTime());
            loginResultVO.setLastLoginUserAgent(loginLogVO.getUserAgent());
        }
        return loginResultVO;
    }
}
