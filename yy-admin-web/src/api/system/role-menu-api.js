/*
 * 角色菜单
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 22:00:49
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import { getRequest, postRequest } from '/src/lib/axios';
export const roleMenuApi = {
  /**
   * @description: 获取角色关联菜单权限
   */
  getRoleSelectedMenu: (roleId) => {
    return getRequest(`role/menu/getRoleSelectedMenu/${roleId}`);
  },
  /**
   * @description: 更新角色权限
   */
  updateRoleMenu: (data) => {
    return postRequest('role/menu/updateRoleMenu', data);
  },
};
