package com.yystudio.admin.module.business.law.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.admin.module.business.law.domain.entity.ContentCollectEntity;
import com.yystudio.admin.module.business.law.domain.form.CollectRecordQueryForm;
import com.yystudio.admin.module.business.law.domain.vo.ContentCollectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.Valid;
import java.util.List;

@Mapper
public interface ContentCollectDao extends BaseMapper<ContentCollectEntity> {
    List<ContentCollectVO> queryPage(Page<?> page, @Param("queryForm") CollectRecordQueryForm queryForm);

}
