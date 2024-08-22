package com.yystudio.base.module.support.serialnumber.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 单据序列号
 *
 * @Author yy-studio
 * @Date 2022-03-25 21:46:07
 *
 * 
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface SerialNumberDao extends BaseMapper<SerialNumberEntity> {

    /**
     * 排他锁查询
     *
     * @param serialNumberId
     * @return
     */
    SerialNumberEntity selectForUpdate(@Param("serialNumberId") Integer serialNumberId);

    /**
     * 更新上一次的 数值和时间
     *
     * @param serialNumberId
     * @param lastNumber
     * @param lastTime
     */
    void updateLastNumberAndTime(@Param("serialNumberId") Integer serialNumberId, @Param("lastNumber") Long lastNumber, @Param("lastTime") LocalDateTime lastTime);

}
