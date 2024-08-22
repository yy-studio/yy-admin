package com.yystudio.admin.module.system.support;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.yystudio.base.common.controller.SupportBaseController;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartEnumUtil;
import com.yystudio.base.constant.SwaggerTagConst;
import com.yystudio.base.module.support.serialnumber.constant.SerialNumberIdEnum;
import com.yystudio.base.module.support.serialnumber.dao.SerialNumberDao;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberEntity;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberGenerateForm;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberRecordEntity;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberRecordQueryForm;
import com.yystudio.base.module.support.serialnumber.service.SerialNumberRecordService;
import com.yystudio.base.module.support.serialnumber.service.SerialNumberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 单据序列号
 *
 * @Author yy-studio
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  远游工作室
 */
@Tag(name = SwaggerTagConst.Support.SERIAL_NUMBER)
@RestController
public class AdminSerialNumberController extends SupportBaseController {

    @Resource
    private SerialNumberDao serialNumberDao;

    @Resource
    private SerialNumberService serialNumberService;

    @Resource
    private SerialNumberRecordService serialNumberRecordService;

    @Operation(summary = "生成单号 @author 卓大")
    @PostMapping("/serialNumber/generate")
    @SaCheckPermission("support:serialNumber:generate")
    public ResponseDTO<List<String>> generate(@RequestBody @Valid SerialNumberGenerateForm generateForm) {
        SerialNumberIdEnum serialNumberIdEnum = SmartEnumUtil.getEnumByValue(generateForm.getSerialNumberId(), SerialNumberIdEnum.class);
        if (null == serialNumberIdEnum) {
            return ResponseDTO.userErrorParam("SerialNumberId，不存在" + generateForm.getSerialNumberId());
        }
        return ResponseDTO.ok(serialNumberService.generate(serialNumberIdEnum, generateForm.getCount()));
    }

    @Operation(summary = "获取所有单号定义 @author 卓大")
    @GetMapping("/serialNumber/all")
    public ResponseDTO<List<SerialNumberEntity>> getAll() {
        return ResponseDTO.ok(serialNumberDao.selectList(null));
    }

    @Operation(summary = "获取生成记录 @author 卓大")
    @PostMapping("/serialNumber/queryRecord")
    @SaCheckPermission("support:serialNumber:record")
    public ResponseDTO<PageResult<SerialNumberRecordEntity>> queryRecord(@RequestBody @Valid SerialNumberRecordQueryForm queryForm) {
        return ResponseDTO.ok(serialNumberRecordService.query(queryForm));
    }

}
