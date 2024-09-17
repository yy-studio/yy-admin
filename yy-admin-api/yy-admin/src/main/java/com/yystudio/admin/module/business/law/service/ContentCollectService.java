package com.yystudio.admin.module.business.law.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yystudio.admin.module.business.law.dao.ContentCollectDao;
import com.yystudio.admin.module.business.law.domain.entity.ContentCollectEntity;
import com.yystudio.admin.module.business.law.domain.form.CollectRecordQueryForm;
import com.yystudio.admin.module.business.law.domain.vo.ContentCollectVO;
import com.yystudio.admin.module.business.law.domain.vo.ReadingRecordVO;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.util.SmartPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Service
public class ContentCollectService extends ServiceImpl<ContentCollectDao, ContentCollectEntity> {
    @Resource
    private ContentCollectDao contentCollectDao;

    public PageResult<ContentCollectVO> queryCollectPage(@Valid CollectRecordQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ContentCollectVO> list = contentCollectDao.queryPage(page, queryForm);
        PageResult<ContentCollectVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;

    }
}
