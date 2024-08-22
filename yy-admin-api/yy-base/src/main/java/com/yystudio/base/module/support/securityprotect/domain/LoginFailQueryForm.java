package com.yystudio.base.module.support.securityprotect.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.yystudio.base.common.domain.PageParam;

import java.time.LocalDate;

/**
 * 登录失败 分页查询表单
 *
 * @Author yy-studio
 * @Date 2023-10-17 18:02:37
 * @Copyright yy-studio
 */

@Data
public class LoginFailQueryForm extends PageParam {

    @Schema(description = "登录名")
    private String loginName;

    @Schema(description = "锁定状态")
    private Boolean lockFlag;

    @Schema(description = "登录失败锁定时间")
    private LocalDate loginLockBeginTimeBegin;

    @Schema(description = "登录失败锁定时间")
    private LocalDate loginLockBeginTimeEnd;

}