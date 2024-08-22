package com.yystudio.base.module.support.dict.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 字典
 *
 * @Author yy-studio
 * @Date 2022/5/26 19:40:55
 * 
 *
 * @Copyright  远游工作室
 */
@Data
@TableName("t_dict_key")
public class DictKeyEntity {

    @TableId(type = IdType.AUTO)
    private Long dictKeyId;

    /**
     * 编码
     */
    private String keyCode;
    /**
     * 名称
     */
    private String keyName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标识
     */
    private Boolean deletedFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}