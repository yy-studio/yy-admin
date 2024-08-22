package com.yystudio.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定时任务调度 配置
 *
 * @Author yy-studio
 * @Date 2022-05-30 21:22:12
 *
 *
 * @Copyright  远游工作室
 */
@Slf4j
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    private ScheduledTaskRegistrar taskRegistrar;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
    }

    public String destroy() {
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(taskRegistrar.getCronTaskList());
        taskList.addAll(taskRegistrar.getTriggerTaskList());
        taskList.addAll(taskRegistrar.getFixedDelayTaskList());
        taskList.addAll(taskRegistrar.getFixedRateTaskList());

        taskRegistrar.destroy();

        List<String> taskNameList = taskList.stream().map(Task::toString).collect(Collectors.toList());
        return "已关闭 @Scheduled定时任务：" + taskNameList.size() + "个！";
    }

}
