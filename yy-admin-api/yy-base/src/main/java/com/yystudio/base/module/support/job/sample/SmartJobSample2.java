package com.yystudio.base.module.support.job.sample;

import com.yystudio.base.module.support.job.core.SmartJob;
import lombok.extern.slf4j.Slf4j;
import com.yystudio.base.module.support.config.ConfigDao;
import com.yystudio.base.module.support.config.domain.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定时任务 示例2
 *
 * @author huke
 * @date 2024/6/17 21:30
 */
@Slf4j
@Service
public class SmartJobSample2 implements SmartJob {

    @Autowired
    private ConfigDao configDao;

    /**
     * 定时任务示例
     * 需要事务时 添加 @Transactional 注解
     *
     * @param param 可选参数 任务不需要时不用管
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public String run(String param) {
        // 随便更新点什么东西
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setConfigId(1L);
        configEntity.setRemark(param);
        configDao.updateById(configEntity);

        configEntity = new ConfigEntity();
        configEntity.setConfigId(2L);
        configEntity.setRemark("SmartJob Sample2 update");
        configDao.updateById(configEntity);

        return "执行成功,本次处理数据1条";
    }

}
