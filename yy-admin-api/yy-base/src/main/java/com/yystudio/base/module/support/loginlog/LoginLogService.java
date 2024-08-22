package com.yystudio.base.module.support.loginlog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.module.support.loginlog.domain.LoginLogEntity;
import com.yystudio.base.module.support.loginlog.domain.LoginLogQueryForm;
import com.yystudio.base.module.support.loginlog.domain.LoginLogVO;
import lombok.extern.slf4j.Slf4j;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.enumeration.UserTypeEnum;
import com.yystudio.base.common.util.SmartPageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志
 *
 * @Author yy-studio
 * @Date 2022/07/22 19:46:23
 * 
 *
 * @Copyright  远游工作室
 */
@Service
@Slf4j
public class LoginLogService {

    @Resource
    private LoginLogDao loginLogDao;

    /**
     * @author 卓大
     * @description 分页查询
     */
    public ResponseDTO<PageResult<LoginLogVO>> queryByPage(LoginLogQueryForm queryForm) {
        Page page = SmartPageUtil.convert2PageQuery(queryForm);
        List<LoginLogVO> logList = loginLogDao.queryByPage(page, queryForm);
        PageResult<LoginLogVO> pageResult = SmartPageUtil.convert2PageResult(page, logList);
        return ResponseDTO.ok(pageResult);
    }

    /**
     * @author 卓大
     * @description 添加
     */
    public void log(LoginLogEntity loginLogEntity) {
        try {
            loginLogDao.insert(loginLogEntity);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 查询上一个登录记录
     *
     * @author 卓大
     * @description 查询上一个登录记录
     */
    public LoginLogVO queryLastByUserId(Long userId, UserTypeEnum userTypeEnum, LoginLogResultEnum loginLogResultEnum) {
        return loginLogDao.queryLastByUserId(userId,userTypeEnum.getValue(), loginLogResultEnum.getValue());
    }

}
