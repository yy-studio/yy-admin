package com.yystudio.admin.module.business.oa.enterprise.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 企业员工
 *
 * @Author yy-studio
 * @Date 2022/7/28 20:37:15
 *
 *
 * @Copyright  远游工作室
 */
@Data
@TableName("t_oa_enterprise_employee")
@NoArgsConstructor
public class EnterpriseEmployeeEntity {

    @TableId(type = IdType.AUTO)
    private Long enterpriseEmployeeId;

    /**
     * 企业ID
     */
    private Long enterpriseId;
    /**
     * 员工
     */
    private Long employeeId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public EnterpriseEmployeeEntity(Long enterpriseId, Long employeeId) {
        this.enterpriseId = enterpriseId;
        this.employeeId = employeeId;
    }
}
