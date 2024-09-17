/*
 * 登录用户
 *
 * @Author:    yy-studio
 * @Date:      2022-09-06 20:55:09
 * 
 * 
 * @Copyright  yy-studio （
 */
// import _ from 'lodash';
import { defineStore } from 'pinia';
import { USER_TOKEN } from '@/common/local-storage-key-const.js';
import { loginApi } from '@/api/login-api';

const defaultUserInfo = {
  //用户类型（2:个人，3:企业）
  type: undefined,
  //用户ID
  id: undefined,
  //用户名
  username: '',
  //手机号
  mobile: '',
  //邮箱
  email: '',
  //头像
  avatar: '',
  //真实姓名
  realName: '',
  //微信openid
  wechatOpenid: '',
  //微博uid
  weiboUid: '',
  //总积分
  totalPoints: undefined,
  //状态:1正常,0未激活,-1注销
  status: undefined,
  //1付费用户
  paid: undefined,
  //上次登录ip
  lastLoginIp: '',
  //上次登录ip地区
  lastLoginIpRegion: '',
  //上次登录 设备
  lastLoginUserAgent: '',
  //上次登录时间
  lastLoginTime: '',
};

export const useUserStore = defineStore({
  id: 'userStore',
  state: () => ({
    ...defaultUserInfo,
  }),
  getters: {
    getToken(state) {
      return uni.getStorageSync(USER_TOKEN);
    },
  },

  actions: {
    logout() {
      this.token = null;
	  
      this.setUserLoginInfo(defaultUserInfo);
      uni.removeStorageSync(USER_TOKEN);
    },
    clearUserLoginInfo() {
      this.setUserLoginInfo(defaultUserInfo);
      uni.removeStorageSync(USER_TOKEN);
    },
    async getLoginInfo() {
		try{
			
			let res = await loginApi.getLoginInfo();
			console.log(res.data);
			this.setUserLoginInfo(res.data);
		} catch (e) {
			console.error(e)
		}
    },
    //设置登录信息
    setUserLoginInfo(data) {
      // 用户基本信息
      this.token = data.token;
		this.id = data.id;
		this.username = data.userName;
		this.mobile = data.mobile;
		this.email = data.email;
		this.avatar = data.avatar;
		this.realName = data.realName;
		this.wechatOpenid = data.wechatOpenid;
		this.weiboUid = data.weiboUid;
		this.totalPoints = data.totalPoints;
		this.status = data.status;
		this.paid = data.paid;
	  
      this.lastLoginIp = data.lastLoginIp;
      this.lastLoginIpRegion = data.lastLoginIpRegion;
      this.lastLoginUserAgent = data.lastLoginUserAgent;
      this.lastLoginTime = data.lastLoginTime;

      uni.setStorageSync(USER_TOKEN, data.token);
    },
  },
});
