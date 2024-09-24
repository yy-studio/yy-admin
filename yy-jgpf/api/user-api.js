/*
 *  用户相关接口
 */
import { getRequest, postRequest } from '@/common/yy-request.js';

export const userApi = {
  /**
   * 分页获取阅读记录 @author yy
   */
  readingRecordList: (param) => {
    return postRequest('/app/readingRecord', param);
  },
  /**
   * 分页获取收藏记录 @author yy
   */
  queryCollectList: (param) => {
    return postRequest('/app/collectRecord', param);
  },
  /**
   * 用户详情 @author yy
   */
  userDetail: () => {
    return getRequest('/app/user/detail');
  },
  /**
   * 更新昵称
   */
  updateUsername: (username) => {
	  return postRequest('/app/update/nickname/' + username);
  },
  
  /**
   * 绑定/更新手机号时获取验证码 @author yy
   */
  getBindMobileVerifyCode: (mobile) => {
  	  return postRequest('/app/update/mobile/' + mobile)
  },
  
  /**
   * 绑定/更新手机号 @author yy
   */
  bindMobile: (param) => {
  	  return postRequest('/app/update/mobile', param)
  },
  
  /**
   * 修改密码 @author yy
   */
  modifyPassword: (param) => {
  	  return postRequest('/app/update/password', param)
  },

};
