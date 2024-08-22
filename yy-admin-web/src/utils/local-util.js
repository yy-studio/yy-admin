/*
 * localStorage 相关操作
 *
 * @Author:    yy-studio
 * @Date:      2022-09-06 20:58:49
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */

export const localSave = (key, value) => {
  localStorage.setItem(key, value);
};

export const localRead = (key) => {
  return localStorage.getItem(key) || '';
};

export const localClear = () => {
  localStorage.clear();
};
