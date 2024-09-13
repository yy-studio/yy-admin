package com.yystudio.admin.module.business.law.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_reading_record")
public class ReadingRecordEntity {

    /**
     * 阅读记录ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 内容ID
     */
    private Long contentId;

    /**
     * 阅读开始时间
     */
    private java.sql.Timestamp readTime;

    /**
     * 阅读耗时（秒）
     */
    private Integer readDuration;

    /**
     * 阅读进度（0-100）
     */
    private Integer progress;

    /**
     * 阅读类型（1付费阅读，0免费阅读）
     */
    private Integer readType;
}
