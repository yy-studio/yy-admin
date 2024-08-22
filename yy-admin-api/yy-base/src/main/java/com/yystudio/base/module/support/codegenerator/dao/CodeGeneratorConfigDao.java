package com.yystudio.base.module.support.codegenerator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystudio.base.module.support.codegenerator.domain.entity.CodeGeneratorConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 表的 代码生成配置 Dao
 * @Author yy-studio
 * @Date 2022-09-23 20:15:38
 * 
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface CodeGeneratorConfigDao extends BaseMapper<CodeGeneratorConfigEntity> {

}