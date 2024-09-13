package com.yystudio.admin.module.business.law.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户表 实体类
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Data
@TableName("t_user")
public class UserEntity {

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 微信openid
     */
    private String wechatOpenid;

    /**
     * 微博uid
     */
    private String weiboUid;

    /**
     * 用户类型（2:个人，3:企业）
     */
    private Integer type;

    /**
     * 总积分
     */
    private Integer totalPoints;

    /**
     * 状态:1正常,0未激活,-1注销
     */
    private Integer status;

    /**
     * 1付费用户
     */
    private Integer paid;

    /**
     * 订阅剩余次数
     */
    private Integer remainingTimes;

    /**
     * 订阅截止时间
     */
    private LocalDateTime subscriptionDeadline;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 修改人ID
     */
    private Long updateUserId;

    /**
     * 修改人姓名
     */
    private String updateUserName;

}