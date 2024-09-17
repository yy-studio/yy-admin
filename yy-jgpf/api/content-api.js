/*
 *  文章
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 21:59:58
 * 
 * 
 * @Copyright  yy-studio （
 */
import { getRequest, postRequest } from '@/common/yy-request.js';

export const contentApi = {
  /**
   * 分页查询文章 @author yy
   */
  queryPage: (param) => {
    return postRequest('/app/content/queryPage', param);
  },

  /**
   * 获取文章详情 @author yy
   */
  getDetail: (id) => {
    return getRequest('/app/content/detail/' + id);
  },
  /**
   * 收藏文章 @author yy
   */
  collect: (param) => {
    return postRequest('/app/content/collect', param);
  },
};
