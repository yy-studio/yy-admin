package com.yystudio.admin.module.system.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.yystudio.admin.module.system.role.domain.entity.RoleEntity;

/**
 * 角色 dao
 *
 * @Author yy-studio
 * @Date 2022-02-26 21:34:01
 * 
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface RoleDao extends BaseMapper<RoleEntity> {

    /**
     * 根据角色名称查询
     */
    RoleEntity getByRoleName(@Param("roleName") String roleName);

    /**
     * 根据角色编码
     */
    RoleEntity getByRoleCode(@Param("roleCode") String roleCode);
}
