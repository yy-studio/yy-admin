package com.yystudio.admin.module.system.role.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yystudio.admin.constant.AdminSwaggerTagConst;
import com.yystudio.admin.module.system.role.domain.form.RoleAddForm;
import com.yystudio.admin.module.system.role.domain.form.RoleUpdateForm;
import com.yystudio.admin.module.system.role.domain.vo.RoleVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.admin.module.system.role.service.RoleService;
import com.yystudio.base.common.domain.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色
 *
 * @Author yy-studio
 * @Date 2021-12-14 19:40:28
 *
 * 
 * @Copyright  远游工作室
 */
@RestController
@Tag(name = AdminSwaggerTagConst.System.SYSTEM_ROLE)
public class RoleController {

    @Resource
    private RoleService roleService;

    @Operation(summary = "添加角色 @author 卓大")
    @PostMapping("/role/add")
    @SaCheckPermission("system:role:add")
    public ResponseDTO<String> addRole(@Valid @RequestBody RoleAddForm roleAddForm) {
        return roleService.addRole(roleAddForm);
    }

    @Operation(summary = "删除角色 @author 卓大")
    @GetMapping("/role/delete/{roleId}")
    @SaCheckPermission("system:role:delete")
    public ResponseDTO<String> deleteRole(@PathVariable Long roleId) {
        return roleService.deleteRole(roleId);
    }

    @Operation(summary = "更新角色 @author 卓大")
    @PostMapping("/role/update")
    @SaCheckPermission("system:role:update")
    public ResponseDTO<String> updateRole(@Valid @RequestBody RoleUpdateForm roleUpdateDTO) {
        return roleService.updateRole(roleUpdateDTO);
    }

    @Operation(summary = "获取角色数据 @author 卓大")
    @GetMapping("/role/get/{roleId}")
    public ResponseDTO<RoleVO> getRole(@PathVariable("roleId") Long roleId) {
        return roleService.getRoleById(roleId);
    }

    @Operation(summary = "获取所有角色 @author 卓大")
    @GetMapping("/role/getAll")
    public ResponseDTO<List<RoleVO>> getAllRole() {
        return roleService.getAllRole();
    }

}
