package com.yystudio.base.module.support.serialnumber.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.yystudio.base.common.enumeration.BaseEnum;

/**
 * 单据序列号 枚举
 *
 * @Author yy-studio
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  远游工作室
 */
@AllArgsConstructor
@Getter
public enum SerialNumberIdEnum implements BaseEnum {

    ORDER(1, "订单id"),

    CONTRACT(2, "合同id"),

    ;

    private final Integer serialNumberId;

    private final String desc;

    @Override
    public Integer getValue() {
        return serialNumberId;
    }

    @Override
    public String toString() {
        return "SerialNumberIdEnum{" +
                "serialNumberId=" + serialNumberId +
                ", desc='" + desc + '\'' +
                '}';
    }
}
