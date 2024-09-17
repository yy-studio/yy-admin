/*
 *  用户相关接口
 */
import { getRequest, postRequest } from '@/common/yy-request.js';

export const userApi = {
  /**
   * 分页获取阅读记录 @author yy
   */
  readingRecordList: (param) => {
    return postRequest('/app/readingRecord', param);
  },
  /**
   * 分页获取收藏记录 @author yy
   */
  queryCollectList: (param) => {
    return postRequest('/app/collectRecord', param);
  },

};
