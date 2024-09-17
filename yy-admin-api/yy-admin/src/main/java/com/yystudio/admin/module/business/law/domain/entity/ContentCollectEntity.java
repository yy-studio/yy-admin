package com.yystudio.admin.module.business.law.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("t_content_collect")
public class ContentCollectEntity {

    /**
     * 收藏记录ID
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
     * 收藏时间
     */
    private Timestamp createTime;
}
