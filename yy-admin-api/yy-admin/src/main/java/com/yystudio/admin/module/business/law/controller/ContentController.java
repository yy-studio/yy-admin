package com.yystudio.admin.module.business.law.controller;

import com.yystudio.admin.module.business.law.domain.form.ContentAddForm;
import com.yystudio.admin.module.business.law.domain.form.ContentQueryForm;
import com.yystudio.admin.module.business.law.domain.form.ContentUpdateForm;
import com.yystudio.admin.module.business.law.domain.vo.ContentDetailVO;
import com.yystudio.admin.module.business.law.domain.vo.ContentVO;
import com.yystudio.admin.module.business.law.service.ContentService;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.domain.ValidateList;
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
 * 内容表 Controller
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@RestController
@Tag(name = "文章")
public class ContentController {

    @Resource
    private ContentService contentService;

    @Operation(summary = "分页查询 @author yy")
    @PostMapping("/content/queryPage")
    public ResponseDTO<PageResult<ContentVO>> queryPage(@RequestBody @Valid ContentQueryForm queryForm) {
        return ResponseDTO.ok(contentService.queryPage(queryForm));
    }

    @Operation(summary = "添加 @author yy")
    @PostMapping("/content/add")
    public ResponseDTO<String> add(@RequestBody @Valid ContentAddForm addForm) {

        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        addForm.setUserId(requestUser.getUserId());
        return contentService.add(addForm);
    }

    @Operation(summary = "更新 @author yy")
    @PostMapping("/content/update")
    public ResponseDTO<String> update(@RequestBody @Valid ContentUpdateForm updateForm) {
        return contentService.update(updateForm);
    }

    @Operation(summary = "批量删除 @author yy")
    @PostMapping("/content/batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody ValidateList<Long> idList) {
        return contentService.batchDelete(idList);
    }

    @Operation(summary = "单个删除 @author yy")
    @GetMapping("/content/delete/{id}")
    public ResponseDTO<String> batchDelete(@PathVariable Long id) {
        return contentService.delete(id);
    }

    @Operation(summary = "下线 @author yy")
    @GetMapping("/content/offLine/{id}")
    public ResponseDTO<String> offLine(@PathVariable Long id) {
        return contentService.offLine(id);
    }


    @Operation(summary = "详情 @author yy")
    @GetMapping("/content/detail/{id}")
    public ResponseDTO<ContentDetailVO> detail(@PathVariable Long id) {
        return contentService.detail(id);
    }
}
