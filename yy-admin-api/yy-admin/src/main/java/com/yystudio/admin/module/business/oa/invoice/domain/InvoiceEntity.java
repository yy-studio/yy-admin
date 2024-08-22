package com.yystudio.admin.module.business.oa.invoice.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.yystudio.base.module.support.datatracer.annoation.DataTracerFieldLabel;

import java.time.LocalDateTime;

/**
 * OA发票信息
 *
 * @Author yy-studio
 * @Date 2022-06-23 19:32:59
 * 
 *
 * @Copyright  远游工作室
 */
@Data
@TableName("t_oa_invoice")
public class InvoiceEntity {

    /**
     * 发票信息ID
     */
    @TableId(type = IdType.AUTO)
    private Long invoiceId;

    /**
     * 开票抬头
     */
    @DataTracerFieldLabel("开票抬头")
    private String invoiceHeads;

    /**
     * 纳税人识别号
     */
    @DataTracerFieldLabel("纳税人识别号")
    private String taxpayerIdentificationNumber;

    /**
     * 银行账户
     */
    @DataTracerFieldLabel("银行账户")
    private String accountNumber;

    /**
     * 开户行
     */
    @DataTracerFieldLabel("开户行")
    private String bankName;

    /**
     * 备注
     */
    @DataTracerFieldLabel("备注")
    private String remark;

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
    @DataTracerFieldLabel("删除状态")
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
