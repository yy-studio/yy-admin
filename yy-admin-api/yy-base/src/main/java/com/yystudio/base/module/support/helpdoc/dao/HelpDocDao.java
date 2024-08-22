package com.yystudio.base.module.support.helpdoc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.helpdoc.domain.entity.HelpDocEntity;
import com.yystudio.base.module.support.helpdoc.domain.form.HelpDocQueryForm;
import com.yystudio.base.module.support.helpdoc.domain.form.HelpDocRelationForm;
import com.yystudio.base.module.support.helpdoc.domain.form.HelpDocViewRecordQueryForm;
import com.yystudio.base.module.support.helpdoc.domain.vo.HelpDocRelationVO;
import com.yystudio.base.module.support.helpdoc.domain.vo.HelpDocVO;
import com.yystudio.base.module.support.helpdoc.domain.vo.HelpDocViewRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 帮助文档 dao
 *
 * @Author yy-studio
 * @Date 2022-08-20 23:11:42
 *
 *
 * @Copyright  远游工作室
 */
@Mapper
@Component
public interface HelpDocDao extends BaseMapper<HelpDocEntity> {

    // ================================= 帮助文档【主表 t_help_doc 】   =================================


    /**
     * 查询 全部相关文档
     *
     * @return
     */
    List<HelpDocVO> queryAllHelpDocList();

    /**
     * 后管分页查询帮助文档
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<HelpDocVO> query(Page<?> page, @Param("query") HelpDocQueryForm queryForm);


    /**
     * 更新 阅读量
     * @param helpDocId
     * @param userViewCountIncrease
     * @param pageViewCountIncrease
     */
    void updateViewCount(@Param("helpDocId")Long helpDocId, @Param("userViewCountIncrease")Integer userViewCountIncrease,@Param("pageViewCountIncrease") Integer pageViewCountIncrease);


    /**
     * 根据目录，查询文档
     *
     * @param helpDocCatalogId
     * @return
     */
    List<HelpDocVO> queryHelpDocByCatalogId( @Param("helpDocCatalogId") Long helpDocCatalogId);

    /**
     * 根据关联文档id，查询文档
     *
     * @param relationId
     * @return
     */
    List<HelpDocVO> queryHelpDocByRelationId( @Param("relationId") Long relationId);

    // ================================= 关联项目 【子表 t_help_doc_relation 】 =================================

    /**
     * 保存 关联
     *
     * @param helpDocId
     * @param relationList
     */
    void insertRelation(@Param("helpDocId") Long helpDocId, @Param("relationList") List<HelpDocRelationForm> relationList);

    /**
     * 删除关联
     *
     * @param helpDocId
     */
    void deleteRelation(@Param("helpDocId") Long helpDocId);

    /**
     * 查询关联
     *
     * @param helpDocId
     */
    List<HelpDocRelationVO> queryRelationByHelpDoc(@Param("helpDocId") Long helpDocId);

    // ================================= 查看记录【子表 t_help_doc_view_record】   =================================

    /**
     * 查询某个用户的指定文档的阅读量
     * @param helpDocId
     * @param userId
     * @return
     */
    long  viewRecordCount(@Param("helpDocId")Long helpDocId, @Param("userId")Long userId);

    /**
     * 查询帮助文档的 查看记录
     * @param page
     * @param helpDocViewRecordQueryForm
     * @return
     */
    List<HelpDocViewRecordVO> queryViewRecordList(Page page, @Param("queryForm") HelpDocViewRecordQueryForm helpDocViewRecordQueryForm);

    /**
     * 保存查看记录
     * @param helpDocId
     * @param userId
     * @param userName
     * @param ip
     * @param userAgent
     */
    void insertViewRecord(@Param("helpDocId") Long helpDocId, @Param("userId") Long userId, @Param("userName") String userName, @Param("ip") String ip, @Param("userAgent") String userAgent,@Param("pageViewCount") Integer pageViewCount);

    /**
     * 更新查看记录
     * @param helpDocId
     * @param userId
     * @param ip
     * @param userAgent
     */
    void updateViewRecord(@Param("helpDocId")Long helpDocId, @Param("userId")Long userId,@Param("ip") String ip, @Param("userAgent")String userAgent);

}
