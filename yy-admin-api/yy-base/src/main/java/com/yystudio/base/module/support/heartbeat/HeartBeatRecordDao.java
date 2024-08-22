package com.yystudio.base.module.support.heartbeat;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.heartbeat.domain.HeartBeatRecordEntity;
import com.yystudio.base.module.support.heartbeat.domain.HeartBeatRecordQueryForm;
import com.yystudio.base.module.support.heartbeat.domain.HeartBeatRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 心跳记录
 *
 * @Author yy-studio
 * @Date 2022-01-09 20:57:24
 *
 *
 * @Copyright  远游工作室
 */
@Component
@Mapper
public interface HeartBeatRecordDao extends BaseMapper<HeartBeatRecordEntity> {

    /**
     * 更新心跳日志
     *
     * @param id
     * @param heartBeatTime
     */
    void updateHeartBeatTimeById(@Param("id") Long id, @Param("heartBeatTime") LocalDateTime heartBeatTime);

    /**
     * 查询心跳日志
     *
     * @param heartBeatRecordEntity
     * @return
     */
    HeartBeatRecordEntity query(HeartBeatRecordEntity heartBeatRecordEntity);

    /**
     * 分页查询
     * @param heartBeatRecordQueryForm
     * @return
     */
    List<HeartBeatRecordVO> pageQuery(Page page, @Param("query") HeartBeatRecordQueryForm heartBeatRecordQueryForm);
}
