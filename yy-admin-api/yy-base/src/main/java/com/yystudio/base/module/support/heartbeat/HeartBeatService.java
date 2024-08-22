package com.yystudio.base.module.support.heartbeat;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.yystudio.base.common.domain.PageResult;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.util.SmartPageUtil;
import com.yystudio.base.module.support.heartbeat.domain.HeartBeatRecordQueryForm;
import com.yystudio.base.module.support.heartbeat.domain.HeartBeatRecordVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 心跳记录
 *
 * @Author yy-studio
 * @Date 2022-01-09 20:57:24
 *
 *
 * @Copyright  远游工作室
 */
@Slf4j
@Service
public class HeartBeatService {

    @Resource
    private HeartBeatRecordDao heartBeatRecordDao;

    public ResponseDTO<PageResult<HeartBeatRecordVO>> pageQuery(HeartBeatRecordQueryForm pageParam) {
        Page pageQueryInfo = SmartPageUtil.convert2PageQuery(pageParam);
        List<HeartBeatRecordVO> recordVOList = heartBeatRecordDao.pageQuery(pageQueryInfo,pageParam);
        PageResult<HeartBeatRecordVO> pageResult = SmartPageUtil.convert2PageResult(pageQueryInfo, recordVOList);
        return ResponseDTO.ok(pageResult);
    }
}
