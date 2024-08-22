package com.yystudio.admin.module.business.oa.notice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yystudio.admin.module.business.oa.notice.domain.entity.NoticeTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 通知公告类型
 *
 * @Author yy-studio
 * @Date 2022-08-12 21:40:39
 * 
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface NoticeTypeDao extends BaseMapper<NoticeTypeEntity> {

}
