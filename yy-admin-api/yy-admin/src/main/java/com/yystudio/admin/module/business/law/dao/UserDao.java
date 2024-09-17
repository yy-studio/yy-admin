package com.yystudio.admin.module.business.law.dao;

import com.yystudio.admin.module.business.law.domain.entity.UserEntity;
import com.yystudio.admin.module.business.law.domain.form.UserQueryForm;
import com.yystudio.admin.module.business.law.domain.form.UserUpdateForm;
import com.yystudio.admin.module.business.law.domain.vo.UserVO;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 用户表 Dao
 *
 * @Author yy
 * @Date 2024-08-24 16:07:47
 * @Copyright yy-studio
 */

@Mapper
@Component
public interface UserDao extends BaseMapper<UserEntity> {

    /**
     * 分页 查询
     *
     * @param page
     * @param queryForm
     * @return
     */
    List<UserVO> queryPage(Page page, @Param("queryForm") UserQueryForm queryForm);


    UserEntity getByLoginName(@Param("username") String username, @Param("userId") Long userId);

    UserEntity getByEmail(@Param("email") String email, @Param("userId") Long userId);

    UserEntity getByMobile(@Param("mobile") String mobile, @Param("userId") Long userId);

    int updateUser(@Param("form") UserUpdateForm form);

    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    void updatePassword(@Param("id") Long id, @Param("password") String password);

    void updateRemainingTimes(@Param("id")Long userId);

    void updateAvatar(@Param("id")Long userId, @Param("avatar")String fileUrl);
}
