package com.yystudio.admin.module.system.role.service;

import com.yystudio.admin.module.system.role.dao.RoleDao;
import com.yystudio.admin.module.system.role.dao.RoleEmployeeDao;
import com.yystudio.admin.module.system.role.dao.RoleMenuDao;
import com.yystudio.admin.module.system.role.domain.entity.RoleEntity;
import com.yystudio.admin.module.system.role.domain.form.RoleAddForm;
import com.yystudio.admin.module.system.role.domain.form.RoleUpdateForm;
import com.yystudio.admin.module.system.role.domain.vo.RoleVO;
import com.yystudio.base.common.code.UserErrorCode;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartBeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色
 *
 * @Author yy-studio
 * @Date 2021-08-16 20:19:22
 *
 *
 * @Copyright 远游工作室
 */
@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private RoleMenuDao roleMenuDao;

    @Resource
    private RoleEmployeeDao roleEmployeeDao;

    /**
     * 新增添加角色
     */
    public ResponseDTO<String> addRole(RoleAddForm roleAddForm) {
        RoleEntity existRoleEntity = roleDao.getByRoleName(roleAddForm.getRoleName());
        if (null != existRoleEntity) {
            return ResponseDTO.userErrorParam("角色名称重复");
        }

        existRoleEntity = roleDao.getByRoleCode(roleAddForm.getRoleCode());
        if (null != existRoleEntity) {
            return ResponseDTO.userErrorParam("角色编码重复，重复的角色为：" + existRoleEntity.getRoleName());
        }

        RoleEntity roleEntity = SmartBeanUtil.copy(roleAddForm, RoleEntity.class);
        roleDao.insert(roleEntity);
        return ResponseDTO.ok();
    }

    /**
     * 根据角色id 删除
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteRole(Long roleId) {
        RoleEntity roleEntity = roleDao.selectById(roleId);
        if (null == roleEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        roleDao.deleteById(roleId);
        roleMenuDao.deleteByRoleId(roleId);
        roleEmployeeDao.deleteByRoleId(roleId);
        return ResponseDTO.ok();
    }

    /**
     * 更新角色
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> updateRole(RoleUpdateForm roleUpdateForm) {
        if (null == roleDao.selectById(roleUpdateForm.getRoleId())) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }

        RoleEntity existRoleEntity = roleDao.getByRoleName(roleUpdateForm.getRoleName());
        if (null != existRoleEntity && !existRoleEntity.getRoleId().equals(roleUpdateForm.getRoleId())) {
            return ResponseDTO.userErrorParam("角色名称重复");
        }

        existRoleEntity = roleDao.getByRoleCode(roleUpdateForm.getRoleCode());
        if (null != existRoleEntity) {
            return ResponseDTO.userErrorParam("角色编码重复，重复的角色为：" + existRoleEntity.getRoleName());
        }

        RoleEntity roleEntity = SmartBeanUtil.copy(roleUpdateForm, RoleEntity.class);
        roleDao.updateById(roleEntity);
        return ResponseDTO.ok();
    }

    /**
     * 根据id获取角色数据
     */
    public ResponseDTO<RoleVO> getRoleById(Long roleId) {
        RoleEntity roleEntity = roleDao.selectById(roleId);
        if (null == roleEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        RoleVO role = SmartBeanUtil.copy(roleEntity, RoleVO.class);
        return ResponseDTO.ok(role);
    }

    /**
     * 获取所有角色列表
     */
    public ResponseDTO<List<RoleVO>> getAllRole() {
        List<RoleEntity> roleEntityList = roleDao.selectList(null);
        List<RoleVO> roleList = SmartBeanUtil.copyList(roleEntityList, RoleVO.class);
        return ResponseDTO.ok(roleList);
    }
}
