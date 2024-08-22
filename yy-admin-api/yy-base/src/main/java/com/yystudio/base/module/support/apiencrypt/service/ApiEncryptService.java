package com.yystudio.base.module.support.apiencrypt.service;

/**
 * 接口加密、解密 Service
 *
 * @Author yy-studio
 * @Date 2023/10/21 11:41:46
 *
 *
 * @Copyright  远游工作室
 */

public interface ApiEncryptService {

    /**
     * 解密
     * @param data
     * @return
     */
    String decrypt(String data);

    /**
     * 加密
     *
     * @param data
     * @return
     */
    String encrypt(String data);

}
