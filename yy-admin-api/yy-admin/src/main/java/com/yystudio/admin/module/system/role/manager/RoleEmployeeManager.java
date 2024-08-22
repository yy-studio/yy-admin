package com.yystudio.admin.module.system.role.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystudio.admin.module.system.role.dao.RoleEmployeeDao;
import com.yystudio.admin.module.system.role.domain.entity.RoleEmployeeEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色员工 manager
 *
 * @Author yy-studio
 * @Date 2022-04-08 21:53:04
 *
 *
 * @Copyright  远游工作室
 */
@Service
public class RoleEmployeeManager extends ServiceImpl<RoleEmployeeDao, RoleEmployeeEntity> {

    /**
     * 保存 角色员工
     *
     */
    @Transactional(rollbackFor = Throwable.class)
    public void saveRoleEmployee(Long roleId, List<RoleEmployeeEntity> roleEmployeeList) {
        this.getBaseMapper().deleteByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(roleEmployeeList)) {
            this.saveBatch(roleEmployeeList);
        }
    }
}
