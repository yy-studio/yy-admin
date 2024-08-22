package com.yystudio.base.module.support.apiencrypt.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import com.yystudio.base.common.domain.ResponseDTO;
import com.yystudio.base.common.enumeration.DataTypeEnum;
import com.yystudio.base.module.support.apiencrypt.annotation.ApiEncrypt;
import com.yystudio.base.module.support.apiencrypt.service.ApiEncryptService;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * 加密
 *
 * @Author yy-studio
 * @Date 2023/10/24 09:52:58
 *
 *
 * @Copyright  远游工作室，Since 2012
 */


@Slf4j
@ControllerAdvice
public class EncryptResponseAdvice implements ResponseBodyAdvice<ResponseDTO> {

    @Resource
    private ApiEncryptService apiEncryptService;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(ApiEncrypt.class) || returnType.getContainingClass().isAnnotationPresent(ApiEncrypt.class);
    }

    @Override
    public ResponseDTO beforeBodyWrite(ResponseDTO body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body.getData() == null) {
            return body;
        }

        String encrypt = apiEncryptService.encrypt(JSON.toJSONString(body.getData()));
        body.setData(encrypt);
        body.setDataType(DataTypeEnum.ENCRYPT.getValue());
        return body;
    }
}


