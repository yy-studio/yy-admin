package com.yystudio.base.module.support.serialnumber.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.serialnumber.dao.SerialNumberRecordDao;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberRecordEntity;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberRecordQueryForm;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 单据序列号 记录
 *
 * @Author yy-studio
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  远游工作室
 */
@Service
public class SerialNumberRecordService {

    @Resource
    private SerialNumberRecordDao serialNumberRecordDao;

    public PageResult<SerialNumberRecordEntity> query(SerialNumberRecordQueryForm queryForm) {
        Page page = SmartPageUtil.convert2PageQuery(queryForm);
        List<SerialNumberRecordEntity> recordList = serialNumberRecordDao.query(page, queryForm);
        return SmartPageUtil.convert2PageResult(page, recordList);
    }
}
