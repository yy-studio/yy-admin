/*
 *  ajax请求
 *
 * @Author:    yy-studio
 * @Date:      2022-09-06 20:46:03
 * 
 * 
 * @Copyright  yy-studio （
 */
import { USER_TOKEN } from '@/common/local-storage-key-const';
import { useUserStore } from '@/store/user';

// http://192.168.1.107:1024/
// const baseUrl = 'http://8.130.112.158:1024';
const baseUrl = 'http://127.0.0.1:1024';

function getUserToken() {
  let token = uni.getStorageSync(USER_TOKEN);
  if (token) {
    return token;
  }
  return '';
}

/**
 * 处理返回的消息
 */
function handleResponse(response, resolve, reject) {

  const res = response.data;
  if (res.code && res.code !== 1) {
    // `token` 过期或者账号已在别处登录
    if (res.code === 30007 || res.code === 30008 || res.code === 30012) {
      uni.showToast({
        title: res.msg,
        icon: 'none',
        position: 'bottom',
      });
      useUserStore().clearUserLoginInfo();
      uni.navigateTo({ url: '/pages/login/login' });
    }

    uni.showToast({
      title: res.msg,
      icon: 'none',
      position: 'bottom',
    });
    reject(response);
  } else {
    resolve(res);
  }
}

/**
 * 通用请求封装
 */
export const request = function (url, method, data) {
  return new Promise((resolve, reject) => {
    uni.request({
      url: baseUrl + url, //拼接请求路径
      data: data,
      method: method,
      header: {
        'x-access-token': getUserToken(),
      },
      success: (response) => {
        handleResponse(response, resolve, reject);
      },
      fail: (error) => {
        reject(error);
      },
    });
  });
};

/**
 * get请求
 */
export const getRequest = (url) => {
  return request(url, 'GET');
};

/**
 * post请求
 */
export const postRequest = (url, data) => {
  return request(url, 'POST', data);
};


// ================================= 文件 =================================

export const uploadRequest = function (filePath, folder) {
  return new Promise((resolve, reject) => {
    uni.uploadFile({
      url: baseUrl + '/app/file/upload',
      filePath,
      header: {
        'x-access-token': getUserToken(),
      },
      name: 'file',
      formData: {
        folder,
      },
      success: (response) => {
        response.data = JSON.parse(response.data.replace('\uFEFF', ''));
        handleResponse(response, resolve, reject);
      },
      fail: (error) => {
        reject(error);
      },
    });
  });
};
