package com.yystudio.base.module.support.dict.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.dict.domain.entity.DictKeyEntity;
import com.yystudio.base.module.support.dict.domain.form.DictKeyQueryForm;
import com.yystudio.base.module.support.dict.domain.vo.DictKeyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典
 *
 * @Author yy-studio
 * @Date 2022/5/26 19:40:55
 *
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface DictKeyDao extends BaseMapper<DictKeyEntity> {

    /**
     * 查找所有未删除的自带key
     */
    List<DictKeyEntity> selectByDeletedFlag(@Param("deletedFlag") Boolean deletedFlag);

    /**
     * 逻辑删除
     *
     */
    void updateDeletedFlagByIdList(@Param("dictKeyIdList") List<Long> dictKeyIdList, @Param("deletedFlag") Boolean deletedFlag);

    /**
     * 分页查询
     *
     */
    List<DictKeyVO> query(Page page, @Param("query") DictKeyQueryForm queryForm);

    /**
     * 跟进code查询
     */
    DictKeyEntity selectByCode(@Param("keyCode")String keyCode, @Param("deletedFlag") Boolean deletedFlag);
}
