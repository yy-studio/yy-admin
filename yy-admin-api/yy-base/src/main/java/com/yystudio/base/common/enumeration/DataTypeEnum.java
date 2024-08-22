package com.yystudio.base.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author yy-studio
 * @Date 2023/10/25 09:47:13
 *
 *
 * @Copyright  远游工作室，Since 2012
 */

@Getter
@AllArgsConstructor
public enum DataTypeEnum implements BaseEnum {

    /**
     *普通数据
     */
    NORMAL(1, "普通数据"),

    /**
     * 加密数据
     */
    ENCRYPT(10, "加密数据"),
    ;
    private final Integer value;

    private final String desc;

}
