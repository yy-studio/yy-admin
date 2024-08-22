package com.yystudio.admin.config;

import com.yystudio.base.module.support.operatelog.core.OperateLogAspect;
import com.yystudio.base.module.support.operatelog.core.OperateLogConfig;
import org.springframework.context.annotation.Configuration;

/**
 * 操作日志切面 配置
 *
 * @Author yy-studio
 * @Date 2022-05-30 21:22:12
 *
 *
 * @Copyright  远游工作室
 */
@Configuration
public class OperateLogAspectConfig extends OperateLogAspect{

    /**
     * 配置信息
     */
    @Override
    public OperateLogConfig getOperateLogConfig() {
        return OperateLogConfig.builder().corePoolSize(1).queueCapacity(10000).build();
    }


}