package com.yystudio.admin.module.business.law.domain.vo;

import lombok.Data;

@Data
public class ReadingRecordVO {
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    private String username;

    /**
     * 内容ID
     */
    private Long contentId;

    private String title;

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

}
