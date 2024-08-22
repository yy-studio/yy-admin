package com.yystudio.base.module.support.operatelog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yystudio.base.common.code.UserErrorCode;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.common.util.SmartPageUtil;
import com.yystudio.base.module.support.operatelog.domain.OperateLogEntity;
import com.yystudio.base.module.support.operatelog.domain.OperateLogQueryForm;
import com.yystudio.base.module.support.operatelog.domain.OperateLogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  操作日志
 *
 * @Author yy-studio
 * @Date 2021-12-08 20:48:52
 *
 *
 * @Copyright  远游工作室
 */
@Service
public class OperateLogService {

    @Resource
    private OperateLogDao operateLogDao;

    /**
     * @author 罗伊
     * @description 分页查询
     */
    public ResponseDTO<PageResult<OperateLogVO>> queryByPage(OperateLogQueryForm queryForm) {
        Page page = SmartPageUtil.convert2PageQuery(queryForm);
        List<OperateLogEntity> logEntityList = operateLogDao.queryByPage(page, queryForm);
        PageResult<OperateLogVO> pageResult = SmartPageUtil.convert2PageResult(page, logEntityList, OperateLogVO.class);
        return ResponseDTO.ok(pageResult);
    }


    /**
     * 查询详情
     * @param operateLogId
     * @return
     */
    public ResponseDTO<OperateLogVO> detail(Long operateLogId) {
        OperateLogEntity operateLogEntity = operateLogDao.selectById(operateLogId);
        if(operateLogEntity == null){
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        OperateLogVO operateLogVO = SmartBeanUtil.copy(operateLogEntity, OperateLogVO.class);
        return ResponseDTO.ok(operateLogVO);
    }
}
