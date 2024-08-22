package com.yystudio.admin.module.system.role.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.yystudio.admin.constant.AdminSwaggerTagConst;
import com.yystudio.admin.module.system.role.domain.form.RoleEmployeeQueryForm;
import com.yystudio.admin.module.system.role.domain.form.RoleEmployeeUpdateForm;
import com.yystudio.admin.module.system.role.domain.vo.RoleSelectedVO;
import com.yystudio.admin.module.system.role.service.RoleEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.admin.module.system.employee.domain.vo.EmployeeVO;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色的员工
 *
 * @Author yy-studio
 * @Date 2022-02-26 22:09:59
 *
 *
 * @Copyright  远游工作室
 */
@RestController
@Tag(name = AdminSwaggerTagConst.System.SYSTEM_ROLE_EMPLOYEE)
public class RoleEmployeeController {

    @Resource
    private RoleEmployeeService roleEmployeeService;

    @Operation(summary = "查询某个角色下的员工列表  @author 卓大")
    @PostMapping("/role/employee/queryEmployee")
    public ResponseDTO<PageResult<EmployeeVO>> queryEmployee(@Valid @RequestBody RoleEmployeeQueryForm roleEmployeeQueryForm) {
        return roleEmployeeService.queryEmployee(roleEmployeeQueryForm);
    }

    @Operation(summary = "获取某个角色下的所有员工列表(无分页)  @author 卓大")
    @GetMapping("/role/employee/getAllEmployeeByRoleId/{roleId}")
    public ResponseDTO<List<EmployeeVO>> listAllEmployeeRoleId(@PathVariable Long roleId) {
        return ResponseDTO.ok(roleEmployeeService.getAllEmployeeByRoleId(roleId));
    }

    @Operation(summary = "从角色成员列表中移除员工 @author 卓大")
    @GetMapping("/role/employee/removeEmployee")
    @SaCheckPermission("system:role:employee:delete")
    public ResponseDTO<String> removeEmployee(Long employeeId, Long roleId) {
        return roleEmployeeService.removeRoleEmployee(employeeId, roleId);
    }

    @Operation(summary = "从角色成员列表中批量移除员工 @author 卓大")
    @PostMapping("/role/employee/batchRemoveRoleEmployee")
    @SaCheckPermission("system:role:employee:batch:delete")
    public ResponseDTO<String> batchRemoveEmployee(@Valid @RequestBody RoleEmployeeUpdateForm updateForm) {
        return roleEmployeeService.batchRemoveRoleEmployee(updateForm);
    }

    @Operation(summary = "角色成员列表中批量添加员工 @author 卓大")
    @PostMapping("/role/employee/batchAddRoleEmployee")
    @SaCheckPermission("system:role:employee:add")
    public ResponseDTO<String> addEmployeeList(@Valid @RequestBody RoleEmployeeUpdateForm addForm) {
        return roleEmployeeService.batchAddRoleEmployee(addForm);
    }

    @Operation(summary = "获取员工所有选中的角色和所有角色 @author 卓大")
    @GetMapping("/role/employee/getRoles/{employeeId}")
    public ResponseDTO<List<RoleSelectedVO>> getRoleByEmployeeId(@PathVariable Long employeeId) {
        return ResponseDTO.ok(roleEmployeeService.getRoleInfoListByEmployeeId(employeeId));
    }
}
