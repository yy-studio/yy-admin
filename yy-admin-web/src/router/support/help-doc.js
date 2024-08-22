/*
 * 帮助文档
 *
 * @Author:    yy-studio
 * @Date:      2022-09-06 20:53:19
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import HelpDocLayout from '/@/layout/help-doc-layout.vue';

export const helpDocRouters = [
  {
    path: '/help-doc',
    name: 'HelpDoc',
    component: HelpDocLayout,
    meta: {
      title: '帮助文档',
      hideInMenu: true,
    },
    children: [
      {
        path: '/help-doc/detail',
        component: () => import('/@/views/support/help-doc/user-view/help-doc-user-view.vue'),
      },
    ],
  },
];
