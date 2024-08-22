package com.yystudio.base.module.support.file.domain.vo;

import lombok.Data;

/**
 * 文件元数据
 *
 * @Author yy-studio
 * @Date 2019年10月11日 15:34:47
 *
 * 
 * @Copyright  远游工作室
 */
@Data
public class FileMetadataVO {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小/字节
     */
    private Long fileSize;

    /**
     * 文件格式
     */
    private String fileFormat;
}
