package com.yystudio.admin.module.system.support;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.base.common.controller.SupportBaseController;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartRequestUtil;
import com.yystudio.base.constant.SwaggerTagConst;
import com.yystudio.base.module.support.operatelog.OperateLogService;
import com.yystudio.base.module.support.operatelog.domain.OperateLogQueryForm;
import com.yystudio.base.module.support.operatelog.domain.OperateLogVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  操作日志
 *
 * @Author yy-studio
 * @Date 2021-12-08 20:48:52
 *
 *
 * @Copyright  远游工作室
 */
@RestController
@Tag(name = SwaggerTagConst.Support.OPERATE_LOG)
public class AdminOperateLogController extends SupportBaseController {

    @Resource
    private OperateLogService operateLogService;

    @Operation(summary = "分页查询 @author 罗伊")
    @PostMapping("/operateLog/page/query")
    @SaCheckPermission("support:operateLog:query")
    public ResponseDTO<PageResult<OperateLogVO>> queryByPage(@RequestBody OperateLogQueryForm queryForm) {
        return operateLogService.queryByPage(queryForm);
    }

    @Operation(summary = "详情 @author 罗伊")
    @GetMapping("/operateLog/detail/{operateLogId}")
    @SaCheckPermission("support:operateLog:detail")
    public ResponseDTO<OperateLogVO> detail(@PathVariable Long operateLogId) {
        return operateLogService.detail(operateLogId);
    }

    @Operation(summary = "分页查询当前登录人信息 @author 善逸")
    @PostMapping("/operateLog/page/query/login")
    public ResponseDTO<PageResult<OperateLogVO>> queryByPageLogin(@RequestBody OperateLogQueryForm queryForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        queryForm.setOperateUserId(requestUser.getUserId());
        queryForm.setOperateUserType(requestUser.getUserType().getValue());
        return operateLogService.queryByPage(queryForm);
    }

}
