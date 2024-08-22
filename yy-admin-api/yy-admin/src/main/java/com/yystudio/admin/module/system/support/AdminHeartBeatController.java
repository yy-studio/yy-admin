package com.yystudio.admin.module.system.support;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.base.common.controller.SupportBaseController;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.constant.SwaggerTagConst;
import com.yystudio.base.module.support.heartbeat.HeartBeatService;
import com.yystudio.base.module.support.heartbeat.domain.HeartBeatRecordQueryForm;
import com.yystudio.base.module.support.heartbeat.domain.HeartBeatRecordVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 心跳记录
 *
 * @Author yy-studio
 * @Date 2022-01-09 20:57:24
 *
 *
 * @Copyright  远游工作室
 */
@Tag(name = SwaggerTagConst.Support.HEART_BEAT)
@RestController
public class AdminHeartBeatController extends SupportBaseController {

    @Resource
    private HeartBeatService heartBeatService;

    @PostMapping("/heartBeat/query")
    @Operation(summary = "查询心跳记录 @author 卓大")
    public ResponseDTO<PageResult<HeartBeatRecordVO>> query(@RequestBody @Valid HeartBeatRecordQueryForm pageParam) {
        return heartBeatService.pageQuery(pageParam);
    }

}
