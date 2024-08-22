package com.yystudio.base.module.support.heartbeat.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 心跳记录
 *
 * @Author yy-studio
 * @Date 2022-01-09 20:57:24
 * 
 *
 * @Copyright  远游工作室
 */
@Data
public class HeartBeatRecordVO {

    private Integer heartBeatRecordId;

    @Schema(description = "项目路径")
    private String projectPath;

    @Schema(description = "服务器ip")
    private String serverIp;

    @Schema(description = "进程号")
    private Integer processNo;

    @Schema(description = "进程开启时间")
    private Date processStartTime;

    @Schema(description = "心跳当前时间")
    private Date heartBeatTime;


}
