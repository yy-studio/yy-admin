package com.yystudio.admin.module.business.law.dao;

import com.yystudio.admin.module.business.law.domain.entity.ContentEntity;
import com.yystudio.admin.module.business.law.domain.form.ContentQueryForm;
import com.yystudio.admin.module.business.law.domain.form.ContentUpdateForm;
import com.yystudio.admin.module.business.law.domain.vo.ContentVO;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 内容表 Dao
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@Mapper
@Component
public interface ContentDao extends BaseMapper<ContentEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<ContentVO> queryPage(Page page, @Param("queryForm") ContentQueryForm queryForm);

    /**
     * 更新删除状态
     */
    long updateDeleted(@Param("id")Long id,@Param("${deletedFlag}")boolean deletedFlag);
    /**
     * 批量更新删除状态
     */
    void batchUpdateDeleted(@Param("idList")List<Long> idList,@Param("${deletedFlag}")boolean deletedFlag);

    /**
     * 更新内容
     */
    int updateContent(ContentUpdateForm form);

    /**
     * 下线
     */
    void updateOffLine(Long id);
}
