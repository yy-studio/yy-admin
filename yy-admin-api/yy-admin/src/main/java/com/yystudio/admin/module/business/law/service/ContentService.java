package com.yystudio.admin.module.business.law.service;

import com.yystudio.admin.module.business.law.dao.ContentDao;
import com.yystudio.admin.module.business.law.domain.entity.ContentEntity;
import com.yystudio.admin.module.business.law.domain.form.ContentAddForm;
import com.yystudio.admin.module.business.law.domain.form.ContentQueryForm;
import com.yystudio.admin.module.business.law.domain.form.ContentUpdateForm;
import com.yystudio.admin.module.business.law.domain.vo.ContentDetailVO;
import com.yystudio.admin.module.business.law.domain.vo.ContentVO;
import java.util.List;

import com.yystudio.base.common.code.UserErrorCode;
import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.common.util.SmartPageUtil;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 内容表 Service
 *
 * @Author yy
 * @Date 2024-08-24 16:08:00
 * @Copyright yy-studio
 */

@Service
public class ContentService {

    @Resource
    private ContentDao contentDao;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ContentVO> queryPage(ContentQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ContentVO> list = contentDao.queryPage(page, queryForm);
        PageResult<ContentVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(ContentAddForm addForm) {
        ContentEntity contentEntity = SmartBeanUtil.copy(addForm, ContentEntity.class);
        contentEntity.setStatus(1);
        contentDao.insert(contentEntity);
        return ResponseDTO.ok();
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(ContentUpdateForm updateForm) {
        int rowsAffected = contentDao.updateContent(updateForm);
        if( rowsAffected > 0){
            return ResponseDTO.ok();
        } else {
            return ResponseDTO.error(UserErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    public ResponseDTO<String> batchDelete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)){
            return ResponseDTO.ok();
        }

        contentDao.batchUpdateDeleted(idList, true);
        return ResponseDTO.ok();
    }

    /**
     * 单个删除
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        contentDao.updateDeleted(id,true);
        return ResponseDTO.ok();
    }

    public ResponseDTO<ContentDetailVO> detail(Long id) {
        ContentEntity contentEntity = contentDao.selectById(id);
        ContentDetailVO contentDetailVO = SmartBeanUtil.copy(contentEntity, ContentDetailVO.class);
        return ResponseDTO.ok(contentDetailVO);
    }

    public ResponseDTO<String> offLine(Long id) {
        contentDao.updateOffLine(id);
        return ResponseDTO.ok();
    }
}
