package com.yystudio.base.module.support.operatelog.core;

import com.yystudio.base.module.support.operatelog.domain.OperateLogEntity;
import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

/**
 * 配置
 *
 * @Author yy-studio
 * @Date 2021-12-08 20:48:52
 *
 * 
 * @Copyright  远游工作室
 */
@Data
@Builder
public class OperateLogConfig {

    /**
     * 操作日志存储方法
     */
    private Function<OperateLogEntity, Boolean> saveFunction;

    /**
     * 核心线程数
     */
    private Integer corePoolSize;

    /**
     * 队列大小
     */
    private Integer queueCapacity;


}
