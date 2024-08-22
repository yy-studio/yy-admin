package com.yystudio.admin.module.system.support;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.base.common.controller.SupportBaseController;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.domain.ValidateList;
import com.yystudio.base.constant.SwaggerTagConst;
import com.yystudio.base.module.support.securityprotect.domain.LoginFailQueryForm;
import com.yystudio.base.module.support.securityprotect.domain.LoginFailVO;
import com.yystudio.base.module.support.securityprotect.service.ProtectLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 * 网络安全
 *
 * @Author yy-studio
 * @Date 2023/10/17 19:07:27
 *
 *
 * @Copyright  远游工作室，Since 2012
 */

@RestController
@Tag(name = SwaggerTagConst.Support.PROTECT)
public class AdminProtectController extends SupportBaseController {

    @Resource
    private ProtectLoginService protectLoginService;

    @Operation(summary = "分页查询 @author yy-studio")
    @PostMapping("/protect/loginFail/queryPage")
    public ResponseDTO<PageResult<LoginFailVO>> queryPage(@RequestBody @Valid LoginFailQueryForm queryForm) {
        return ResponseDTO.ok(protectLoginService.queryPage(queryForm));
    }


    @Operation(summary = "批量删除 @author yy-studio")
    @PostMapping("/protect/loginFail/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return protectLoginService.batchDelete(idList);
    }


}
