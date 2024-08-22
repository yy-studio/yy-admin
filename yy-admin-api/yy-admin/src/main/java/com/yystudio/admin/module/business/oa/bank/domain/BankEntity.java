package com.yystudio.admin.module.business.oa.bank.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.yystudio.base.module.support.datatracer.annoation.DataTracerFieldLabel;

import java.time.LocalDateTime;

/**
 * OA办公-OA银行信息
 *
 * @Author yy-studio
 * @Date 2022/6/23 21:59:22
 *
 *
 * @Copyright  远游工作室
 */
@Data
@TableName("t_oa_bank")
public class BankEntity {

    /**
     * 银行信息ID
     */
    @TableId(type = IdType.AUTO)
    @DataTracerFieldLabel("银行信息ID")
    private Long bankId;

    /**
     * 开户银行
     */
    @DataTracerFieldLabel("开户银行")
    private String bankName;

    /**
     * 账户名称
     */
    @DataTracerFieldLabel("账户名称")
    private String accountName;

    /**
     * 账号
     */
    @DataTracerFieldLabel("账号")
    private String accountNumber;

    /**
     * 备注
     */
    @DataTracerFieldLabel("备注")
    private String remark;

    /**
     * 是否对公
     */
    @DataTracerFieldLabel("是否对公")
    private Boolean businessFlag;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 禁用状态
     */
    @DataTracerFieldLabel("禁用状态")
    private Boolean disabledFlag;

    /**
     * 删除状态
     */
    private Boolean deletedFlag;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建人ID
     */
    private String createUserName;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
