package com.yystudio.admin.module.system.role.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yystudio.admin.constant.AdminSwaggerTagConst;
import com.yystudio.admin.module.system.role.domain.form.RoleDataScopeUpdateForm;
import com.yystudio.admin.module.system.role.domain.vo.RoleDataScopeVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.admin.module.system.role.service.RoleDataScopeService;
import com.yystudio.base.common.domain.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色的数据权限配置
 *
 * @Author yy-studio
 * @Date 2022-02-26 22:09:59
 *
 * 
 * @Copyright 远游工作室
 */
@RestController
@Tag(name = AdminSwaggerTagConst.System.SYSTEM_ROLE_DATA_SCOPE)
public class RoleDataScopeController {

    @Resource
    private RoleDataScopeService roleDataScopeService;

    @Operation(summary = "获取某角色所设置的数据范围 @author 卓大")
    @GetMapping("/role/dataScope/getRoleDataScopeList/{roleId}")
    public ResponseDTO<List<RoleDataScopeVO>> dataScopeListByRole(@PathVariable Long roleId) {
        return roleDataScopeService.getRoleDataScopeList(roleId);
    }

    @Operation(summary = "批量设置某角色数据范围 @author 卓大")
    @PostMapping("/role/dataScope/updateRoleDataScopeList")
    @SaCheckPermission("system:role:dataScope:update")
    public ResponseDTO<String> updateRoleDataScopeList(@RequestBody @Valid RoleDataScopeUpdateForm roleDataScopeUpdateForm) {
        return roleDataScopeService.updateRoleDataScopeList(roleDataScopeUpdateForm);
    }


}
