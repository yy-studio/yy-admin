package com.yystudio.base.module.support.operatelog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.operatelog.domain.OperateLogEntity;
import com.yystudio.base.module.support.operatelog.domain.OperateLogQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  操作日志
 *
 * @Author yy-studio
 * @Date 2021-12-08 20:48:52
 *
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface OperateLogDao extends BaseMapper<OperateLogEntity> {

    /**
     * 分页查询
     * @param page
     * @param queryForm
     * @return UserOperateLogEntity
     */
    List<OperateLogEntity> queryByPage(Page page, @Param("query") OperateLogQueryForm queryForm);


    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    void deleteByIds(@Param("idList") List<Long> idList);
}
