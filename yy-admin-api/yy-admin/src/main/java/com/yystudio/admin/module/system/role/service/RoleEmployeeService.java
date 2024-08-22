package com.yystudio.admin.module.system.role.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.admin.module.system.department.dao.DepartmentDao;
import com.yystudio.admin.module.system.department.domain.entity.DepartmentEntity;
import com.yystudio.admin.module.system.role.dao.RoleDao;
import com.yystudio.admin.module.system.role.dao.RoleEmployeeDao;
import com.yystudio.admin.module.system.role.domain.entity.RoleEmployeeEntity;
import com.yystudio.admin.module.system.role.domain.entity.RoleEntity;
import com.yystudio.admin.module.system.role.domain.form.RoleEmployeeQueryForm;
import com.yystudio.admin.module.system.role.domain.form.RoleEmployeeUpdateForm;
import com.yystudio.admin.module.system.role.domain.vo.RoleSelectedVO;
import com.yystudio.admin.module.system.role.domain.vo.RoleVO;
import com.yystudio.admin.module.system.employee.domain.vo.EmployeeVO;
import com.yystudio.admin.module.system.role.manager.RoleEmployeeManager;
import com.yystudio.base.common.constant.StringConst;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.common.util.SmartPageUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色-员工
 *
 * @Author yy-studio
 * @Date 2021-10-22 23:17:47
 *
 *
 * @Copyright  远游工作室
 */
@Service
public class RoleEmployeeService {

    @Resource
    private RoleEmployeeDao roleEmployeeDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private RoleEmployeeManager roleEmployeeManager;


    /**
     * 批量插入
     *
     */
    public void batchInsert(List<RoleEmployeeEntity> roleEmployeeList) {
        roleEmployeeManager.saveBatch(roleEmployeeList);
    }

    /**
     * 通过角色id，分页获取成员员工列表
     *
     */
    public ResponseDTO<PageResult<EmployeeVO>> queryEmployee(RoleEmployeeQueryForm roleEmployeeQueryForm) {
        Page page = SmartPageUtil.convert2PageQuery(roleEmployeeQueryForm);
        List<EmployeeVO> employeeList = roleEmployeeDao.selectRoleEmployeeByName(page, roleEmployeeQueryForm)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Long> departmentIdList = employeeList.stream().filter(e -> e != null && e.getDepartmentId() != null).map(EmployeeVO::getDepartmentId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(departmentIdList)) {
            List<DepartmentEntity> departmentEntities = departmentDao.selectBatchIds(departmentIdList);
            Map<Long, String> departmentIdNameMap = departmentEntities.stream().collect(Collectors.toMap(DepartmentEntity::getDepartmentId, DepartmentEntity::getName));
            employeeList.forEach(e -> {
                e.setDepartmentName(departmentIdNameMap.getOrDefault(e.getDepartmentId(), StringConst.EMPTY));
            });
        }
        PageResult<EmployeeVO> pageResult = SmartPageUtil.convert2PageResult(page, employeeList, EmployeeVO.class);
        return ResponseDTO.ok(pageResult);
    }

    public List<EmployeeVO> getAllEmployeeByRoleId(Long roleId) {
        return roleEmployeeDao.selectEmployeeByRoleId(roleId);
    }

    /**
     * 移除员工角色
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeRoleEmployee(Long employeeId, Long roleId) {
        if (null == employeeId || null == roleId) {
            return ResponseDTO.userErrorParam();
        }
        roleEmployeeDao.deleteByEmployeeIdRoleId(employeeId, roleId);
        return ResponseDTO.ok();
    }

    /**
     * 批量删除角色的成员员工
     *
     */
    public ResponseDTO<String> batchRemoveRoleEmployee(RoleEmployeeUpdateForm roleEmployeeUpdateForm) {
        roleEmployeeDao.batchDeleteEmployeeRole(roleEmployeeUpdateForm.getRoleId(), roleEmployeeUpdateForm.getEmployeeIdList());
        return ResponseDTO.ok();
    }

    /**
     * 批量添加角色的成员员工
     *
     */
    public ResponseDTO<String> batchAddRoleEmployee(RoleEmployeeUpdateForm roleEmployeeUpdateForm) {
        Long roleId = roleEmployeeUpdateForm.getRoleId();
        List<Long> employeeIdList = roleEmployeeUpdateForm.getEmployeeIdList();
        // 保存新的角色员工
        List<RoleEmployeeEntity> roleEmployeeList = null;
        if (CollectionUtils.isNotEmpty(employeeIdList)) {
            roleEmployeeList = employeeIdList.stream()
                    .map(employeeId -> new RoleEmployeeEntity(roleId, employeeId))
                    .collect(Collectors.toList());
        }
        // 保存数据
        roleEmployeeManager.saveRoleEmployee(roleId, roleEmployeeList);
        return ResponseDTO.ok();
    }

    /**
     * 通过员工id获取员工角色
     *
     */
    public List<RoleSelectedVO> getRoleInfoListByEmployeeId(Long employeeId) {
        List<Long> roleIds = roleEmployeeDao.selectRoleIdByEmployeeId(employeeId);
        List<RoleEntity> roleList = roleDao.selectList(null);
        List<RoleSelectedVO> result = SmartBeanUtil.copyList(roleList, RoleSelectedVO.class);
        result.stream().forEach(item -> item.setSelected(roleIds.contains(item.getRoleId())));
        return result;
    }

    /**
     * 根据员工id 查询角色id集合
     *
     */
    public List<RoleVO> getRoleIdList(Long employeeId) {
        return roleEmployeeDao.selectRoleByEmployeeId(employeeId);
    }


}
