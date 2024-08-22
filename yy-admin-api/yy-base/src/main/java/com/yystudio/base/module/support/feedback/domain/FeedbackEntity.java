package com.yystudio.base.module.support.feedback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 意见反馈 表
 *
 * @Author yy-studio
 * @Date 2022-08-11 20:48:09
 *
 * 
 * @Copyright  远游工作室
 */
@Data
@TableName("t_feedback")
public class FeedbackEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long feedbackId;

    /**
     * 反馈内容
     */
    private String feedbackContent;

    /**
     * 反馈附件
     */
    private String feedbackAttachment;

    /**
     * 创建人id
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 创建人姓名
     */
    private String userName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}