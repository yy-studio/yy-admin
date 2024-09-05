package com.yystudio.base.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户级别的错误码（用户引起的错误返回码，可以不用关注）
 *
 * @Author yy-studio
 * @Date 2021/09/21 22:12:27
 *
 *
 * @Copyright  远游工作室
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

    PARAM_ERROR(30001, "参数错误"),

    DATA_NOT_EXIST(30002, "左翻右翻，数据竟然找不到了~"),

    ALREADY_EXIST(30003, "数据已存在了呀~"),

    REPEAT_SUBMIT(30004, "亲~您操作的太快了，请稍等下再操作~"),

    NO_PERMISSION(30005, "对不起，您没有权限访问此内容哦~"),

    DEVELOPING(30006, "系統正在紧急开发中，敬请期待~"),

    LOGIN_STATE_INVALID(30007, "您还未登录或登录失效，请重新登录！"),

    USER_STATUS_ERROR(30008, "用户状态异常"),

    FORM_REPEAT_SUBMIT(30009, "请勿重复提交"),

    LOGIN_FAIL_LOCK(30010, "登录连续失败已经被锁定，无法登录"),
    LOGIN_FAIL_WILL_LOCK(30011, "登录连续失败将会锁定提醒"),

    LOGIN_ACTIVE_TIMEOUT(30012, "长时间未操作系统，需要重新登录"),


    UPDATE_ERROR(30101, "更新失败"),
    CODE_SEND_ERROR(30102, "验证码发送失败"),
    CODE_VERIFY_ERROR(30103, "验证码验证失败"),
    USER_NOT_EXIST(30104, "用户不存在"),
    USER_UN_ACTIVE(30105, "用户未激活"),
    PASSWORD_ERROR(30106, "用户名或密码错误"),
    DEFAULT_PASSWORD_ERROR(30107, "初始密码登录，请修改密码"),
    CODE_VERIFY_TIMEOUT_ERROR(30108, "验证码已失效，请重新获取"),
    ;


    private final int code;

    private final String msg;

    private final String level;

    UserErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.level = LEVEL_USER;
    }
}
