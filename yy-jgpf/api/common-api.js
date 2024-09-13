/*
 *  公共接口
 * 
 * @Copyright  yy-studio 
 */
import { getRequest, postRequest } from '@/common/yy-request.js';

export const commonApi = {
  /**
   * tabBar标题列表 @author yy
   */
  getTabBars: () => {
    return getRequest('/app/common/tabbars');
  },

};
