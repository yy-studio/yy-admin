package com.yystudio.admin.module.business.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.admin.module.business.goods.domain.form.GoodsQueryForm;
import com.yystudio.admin.module.business.goods.domain.vo.GoodsVO;
import com.yystudio.admin.module.business.goods.domain.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品
 *
 * @Author yy-studio
 * @Date 2021-10-25 20:26:54
 *
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface GoodsDao extends BaseMapper<GoodsEntity> {

    /**
     * 分页 查询商品
     *
     */
    List<GoodsVO> query(Page page, @Param("query") GoodsQueryForm query);

    /**
     * 批量更新删除状态
     */

    void batchUpdateDeleted(@Param("goodsIdList")List<Long> goodsIdList,@Param("deletedFlag")Boolean deletedFlag);
}
