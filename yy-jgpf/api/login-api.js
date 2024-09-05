/*
 *  登录
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 21:59:58
 * 
 * 
 * @Copyright  yy-studio （
 */
import { getRequest, postRequest } from '@/common/yy-request.js';

export const loginApi = {
  /**
   * 登录 @author yy
   */
  login: (param) => {
    return postRequest('/app/user/login', param);
  },
  
  /**
   * 注册 @author yy
   */
  userRegister: (param) => {
    return postRequest('/app/user/register', param);
  },
  
  
  /**
   * 获取注册验证码 @author yy
   */
  getRegisterVerifyCode: (param) => {
	  return postRequest('/app/verifyCode', param)
  },

  /**
   * 验证验证码 @author yy
   */
  verifyCode: (param) => {
	  return postRequest('/app/register/verify', param)
  },
  
  /**
   * 登录时获取验证码 @author yy
   */
  getLoginVerifyCode: (mobile) => {
  	  return getRequest('/app/verifyCode/login/' + mobile)
  },
  
  /**
   * 验证码登录 @author yy
   */
  loginByVerify: (param) => {
	  return postRequest('/app/user/loginByVerify', param);
  },
  
  /**
   * 退出登录 @author yy
   */
  logout: () => {
    return getRequest('/login/logout');
  },

  /**
   * 获取登录信息 @author yy
   */
  getLoginInfo: () => {
    return getRequest('/app/login/getLoginInfo');
  },
};
