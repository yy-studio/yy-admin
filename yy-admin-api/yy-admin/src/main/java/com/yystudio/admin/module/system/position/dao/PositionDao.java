package com.yystudio.admin.module.system.position.dao;

import java.util.List;

import com.yystudio.admin.module.system.position.domain.entity.PositionEntity;
import com.yystudio.admin.module.system.position.domain.form.PositionQueryForm;
import com.yystudio.admin.module.system.position.domain.vo.PositionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 职务表 Dao
 *
 * @Author kaiyun
 * @Date 2024-06-23 23:31:38
 * @Copyright 远游工作室
 */

@Mapper
@Component
public interface PositionDao extends BaseMapper<PositionEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<PositionVO> queryPage(Page page, @Param("queryForm") PositionQueryForm queryForm);


    /**
     * 查询
     * @param deletedFlag
     * @return
     */
    List<PositionVO> queryList(@Param("deletedFlag") Boolean deletedFlag);
}
