package com.yystudio.base.module.support.helpdoc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystudio.base.module.support.helpdoc.domain.entity.HelpDocCatalogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 帮助文档目录
 *
 * @Author yy-studio
 * @Date 2022-08-20 23:11:42
 * 
 * 
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface HelpDocCatalogDao extends BaseMapper<HelpDocCatalogEntity> {

}
