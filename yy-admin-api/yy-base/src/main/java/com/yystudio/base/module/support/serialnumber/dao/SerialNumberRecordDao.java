package com.yystudio.base.module.support.serialnumber.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberRecordEntity;
import com.yystudio.base.module.support.serialnumber.domain.SerialNumberRecordQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * 单据序列号 生成的记录
 *
 * @Author yy-studio
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface SerialNumberRecordDao extends BaseMapper<SerialNumberRecordEntity> {

    /**
     * 根据 id和日期 查询 记录id
     *
     * @param serialNumberId
     * @param recordDate
     * @return
     */
    Long selectRecordIdBySerialNumberIdAndDate(@Param("serialNumberId") Integer serialNumberId, @Param("recordDate") String recordDate);

    /**
     * 更新记录
     *
     * @param serialNumberId
     * @param recordDate
     * @param lastNumber
     * @param count
     * @return
     */
    Long updateRecord(@Param("serialNumberId") Integer serialNumberId, @Param("recordDate") LocalDate recordDate, @Param("lastNumber") Long lastNumber, @Param("count") int count);

    /**
     * 分页查询记录
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<SerialNumberRecordEntity> query(Page page, @Param("queryForm") SerialNumberRecordQueryForm queryForm);
}
