package com.yystudio.base.common.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 校验数据是否为空的包装类
 *
 * @Author yy-studio
 * @Date 2020/10/16 21:06:11
 * 
 * 
 * @Copyright  远游工作室
 */
@Data
public class ValidateData<T> {

    @NotNull(message = "数据不能为空哦")
    private T data;
}