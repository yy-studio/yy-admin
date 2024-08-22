package com.yystudio.base.module.support.config;

import com.yystudio.base.common.controller.SupportBaseController;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.constant.SwaggerTagConst;
import com.yystudio.base.module.support.config.domain.ConfigVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 配置
 *
 * @Author yy-studio
 * @Date 2022-03-14 20:46:27
 *
 *
 * @Copyright  远游工作室
 */
@Tag(name = SwaggerTagConst.Support.CONFIG)
@RestController
public class ConfigController extends SupportBaseController {

    @Resource
    private ConfigService configService;

    @Operation(summary = "查询配置详情 @author 卓大")
    @GetMapping("/config/queryByKey")
    public ResponseDTO<ConfigVO> queryByKey(@RequestParam String configKey) {
        return ResponseDTO.ok(configService.getConfig(configKey));
    }

}
