package com.yystudio.base.module.support.datatracer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.datatracer.domain.vo.DataTracerVO;
import com.yystudio.base.module.support.datatracer.domain.entity.DataTracerEntity;
import com.yystudio.base.module.support.datatracer.domain.form.DataTracerQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * dao： t_data_tracker
 *
 * @Author yy-studio
 * @Date 2022-07-23 19:38:52
 *
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface DataTracerDao extends BaseMapper<DataTracerEntity> {

    /**
     * 操作记录查询
     *
     */
    List<DataTracerVO> selectRecord(@Param("dataId") Long dataId, @Param("dataType") Integer dataType);

    /**
     * 分页查询
     *
     */
    List<DataTracerVO> query(Page page, @Param("query") DataTracerQueryForm queryForm);
}
