/**
 * 内容表 api 封装
 *
 * @Author:    yy
 * @Date:      2024-08-24 16:08:00
 * @Copyright  yy-studio
 */
import { postRequest, getRequest } from '/@/lib/axios';

export const contentApi = {

  /**
   * 分页查询  @author  yy
   */
  queryPage: (param) => {
    return postRequest('/content/queryPage', param);
  },

  /**
   * 增加  @author  yy
   */
  add: (param) => {
    return postRequest('/content/add', param);
  },

  /**
   * 修改  @author  yy
   */
  update: (param) => {
    return postRequest('/content/update', param);
  },


  /**
   * 删除  @author  yy
   */
  delete: (id) => {
    return getRequest(`/content/delete/${id}`);
  },

  /**
   * 下线  @Author  yy
   * @param id
   */
  offLine: (id) => {
    return getRequest(`/content/offLine/${id}`);
  },

  /**
   * 批量删除  @author  yy
   */
  batchDelete: (idList) => {
    return postRequest('/content/batchDelete', idList);
  },

  // 获取详情 @author yy
  getDetail(id) {
    return getRequest(`/content/detail/${id}`);
  },

};
