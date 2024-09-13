package com.yystudio.admin.module.business.law.controller;

import com.yystudio.admin.module.business.law.domain.vo.AppTabBarVO;
import com.yystudio.base.common.annoation.NoNeedLogin;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.module.support.dict.domain.vo.DictValueVO;
import com.yystudio.base.module.support.dict.service.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * APP公共接口 Controller
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@RestController
@RequestMapping("/app/common")
@Tag(name = "APP公共接口")
public class AppCommonController {

    @Resource
    private DictService dictService;


    @Operation(summary = "获取tabBar标题列表")
    @GetMapping("/tabbars")
    @NoNeedLogin
    public ResponseDTO<List<AppTabBarVO>> tabbars() {

        List<DictValueVO> dictValueVOS = dictService.queryTabBarList();
        List<AppTabBarVO> appTabBarVOS = new ArrayList<>();
        for (DictValueVO dictValueVO : dictValueVOS) {
            AppTabBarVO appTabBarVO = new AppTabBarVO();
            appTabBarVO.setId(dictValueVO.getValueCode());
            appTabBarVO.setName(dictValueVO.getValueName());
            appTabBarVOS.add(appTabBarVO);
        }

        return ResponseDTO.ok(appTabBarVOS);
    }

}
