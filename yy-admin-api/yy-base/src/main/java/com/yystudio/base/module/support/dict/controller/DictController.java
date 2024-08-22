package com.yystudio.base.module.support.dict.controller;

import com.yystudio.base.common.controller.SupportBaseController;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.constant.SwaggerTagConst;
import com.yystudio.base.module.support.dict.domain.form.DictValueQueryForm;
import com.yystudio.base.module.support.dict.domain.vo.DictKeyVO;
import com.yystudio.base.module.support.dict.domain.vo.DictValueVO;
import com.yystudio.base.module.support.dict.service.DictCacheService;
import com.yystudio.base.module.support.dict.service.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 字典
 *
 * @Author yy-studio
 * @Date 2022/5/26 19:40:55
 *
 *
 * @Copyright  远游工作室
 */
@Tag(name = SwaggerTagConst.Support.DICT)
@RestController
public class DictController extends SupportBaseController {

    @Resource
    private DictService dictService;
    @Resource
    private DictCacheService dictCacheService;


    @Operation(summary = "查询全部字典key - @author 卓大")
    @GetMapping("/dict/key/queryAll")
    public ResponseDTO<List<DictKeyVO>> queryAll() {
        return ResponseDTO.ok(dictService.queryAllKey());
    }

    @Operation(summary = "分页查询数据字典value - @author 罗伊")
    @PostMapping("/dict/value/query")
    public ResponseDTO<PageResult<DictValueVO>> valueQuery(@Valid @RequestBody DictValueQueryForm queryForm) {
        return dictService.valueQuery(queryForm);
    }

    @Operation(summary = "数据字典-值列表- @author 罗伊")
    @GetMapping("/dict/value/list/{keyCode}")
    public ResponseDTO<List<DictValueVO>> valueList(@PathVariable String keyCode) {
        List<DictValueVO> dictValueVOList = dictCacheService.selectByKeyCode(keyCode);
        return ResponseDTO.ok(dictValueVOList);
    }
}
