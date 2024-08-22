package com.yystudio.base.module.support.reload.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystudio.base.module.support.reload.domain.ReloadResultEntity;
import com.yystudio.base.module.support.reload.domain.ReloadResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * t_reload_result 数据表dao
 *
 * @Author yy-studio
 * @Date 2015-03-02 19:11:52
 *
 *
 * @Copyright  远游工作室
 */
@Component
@Mapper
public interface ReloadResultDao extends BaseMapper<ReloadResultEntity> {

    List<ReloadResultVO> query(@Param("tag") String tag);
}
