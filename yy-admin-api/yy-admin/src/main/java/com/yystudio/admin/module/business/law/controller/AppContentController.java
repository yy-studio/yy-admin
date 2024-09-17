package com.yystudio.admin.module.business.law.controller;

import com.yystudio.admin.module.business.law.domain.form.ContentAddForm;
import com.yystudio.admin.module.business.law.domain.form.ContentCollectForm;
import com.yystudio.admin.module.business.law.domain.form.ContentQueryForm;
import com.yystudio.admin.module.business.law.domain.form.ContentUpdateForm;
import com.yystudio.admin.module.business.law.domain.vo.AppContentDetailVO;
import com.yystudio.admin.module.business.law.domain.vo.ContentDetailVO;
import com.yystudio.admin.module.business.law.domain.vo.ContentVO;
import com.yystudio.admin.module.business.law.service.ContentService;
import com.yystudio.base.common.annoation.NoNeedLogin;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.domain.ValidateList;
import com.yystudio.base.common.util.SmartRequestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 内容表 Controller
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@RestController
@RequestMapping("/app")
@Tag(name = "APP文章接口")
public class AppContentController {

    @Resource
    private ContentService contentService;

    @Operation(summary = "分页查询 @author yy")
    @PostMapping("/content/queryPage")
    @NoNeedLogin
    public ResponseDTO<PageResult<ContentVO>> queryPage(@RequestBody @Valid ContentQueryForm queryForm) {
        return ResponseDTO.ok(contentService.queryAppPage(queryForm));
    }

    @Operation(summary = "详情 @author yy")
    @GetMapping("/content/detail/{id}")
    @NoNeedLogin
    public ResponseDTO<AppContentDetailVO> detail(@PathVariable Long id) {
        return contentService.detailForApp(id);
    }

    @Operation(summary = "收藏")
    @PostMapping("/content/collect")
    public ResponseDTO<String> collect(@RequestBody @Valid ContentCollectForm addForm) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        if(requestUser == null) {
            return ResponseDTO.userErrorParam("请先登录");
        }
        addForm.setUserId(requestUser.getUserId());
        return contentService.collect(addForm);
    }
}
