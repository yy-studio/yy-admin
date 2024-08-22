package com.yystudio.admin.module.system.role.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yystudio.admin.constant.AdminSwaggerTagConst;
import com.yystudio.admin.module.system.role.domain.form.RoleMenuUpdateForm;
import com.yystudio.admin.module.system.role.domain.vo.RoleMenuTreeVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.admin.module.system.role.service.RoleMenuService;
import com.yystudio.base.common.domain.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 角色的菜单
 *
 * @Author yy-studio
 * @Date 2022-02-26 21:34:01
 *
 *
 * @Copyright  远游工作室
 */
@RestController
@Tag(name = AdminSwaggerTagConst.System.SYSTEM_ROLE_MENU)
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;

    @Operation(summary = "更新角色权限 @author 卓大")
    @PostMapping("/role/menu/updateRoleMenu")
    @SaCheckPermission("system:role:menu:update")
    public ResponseDTO<String> updateRoleMenu(@Valid @RequestBody RoleMenuUpdateForm updateDTO) {
        return roleMenuService.updateRoleMenu(updateDTO);
    }

    @Operation(summary = "获取角色关联菜单权限 @author 卓大")
    @GetMapping("/role/menu/getRoleSelectedMenu/{roleId}")
    public ResponseDTO<RoleMenuTreeVO> getRoleSelectedMenu(@PathVariable Long roleId) {
        return roleMenuService.getRoleSelectedMenu(roleId);
    }
}
