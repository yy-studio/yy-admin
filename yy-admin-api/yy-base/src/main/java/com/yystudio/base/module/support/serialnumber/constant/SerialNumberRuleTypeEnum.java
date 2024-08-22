package com.yystudio.base.module.support.serialnumber.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.yystudio.base.common.constant.StringConst;
import com.yystudio.base.common.enumeration.BaseEnum;

/**
 * 单据序列号 周期
 *
 * @Author yy-studio
 * @Date 2022-03-25 21:46:07
 *
 *
 * @Copyright  远游工作室
 */
@AllArgsConstructor
@Getter
public enum SerialNumberRuleTypeEnum implements BaseEnum {
    /**
     * 没有周期
     */
    NONE(StringConst.EMPTY, "", "没有周期"),
    /**
     * 年周期
     */
    YEAR("[yyyy]", "\\[yyyy\\]", "年"),
    /**
     * 月周期
     */
    MONTH("[mm]", "\\[mm\\]", "年月"),
    /**
     * 日周期
     */
    DAY("[dd]", "\\[dd\\]", "年月日");

    private final String value;

    private final String regex;

    private final String desc;


}
