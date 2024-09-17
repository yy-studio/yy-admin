package com.yystudio.admin.module.business.law.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yystudio.admin.module.business.law.dao.ContentCollectDao;
import com.yystudio.admin.module.business.law.dao.ContentDao;
import com.yystudio.admin.module.business.law.dao.ReadingRecordDao;
import com.yystudio.admin.module.business.law.dao.UserDao;
import com.yystudio.admin.module.business.law.domain.entity.ContentCollectEntity;
import com.yystudio.admin.module.business.law.domain.entity.ContentEntity;
import com.yystudio.admin.module.business.law.domain.entity.ReadingRecordEntity;
import com.yystudio.admin.module.business.law.domain.entity.UserEntity;
import com.yystudio.admin.module.business.law.domain.form.*;
import com.yystudio.admin.module.business.law.domain.vo.AppContentDetailVO;
import com.yystudio.admin.module.business.law.domain.vo.ContentDetailVO;
import com.yystudio.admin.module.business.law.domain.vo.ContentVO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.yystudio.admin.module.business.law.domain.vo.ReadingRecordVO;
import com.yystudio.base.common.code.UserErrorCode;
import com.yystudio.base.common.domain.RequestUser;
import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.common.util.SmartPageUtil;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.common.util.SmartRequestUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @Resource
    private ReadingRecordDao readingRecordDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ContentCollectDao contentCollectDao;
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

    /**
     * APP分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<ContentVO> queryAppPage(ContentQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<ContentVO> list = contentDao.queryAppPage(page, queryForm);
        PageResult<ContentVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    public ResponseDTO<AppContentDetailVO> detailForApp(Long id) {
        RequestUser requestUser = SmartRequestUtil.getRequestUser();
        ContentEntity contentEntity = contentDao.selectById(id);

        if (contentEntity.getStatus().equals(0)) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }

        contentDao.updateViews(id);
        AppContentDetailVO contentDetailVO = createContentDetailVO(contentEntity);
        int readType = determineReadType(requestUser, contentEntity, contentDetailVO);

        if (requestUser != null) {
            insertReadingRecord(requestUser.getUserId(), id, readType);
        }

        if (contentDetailVO.getDisplayType() == 0) {
            String content = contentDetailVO.getContent().replaceAll("<[^>]*>", "");
            if(content.length() > 100){
                contentDetailVO.setContent(content.substring(0, 100));
            } else {
                contentDetailVO.setContent(content);
            }
        }

        return ResponseDTO.ok(contentDetailVO);
    }

    private AppContentDetailVO createContentDetailVO(ContentEntity contentEntity) {
        AppContentDetailVO contentDetailVO = SmartBeanUtil.copy(contentEntity, AppContentDetailVO.class);
        String content = contentDetailVO.getContent().replaceAll("<[^>]*>", "");
        contentDetailVO.setWordCount(content.length());
        contentDetailVO.setReadTime(content.length() / 200);
        return contentDetailVO;
    }

    private int determineReadType(RequestUser requestUser, ContentEntity contentEntity, AppContentDetailVO contentDetailVO) {
        int readType = 0;

        if (requestUser == null) {
            contentDetailVO.setDisplayType(contentEntity.getType().equals(1) ? 0 : 1);
        } else {
            if (contentEntity.getType().equals(1)) {
                readType = handlePaidContent(requestUser, contentEntity, contentDetailVO);
            } else {
                contentDetailVO.setDisplayType(1);
            }
        }

        return readType;
    }

    private int handlePaidContent(RequestUser requestUser, ContentEntity contentEntity, AppContentDetailVO contentDetailVO) {
        int readType = 0;
        List<ReadingRecordEntity> readingRecordList = readingRecordDao.selectList(
                new LambdaQueryWrapper<ReadingRecordEntity>()
                        .eq(ReadingRecordEntity::getContentId, contentEntity.getId())
                        .eq(ReadingRecordEntity::getUserId, requestUser.getUserId())
                        .eq(ReadingRecordEntity::getReadType, 1)
        );

        if (CollectionUtils.isEmpty(readingRecordList)) {
            UserEntity user = userDao.selectById(requestUser.getUserId());
            if (user.getRemainingTimes() > 0) {
                readType = 1;
                contentDetailVO.setDisplayType(1);
                userDao.updateRemainingTimes(requestUser.getUserId());
            } else if (user.getSubscriptionDeadline() != null && user.getSubscriptionDeadline().isAfter(LocalDateTime.now())) {
                readType = 1;
                contentDetailVO.setDisplayType(1);
            } else {
                contentDetailVO.setDisplayType(0);
            }
        } else {
            readType = 1;
            contentDetailVO.setDisplayType(1);
        }

        return readType;
    }

    private void insertReadingRecord(Long userId, Long contentId, int readType) {
        ReadingRecordEntity readingRecordEntity = new ReadingRecordEntity();
        readingRecordEntity.setUserId(userId);
        readingRecordEntity.setContentId(contentId);
        readingRecordEntity.setReadType(readType);
        readingRecordDao.insert(readingRecordEntity);
    }

    public ResponseDTO<String> collect(@Valid ContentCollectForm addForm) {
        // 先判断是否已经收藏
        List<ContentCollectEntity> contentCollectList = contentCollectDao.selectList(
                new LambdaQueryWrapper<ContentCollectEntity>()
                        .eq(ContentCollectEntity::getContentId, addForm.getContentId())
                        .eq(ContentCollectEntity::getUserId, addForm.getUserId())
        );
        if (CollectionUtils.isNotEmpty(contentCollectList)) {
            return ResponseDTO.ok("已收藏");
        }
        contentCollectDao.insert(SmartBeanUtil.copy(addForm, ContentCollectEntity.class));
        contentDao.updateCollections(addForm.getContentId());
        return ResponseDTO.ok("收藏成功");
    }
//
//    public ResponseDTO<AppContentDetailVO> detailForApp(Long id) {
//
//        RequestUser requestUser = SmartRequestUtil.getRequestUser();
//        ContentEntity contentEntity = contentDao.selectById(id);
//        if(contentEntity.getStatus().equals(0)){
//            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
//        }
//        // 更新阅读量
//        contentDao.updateViews(id);
//
//        AppContentDetailVO contentDetailVO = SmartBeanUtil.copy(contentEntity, AppContentDetailVO.class);
//        // 计算字数， 移除 HTML 标签
//        String content = contentDetailVO.getContent().replaceAll("<[^>]*>", "");
//        contentDetailVO.setWordCount(content.length());
//        // 计算阅读时间，假设平均阅读速度为 200 字/分钟
//        int readTime = content.length() / 200;
//        contentDetailVO.setReadTime(readTime);
//
//        // 如果是付费文章，判断用户是否有权限阅读
//        int readType = 0; //阅读类型（1付费阅读，0免费阅读）
//        if(null == requestUser){
//            // 未登录用户
//            if (contentEntity.getType().equals(1)) {
//                // 付费文章，只能看部分内容
//                contentDetailVO.setDisplayType(0);
//            } else {
//                // 免费文章，文章内容全部展示
//                contentDetailVO.setDisplayType(1);
//            }
//
//        } else {
//            // 登录用户
//            if (contentEntity.getType().equals(1)) {
//                // 查询用户是否有付费阅读记录
//                List<ReadingRecordEntity> readingRecordList = readingRecordDao.selectList(
//                        new LambdaQueryWrapper<ReadingRecordEntity>()
//                                .eq(ReadingRecordEntity::getContentId, id)
//                                .eq(ReadingRecordEntity::getUserId, requestUser.getUserId())
//                                .eq(ReadingRecordEntity::getReadType, 1)
//                );
//                if (CollectionUtils.isEmpty(readingRecordList)) {
//                    // 没有付费阅读记录
//                    // 判断用户是否还有剩余阅读次数
//                    UserEntity user = userDao.selectById(requestUser.getUserId());
//                    if(user.getRemainingTimes() > 0){
//                        // 有剩余阅读次数
//                        readType = 1;
//                        contentDetailVO.setDisplayType(1);
//                        // 更新用户剩余阅读次数
//                        userDao.updateRemainingTimes(requestUser.getUserId());
//                        // TODO 更新t_subscription表中的已使用次数？
//                    } else {
//                        // 没有剩余阅读次数，判断订阅截止时间
//                        if(user.getSubscriptionDeadline() != null && user.getSubscriptionDeadline().isAfter(LocalDateTime.now())){
//                            // 在订阅期内
//                            readType = 1;
//                            contentDetailVO.setDisplayType(1);
//                        } else {
//                            // 不在订阅期内
//                            contentDetailVO.setDisplayType(0);
//                        }
//                    }
//
//                } else {
//                    // 有付费阅读记录，文章内容全部展示
//                    readType = 1;
//                    contentDetailVO.setDisplayType(1);
//                }
//            } else {
//                // 免费文章，文章内容全部展示
//                contentDetailVO.setDisplayType(1);
//            }
//
//            // 插入阅读记录表t_reading_record
//            ReadingRecordEntity readingRecordEntity = new ReadingRecordEntity();
//            readingRecordEntity.setUserId(requestUser.getUserId());
//            readingRecordEntity.setContentId(id);
//            readingRecordEntity.setReadType(readType);
//            readingRecordDao.insert(readingRecordEntity);
//        }
//        if(contentDetailVO.getDisplayType() == 0){
//            // 付费文章，只能看部分内容
//            // TODO 保留HTML标签？
//            contentDetailVO.setContent(content.substring(0, 100));
//        }
//
//        return ResponseDTO.ok(contentDetailVO);
//    }
}
