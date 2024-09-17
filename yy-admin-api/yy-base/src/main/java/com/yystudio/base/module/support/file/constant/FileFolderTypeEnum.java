package com.yystudio.base.module.support.file.constant;

import com.yystudio.base.common.enumeration.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件服务 文件夹位置类型枚举类
 *
 * @Author yy-studio
 * @Date 2019年10月11日 15:34:47
 *
 *
 * @Copyright  远游工作室
 */
@AllArgsConstructor
@Getter
public enum FileFolderTypeEnum implements BaseEnum {

    /**
     * 通用
     */
    COMMON(1, FileFolderTypeEnum.FOLDER_PUBLIC + "/common/", "通用"),

    /**
     * APP
     */
    USER_AVATAR(2, FileFolderTypeEnum.FOLDER_PUBLIC + "/app/avatar/", "用户头像"),

    /**
     * 帮助中心
     */
    HELP_DOC(3, FileFolderTypeEnum.FOLDER_PUBLIC + "/help-doc/", "帮助中心"),

    /**
     * 意见反馈
     */
    FEEDBACK(4, FileFolderTypeEnum.FOLDER_PUBLIC + "/feedback/", "意见反馈"),

    ;

    /**
     * 公用读取文件夹 public
     */
    public static final String FOLDER_PUBLIC = "public";

    /**
     * 私有读取文件夹 private， 私有文件夹会设置 只读权限，并且 文件url 拥有过期时间
     */
    public static final String FOLDER_PRIVATE = "private";

    private final Integer value;

    private final String folder;

    private final String desc;
}

