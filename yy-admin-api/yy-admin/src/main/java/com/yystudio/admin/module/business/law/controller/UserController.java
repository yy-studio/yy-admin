package com.yystudio.admin.module.business.law.controller;

import com.yystudio.admin.module.business.law.constant.LawConst;
import com.yystudio.admin.module.business.law.constant.UserStatusEnum;
import com.yystudio.admin.module.business.law.constant.YesNoEnum;
import com.yystudio.admin.module.business.law.dao.ReadingRecordDao;
import com.yystudio.admin.module.business.law.domain.form.*;
import com.yystudio.admin.module.business.law.domain.vo.ContentVO;
import com.yystudio.admin.module.business.law.domain.vo.ReadingRecordVO;
import com.yystudio.admin.module.business.law.domain.vo.UserVO;
import com.yystudio.admin.module.business.law.service.ReadingRecordService;
import com.yystudio.admin.module.business.law.service.UserService;
import com.yystudio.admin.module.system.employee.service.EmployeeService;
import com.yystudio.base.common.annoation.NoNeedLogin;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.domain.ValidateList;
import com.yystudio.base.common.enumeration.UserTypeEnum;
import com.yystudio.base.common.util.SmartRequestUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.domain.PageResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户表 Controller
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@RestController
@Tag(name = "用户")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "分页查询 @author yy")
    @PostMapping("/user/queryPage")
    public ResponseDTO<PageResult<UserVO>> queryPage(@RequestBody @Valid UserQueryForm queryForm) {
        return ResponseDTO.ok(userService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author yy")
    @PostMapping("/user/add")
    public ResponseDTO<String> add(@RequestBody @Valid UserAddForm addForm) {

        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setCreateUserId(requestUser.getUserId());
        addForm.setCreateUserName(requestUser.getUserName());
        addForm.setPaid(YesNoEnum.YES.getValue());
        addForm.setStatus(UserStatusEnum.UN_ACTIVE.getValue());
        addForm.setType(UserTypeEnum.USER_ENTERPRISE.getValue());
        addForm.setPassword(EmployeeService.getEncryptPwd(LawConst.DEFAULT_PASSWORD));
        return userService.add(addForm);
    }

    @Operation(summary = "更新 @author yy")
    @PostMapping("/user/update")
    public ResponseDTO<String> update(@RequestBody @Valid UserUpdateForm updateForm) {
        updateForm.setUpdateUserId(SmartRequestUtil.getRequestUser().getUserId());
        updateForm.setUpdateUserName(SmartRequestUtil.getRequestUser().getUserName());
        return userService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author yy")
    @PostMapping("/user/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return userService.batchDelete(idList);
    }

    @Operation(summary = "单个注销 @author yy")
    @GetMapping("/user/delete/{id}")
    public ResponseDTO<String> delete(@PathVariable Long id) {
        return userService.delete(id);
    }



}
