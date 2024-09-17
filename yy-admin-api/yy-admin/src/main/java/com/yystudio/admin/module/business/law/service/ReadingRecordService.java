package com.yystudio.admin.module.business.law.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.admin.module.business.law.dao.ReadingRecordDao;
import com.yystudio.admin.module.business.law.domain.form.ContentQueryForm;
import com.yystudio.admin.module.business.law.domain.form.ReadingRecordQueryForm;
import com.yystudio.admin.module.business.law.domain.vo.ContentVO;
import com.yystudio.admin.module.business.law.domain.vo.ReadingRecordVO;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.util.SmartPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ReadingRecordService {

    @Resource
    private ReadingRecordDao readingRecordDao;

    public PageResult<ReadingRecordVO> queryPage(ReadingRecordQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ReadingRecordVO> list = readingRecordDao.queryPage(page, queryForm);
        PageResult<ReadingRecordVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }
}
