package com.yystudio.admin.module.system.menu.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yystudio.admin.constant.AdminSwaggerTagConst;
import com.yystudio.admin.module.system.menu.domain.form.MenuAddForm;
import com.yystudio.admin.module.system.menu.domain.form.MenuUpdateForm;
import com.yystudio.admin.module.system.menu.domain.vo.MenuTreeVO;
import com.yystudio.admin.module.system.menu.domain.vo.MenuVO;
import com.yystudio.admin.module.system.menu.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.base.common.domain.RequestUrlVO;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartRequestUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 菜单
 *
 * @Author yy-studio
 * @Date 2022-03-06 22:04:37
 *
 *
 * @Copyright  远游工作室
 */
@RestController
@Tag(name = AdminSwaggerTagConst.System.SYSTEM_MENU)
public class MenuController {

    @Resource
    private MenuService menuService;

    @Operation(summary = "添加菜单 @author 卓大")
    @PostMapping("/menu/add")
    @SaCheckPermission("system:menu:add")
    public ResponseDTO<String> addMenu(@RequestBody @Valid MenuAddForm menuAddForm) {
        menuAddForm.setCreateUserId(SmartRequestUtil.getRequestUserId());
        return menuService.addMenu(menuAddForm);
    }

    @Operation(summary = "更新菜单 @author 卓大")
    @PostMapping("/menu/update")
    @SaCheckPermission("system:menu:update")
    public ResponseDTO<String> updateMenu(@RequestBody @Valid MenuUpdateForm menuUpdateForm) {
        menuUpdateForm.setUpdateUserId(SmartRequestUtil.getRequestUserId());
        return menuService.updateMenu(menuUpdateForm);
    }

    @Operation(summary = "批量删除菜单 @author 卓大")
    @GetMapping("/menu/batchDelete")
    @SaCheckPermission("system:menu:batchDelete")
    public ResponseDTO<String> batchDeleteMenu(@RequestParam("menuIdList") List<Long> menuIdList) {
        return menuService.batchDeleteMenu(menuIdList, SmartRequestUtil.getRequestUserId());
    }

    @Operation(summary = "查询菜单列表 @author 卓大")
    @GetMapping("/menu/query")
    public ResponseDTO<List<MenuVO>> queryMenuList() {
        return ResponseDTO.ok(menuService.queryMenuList(null));
    }

    @Operation(summary = "查询菜单详情 @author 卓大")
    @GetMapping("/menu/detail/{menuId}")
    public ResponseDTO<MenuVO> getMenuDetail(@PathVariable Long menuId) {
        return menuService.getMenuDetail(menuId);
    }

    @Operation(summary = "查询菜单树 @author 卓大")
    @GetMapping("/menu/tree")
    public ResponseDTO<List<MenuTreeVO>> queryMenuTree(@RequestParam("onlyMenu") Boolean onlyMenu) {
        return menuService.queryMenuTree(onlyMenu);
    }

    @Operation(summary = "获取所有请求路径 @author 卓大")
    @GetMapping("/menu/auth/url")
    public ResponseDTO<List<RequestUrlVO>> getAuthUrl() {
        return menuService.getAuthUrl();
    }
}
