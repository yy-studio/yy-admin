/*
 *  权限插件
 *
 * @Author:    yy-studio
 * @Date:      2022-09-06 20:50:46
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import { useUserStore } from '/@/store/modules/system/user';

const privilege = (value) => {
  // 超级管理员
  if (useUserStore().administratorFlag) {
    return true;
  }
  // 获取功能点权限
  let userPointsList = useUserStore().getPointList;
  if (!userPointsList) {
    return false;
  }
  return userPointsList && userPointsList.includes(value);
};

export default {
  install: (app) => {
    app.config.globalProperties.$privilege = privilege;
  },
};
