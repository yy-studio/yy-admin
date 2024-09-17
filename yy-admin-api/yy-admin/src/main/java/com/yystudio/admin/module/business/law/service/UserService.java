package com.yystudio.admin.module.business.law.service;

import com.yystudio.admin.module.business.law.constant.UserStatusEnum;
import com.yystudio.admin.module.business.law.dao.UserDao;
import com.yystudio.admin.module.business.law.domain.entity.UserEntity;
import com.yystudio.admin.module.business.law.domain.form.UserAddForm;
import com.yystudio.admin.module.business.law.domain.form.UserQueryForm;
import com.yystudio.admin.module.business.law.domain.form.UserUpdateForm;
import com.yystudio.admin.module.business.law.domain.vo.UserVO;
import java.util.List;

import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.common.util.SmartPageUtil;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.domain.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.redis.RedisService;
import com.yystudio.base.module.support.sms.SmsService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表 Service
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private SmsService smsService;

    @Resource
    private RedisService redisService;

    /**
     * 分页查询
     *
     * @param queryForm
     * @return
     */
    public PageResult<UserVO> queryPage(UserQueryForm queryForm) {
        Page<?> page = SmartPageUtil.convert2PageQuery(queryForm);
        List<UserVO> list = userDao.queryPage(page, queryForm);
        PageResult<UserVO> pageResult = SmartPageUtil.convert2PageResult(page, list);
        return pageResult;
    }

    /**
     * 添加
     */
    public ResponseDTO<String> add(UserAddForm addForm) {
        if(StringUtils.isBlank(addForm.getMobile()) && StringUtils.isBlank(addForm.getEmail())){
            return ResponseDTO.userErrorParam("手机号和邮箱不能同时为空");
        }
        // 检查是否存在
        if(StringUtils.isNotBlank(addForm.getMobile())){
            UserEntity userEntity = userDao.getByMobile(addForm.getMobile(), null);
            if (null != userEntity) {
                return ResponseDTO.userErrorParam("手机号已存在");
            }
        }
        if(StringUtils.isNotBlank(addForm.getEmail())){
            UserEntity userEntity = userDao.getByEmail(addForm.getEmail(), null);
            if (null != userEntity) {
                return ResponseDTO.userErrorParam("邮箱已存在");
            }
        }
        if(StringUtils.isNotBlank(addForm.getUsername())){
            UserEntity userEntity = userDao.getByLoginName(addForm.getUsername(), null);
            if (null != userEntity) {
                return ResponseDTO.userErrorParam("昵称已存在");
            }
        }

        UserEntity newUserEntity = SmartBeanUtil.copy(addForm, UserEntity.class);
        userDao.insert(newUserEntity);
        return ResponseDTO.ok(addForm.getMobile());
    }

    /**
     * 更新
     *
     * @param updateForm
     * @return
     */
    public ResponseDTO<String> update(UserUpdateForm updateForm) {

        // 检查是否存在
        UserEntity userEntity = userDao.getByLoginName(updateForm.getUsername(), updateForm.getId());
        if (null != userEntity) {
            return ResponseDTO.userErrorParam("用户名重复");
        }
        userEntity = userDao.getByEmail(updateForm.getEmail(), updateForm.getId());
        if (null != userEntity) {
            return ResponseDTO.userErrorParam("邮箱重复");
        }
        userEntity = userDao.getByMobile(updateForm.getMobile(), updateForm.getId());
        if (null != userEntity) {
            return ResponseDTO.userErrorParam("手机号重复");
        }
        userDao.updateUser(updateForm);
        return ResponseDTO.ok();
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

        userDao.deleteBatchIds(idList);
        return ResponseDTO.ok();
    }

    /**
     * 单个注销用户
     */
    public ResponseDTO<String> delete(Long id) {
        if (null == id){
            return ResponseDTO.ok();
        }

        userDao.updateStatus(id, UserStatusEnum.CANCEL.getValue());
        return ResponseDTO.ok();
    }

    public void updateAvatar(Long userId, String fileUrl) {
        userDao.updateAvatar(userId, fileUrl);
    }
}
