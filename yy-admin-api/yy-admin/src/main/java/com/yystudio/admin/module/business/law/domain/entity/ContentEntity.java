package com.yystudio.admin.module.business.law.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 内容表 实体类
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@Data
@TableName("t_content")
public class ContentEntity {

    /**
     * 内容ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 类型（0:免费，1:付费）
     */
    private Integer type;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 发布者ID
     */
    private Long userId;

    /**
     * 封面图片
     */
    private String coverImage;

    /**
     * 内容正文
     */
    private String content;

    /**
     * 标签
     */
    private String tags;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否删除
     */
    private Integer deletedFlag;

    /**
     * 阅读量
     */
    private Integer views;

    /**
     * 点赞量
     */
    private Integer likes;

    /**
     * 收藏量
     */
    private Integer collections;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}