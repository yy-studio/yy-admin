/**
 * 用户表 api 封装
 *
 * @Author:    yy
 * @Date:      2024-08-24 16:07:47
 * @Copyright  yy-studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const userApi = {

  /**
   * 分页查询  @author  yy
   */
  queryPage : (param) => {
    return postRequest('/user/queryPage', param);
  },

  /**
   * 增加  @author  yy
   */
  add: (param) => {
      return postRequest('/user/add', param);
  },

  /**
   * 修改  @author  yy
   */
  update: (param) => {
      return postRequest('/user/update', param);
  },


  /**
   * 删除  @author  yy
   */
  delete: (id) => {
      return getRequest(`/user/delete/${id}`);
  },

  /**
   * 批量删除  @author  yy
   */
  batchDelete: (idList) => {
      return postRequest('/user/batchDelete', idList);
  },

};
