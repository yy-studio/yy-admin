package com.yystudio.base.module.support.heartbeat;

import lombok.extern.slf4j.Slf4j;
import com.yystudio.base.common.util.SmartBeanUtil;
import com.yystudio.base.module.support.heartbeat.core.HeartBeatRecord;
import com.yystudio.base.module.support.heartbeat.core.IHeartBeatRecordHandler;
import com.yystudio.base.module.support.heartbeat.domain.HeartBeatRecordEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class HeartBeatRecordHandler implements IHeartBeatRecordHandler {

    @Resource
    private HeartBeatRecordDao heartBeatRecordDao;

    /**
     * 心跳日志处理方法
     * @param heartBeatRecord
     */
    @Override
    public void handler(HeartBeatRecord heartBeatRecord) {
        HeartBeatRecordEntity heartBeatRecordEntity = SmartBeanUtil.copy(heartBeatRecord, HeartBeatRecordEntity.class);
        HeartBeatRecordEntity heartBeatRecordOld = heartBeatRecordDao.query(heartBeatRecordEntity);
        if (heartBeatRecordOld == null) {
            heartBeatRecordDao.insert(heartBeatRecordEntity);
        } else {
            heartBeatRecordDao.updateHeartBeatTimeById(heartBeatRecordOld.getHeartBeatRecordId(), heartBeatRecordEntity.getHeartBeatTime());
        }
    }

}
