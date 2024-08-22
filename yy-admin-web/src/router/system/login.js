/*
 * 登录页面
 *
 * @Author:    yy-studio
 * @Date:      2022-09-06 20:51:50
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */

export const loginRouters = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('/@/views/system/login3/login.vue'),
    meta: {
      title: '登录',
      hideInMenu: true,
    },
  },
];
