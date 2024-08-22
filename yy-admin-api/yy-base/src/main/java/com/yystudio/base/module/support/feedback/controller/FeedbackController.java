package com.yystudio.base.module.support.feedback.controller;

import com.yystudio.base.module.support.feedback.domain.FeedbackAddForm;
import com.yystudio.base.module.support.feedback.domain.FeedbackQueryForm;
import com.yystudio.base.module.support.feedback.domain.FeedbackVO;
import com.yystudio.base.module.support.feedback.service.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import com.yystudio.base.common.controller.SupportBaseController;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartRequestUtil;
import com.yystudio.base.constant.SwaggerTagConst;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 意见反馈
 *
 * @Author yy-studio
 * @Date 2022-08-11 20:48:09
 *
 *
 * @Copyright  远游工作室
 */
@Slf4j
@Tag(name = SwaggerTagConst.Support.FEEDBACK)
@RestController
public class FeedbackController extends SupportBaseController {

    @Resource
    private FeedbackService feedbackService;

    @Operation(summary = "意见反馈-分页查询 @author 开云")
    @PostMapping("/feedback/query")
    public ResponseDTO<PageResult<FeedbackVO>> query(@RequestBody @Valid FeedbackQueryForm queryForm) {
        return feedbackService.query(queryForm);
    }

    @Operation(summary = "意见反馈-新增 @author 开云")
    @PostMapping("/feedback/add")
    public ResponseDTO<String> add(@RequestBody @Valid FeedbackAddForm addForm) {
        RequestUser employee = SmartRequestUtil.getRequestUser();
        return feedbackService.add(addForm, employee);
    }
}
