/*
 *  登录
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 21:59:58
 * 
 * 
 * @Copyright  yy-studio （
 */
import { getRequest, postRequest } from '@/lib/smart-request';

export const loginApi = {
  /**
   * 登录 @author 卓大
   */
  login: (param) => {
    return postRequest('/login', param);
  },

  /**
   * 退出登录 @author 卓大
   */
  logout: () => {
    return getRequest('/login/logout');
  },

  /**
   * 获取验证码 @author 卓大
   */
  getCaptcha: () => {
    return getRequest('/login/getCaptcha');
  },

  /**
   * 获取登录信息 @author 卓大
   */
  getLoginInfo: () => {
    return getRequest('/login/getLoginInfo');
  },
};
