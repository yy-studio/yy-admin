package com.yystudio.admin.module.system.role.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystudio.admin.module.system.role.dao.RoleMenuDao;
import com.yystudio.admin.module.system.role.domain.entity.RoleMenuEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色-菜单 manager
 *
 * @Author yy-studio
 * @Date 2022-04-09 19:05:49
 *
 *
 * @Copyright  远游工作室
 */
@Service
public class RoleMenuManager extends ServiceImpl<RoleMenuDao, RoleMenuEntity> {

    @Resource
    private RoleMenuDao roleMenuDao;

    /**
     * 更新角色权限
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleMenu(Long roleId, List<RoleMenuEntity> roleMenuEntityList) {
        // 根据角色ID删除菜单权限
        roleMenuDao.deleteByRoleId(roleId);
        // 批量添加菜单权限
        saveBatch(roleMenuEntityList);
    }
}
