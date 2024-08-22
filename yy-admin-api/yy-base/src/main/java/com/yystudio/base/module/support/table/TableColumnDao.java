package com.yystudio.base.module.support.table;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystudio.base.module.support.table.domain.TableColumnEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 表格自定义列（前端用户自定义表格列，并保存到数据库里）
 *
 * @Author yy-studio
 * @Date 2022-08-12 22:52:21
 *
 *
 * @Copyright  远游工作室
 */
@Mapper
public interface TableColumnDao extends BaseMapper<TableColumnEntity> {

    TableColumnEntity selectByUserIdAndTableId(@Param("userId") Long userId, @Param("userType") Integer userType, @Param("tableId") Integer tableId);

    void deleteTableColumn(@Param("userId") Long userId, @Param("userType") Integer userType, @Param("tableId") Integer tableId);
}
