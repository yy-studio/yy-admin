package com.yystudio.base.module.support.dict.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.yystudio.base.common.code.UserErrorCode;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.common.util.SmartPageUtil;
import com.yystudio.base.module.support.dict.dao.DictKeyDao;
import com.yystudio.base.module.support.dict.domain.entity.DictKeyEntity;
import com.yystudio.base.module.support.dict.domain.entity.DictValueEntity;
import com.yystudio.base.module.support.dict.domain.form.*;
import com.yystudio.base.module.support.dict.dao.DictValueDao;
import com.yystudio.base.module.support.dict.domain.form.*;
import com.yystudio.base.module.support.dict.domain.vo.DictKeyVO;
import com.yystudio.base.module.support.dict.domain.vo.DictValueVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典 服务
 *
 * @Author yy-studio
 * @Date 2022/5/26 19:40:55
 *
 *
 * @Copyright  远游工作室
 */
@Service
public class DictService {

    @Resource
    private DictKeyDao dictKeyDao;
    @Resource
    private DictValueDao dictValueDao;
    @Resource
    private DictCacheService dictCacheService;
    /**
     * CODE锁
     */
    private static final Interner<String> CODE_POOL = Interners.newWeakInterner();


    /**
     * key添加
     *
     * @param keyAddForm
     * @return
     */
    public ResponseDTO<String> keyAdd(DictKeyAddForm keyAddForm) {
        synchronized (CODE_POOL.intern(keyAddForm.getKeyCode())) {
            DictKeyEntity dictKeyEntity = dictKeyDao.selectByCode(keyAddForm.getKeyCode(), false);
            if (dictKeyEntity != null) {
                return ResponseDTO.error(UserErrorCode.ALREADY_EXIST);
            }
            dictKeyEntity = SmartBeanUtil.copy(keyAddForm, DictKeyEntity.class);
            dictKeyDao.insert(dictKeyEntity);
        }
        return ResponseDTO.ok();
    }

    /**
     * 值添加
     *
     * @param valueAddForm
     * @return
     */
    public ResponseDTO<String> valueAdd(DictValueAddForm valueAddForm) {
        synchronized (CODE_POOL.intern(valueAddForm.getValueCode())) {
            DictValueEntity dictValueEntity = dictValueDao.selectByCode(valueAddForm.getValueCode(), false);
            if (dictValueEntity != null) {
                return ResponseDTO.error(UserErrorCode.ALREADY_EXIST);
            }
            dictValueEntity = SmartBeanUtil.copy(valueAddForm, DictValueEntity.class);
            dictValueDao.insert(dictValueEntity);
        }
        return ResponseDTO.ok();
    }

    /**
     * key 编辑
     *
     * @param keyUpdateForm
     * @return
     */
    public ResponseDTO<String> keyEdit(DictKeyUpdateForm keyUpdateForm) {
        synchronized (CODE_POOL.intern(keyUpdateForm.getKeyCode())) {
            DictKeyEntity dictKeyEntity = dictKeyDao.selectByCode(keyUpdateForm.getKeyCode(), false);
            if (dictKeyEntity != null && !dictKeyEntity.getDictKeyId().equals(keyUpdateForm.getDictKeyId())) {
                return ResponseDTO.error(UserErrorCode.ALREADY_EXIST);
            }
            DictKeyEntity dictKeyUpdateEntity = SmartBeanUtil.copy(keyUpdateForm, DictKeyEntity.class);
            dictKeyDao.updateById(dictKeyUpdateEntity);
        }
        return ResponseDTO.ok();
    }

    /**
     * 值编辑
     *
     * @param valueUpdateForm
     * @return
     */
    public ResponseDTO<String> valueEdit(DictValueUpdateForm valueUpdateForm) {
        DictKeyEntity dictKeyEntity = dictKeyDao.selectById(valueUpdateForm.getDictKeyId());
        if (dictKeyEntity == null || dictKeyEntity.getDeletedFlag()) {
            return ResponseDTO.userErrorParam("key不能存在");
        }
        synchronized (CODE_POOL.intern(valueUpdateForm.getValueCode())) {
            DictValueEntity dictValueEntity = dictValueDao.selectByCode(valueUpdateForm.getValueCode(), false);
            if (dictValueEntity != null && !dictValueEntity.getDictValueId().equals(valueUpdateForm.getDictValueId())) {
                return ResponseDTO.error(UserErrorCode.ALREADY_EXIST);
            }
            DictValueEntity dictValueUpdateEntity = SmartBeanUtil.copy(valueUpdateForm, DictValueEntity.class);
            dictValueDao.updateById(dictValueUpdateEntity);
        }
        return ResponseDTO.ok();
    }

    /**
     * key删除
     *
     * @param keyIdList
     * @return
     */
    public ResponseDTO<String> keyDelete(List<Long> keyIdList) {
        if (CollectionUtils.isEmpty(keyIdList)) {
            return ResponseDTO.ok();
        }
        dictKeyDao.updateDeletedFlagByIdList(keyIdList, true);
        return ResponseDTO.ok();
    }

    /**
     * 值删除
     *
     * @param valueIdList
     * @return
     */
    public ResponseDTO<String> valueDelete(List<Long> valueIdList) {
        if (CollectionUtils.isEmpty(valueIdList)) {
            return ResponseDTO.ok();
        }
        dictValueDao.updateDeletedFlagByIdList(valueIdList, true);
        return ResponseDTO.ok();
    }

    /**
     * 分页查询key
     *
     * @param queryForm
     * @return
     */
    public ResponseDTO<PageResult<DictKeyVO>> keyQuery(DictKeyQueryForm queryForm) {
        queryForm.setDeletedFlag(false);
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<DictKeyVO> list = dictKeyDao.query(page, queryForm);
        PageResult<DictKeyVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        if (pageResult.getEmptyFlag()) {
            return ResponseDTO.ok(pageResult);
        }
        return ResponseDTO.ok(pageResult);
    }

    /**
     * 所有key
     *
     * @return
     */
    public List<DictKeyVO> queryAllKey() {
        return SmartBeanUtil.copyList(dictKeyDao.selectList(null), DictKeyVO.class);
    }

    /**
     * 分页查询值
     *
     * @param queryForm
     * @return
     */
    public ResponseDTO<PageResult<DictValueVO>> valueQuery(DictValueQueryForm queryForm) {
        queryForm.setDeletedFlag(false);
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<DictValueVO> list = dictValueDao.query(page, queryForm);
        PageResult<DictValueVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        if (pageResult.getEmptyFlag()) {
            return ResponseDTO.ok(pageResult);
        }
        return ResponseDTO.ok(pageResult);
    }

    public List<DictValueVO> queryTabBarList() {
        LambdaQueryWrapper<DictValueEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(DictValueEntity::getValueCode, "tb10");
        queryWrapper.orderByAsc(DictValueEntity::getSort);
        List<DictValueEntity> dictValueEntityList = dictValueDao.selectList(queryWrapper);
        return SmartBeanUtil.copyList(dictValueEntityList, DictValueVO.class);
    }
}