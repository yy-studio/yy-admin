package com.yystudio.admin.module.business.law.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystudio.admin.module.business.law.domain.entity.ReadingRecordEntity;
import com.yystudio.admin.module.business.law.domain.form.ReadingRecordQueryForm;
import com.yystudio.admin.module.business.law.domain.vo.ReadingRecordVO;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface ReadingRecordDao extends BaseMapper<ReadingRecordEntity> {
    List<ReadingRecordVO> queryPage(Page page, @Param("queryForm") ReadingRecordQueryForm queryForm);

    void updateReadDuration(@Param("id") Long id, @Param("readDuration") Integer readDuration);

    void updateProgress(@Param("id") Long id, @Param("progress") Integer progress);
}
