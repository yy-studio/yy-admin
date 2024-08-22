package com.yystudio.base.module.support.helpdoc.manager;

import com.yystudio.base.module.support.helpdoc.domain.entity.HelpDocEntity;
import com.yystudio.base.module.support.helpdoc.dao.HelpDocDao;
import com.yystudio.base.module.support.helpdoc.domain.form.HelpDocRelationForm;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 帮助文档 manager
 *
 * @Author yy-studio
 * @Date 2022-08-20 23:11:42
 * 
 *
 * @Copyright  远游工作室
 */
@Service
public class HelpDocManager {

    @Resource
    private HelpDocDao helpDocDao;

    /**
     * 保存
     *
     * @param helpDocEntity
     * @param relationList
     */
    @Transactional(rollbackFor = Throwable.class)
    public void save(HelpDocEntity helpDocEntity, List<HelpDocRelationForm> relationList) {
        helpDocDao.insert(helpDocEntity);
        Long helpDocId = helpDocEntity.getHelpDocId();
        // 保存关联
        if (CollectionUtils.isNotEmpty(relationList)) {
            helpDocDao.insertRelation(helpDocId, relationList);
        }
    }

    /**
     * 更新
     *
     * @param helpDocEntity
     * @param relationList
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(HelpDocEntity helpDocEntity, List<HelpDocRelationForm> relationList) {
        helpDocDao.updateById(helpDocEntity);
        Long helpDocId = helpDocEntity.getHelpDocId();
        // 保存关联
        if (CollectionUtils.isNotEmpty(relationList)) {
            helpDocDao.deleteRelation(helpDocId);
            helpDocDao.insertRelation(helpDocId, relationList);
        }
    }
}
