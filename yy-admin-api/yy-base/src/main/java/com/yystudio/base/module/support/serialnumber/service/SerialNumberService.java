package com.yystudio.base.module.support.serialnumber.service;

import com.yystudio.base.module.support.serialnumber.constant.SerialNumberIdEnum;

import java.util.List;

/**
 * 单据序列号
 *
 * @Author yy-studio
 * @Date 2022-03-25 21:46:07
 * 
 *
 * @Copyright  远游工作室
 */
public interface SerialNumberService {

    /**
     * 生成
     *
     * @param serialNumberIdEnum
     * @return
     */
    String generate(SerialNumberIdEnum serialNumberIdEnum);


    /**
     * 生成n个
     *
     * @param serialNumberIdEnum
     * @param count
     * @return
     */
    List<String> generate(SerialNumberIdEnum serialNumberIdEnum, int count);

}
