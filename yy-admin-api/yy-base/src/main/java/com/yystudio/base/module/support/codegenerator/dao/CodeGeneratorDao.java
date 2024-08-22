package com.yystudio.base.module.support.codegenerator.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.codegenerator.domain.form.TableQueryForm;
import com.yystudio.base.module.support.codegenerator.domain.vo.TableColumnVO;
import com.yystudio.base.module.support.codegenerator.domain.vo.TableVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author yy-studio
 * @Date 2022-06-30 22:15:38
 *
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface CodeGeneratorDao {

    /**
     * 分页查询表
     */
    List<TableVO> queryTableList(Page page, @Param("queryForm") TableQueryForm queryForm);

    /**
     * 查询表是否存在
     *
     * @param tableName
     * @return
     */
    long countByTableName(@Param("tableName") String tableName);


    /**
     * 查询表列信息
     *
     * @param tableName
     * @return
     */
    List<TableColumnVO> selectTableColumn(@Param("tableName") String tableName);
}