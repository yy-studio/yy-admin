/*
 * 首页api
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 21:59:39
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import { getRequest } from '/src/lib/axios';

export const homeApi = {
  /**
   * @description: 首页-金额统计（业绩、收款、订单数等） @author 卓大
   */
  homeAmountStatistics: () => {
    return getRequest('/home/amount/statistics');
  },
  /**
   * @description: 首页-待办信息 @author 卓大
   */
  homeWaitHandle: () => {
    return getRequest('home/wait/handle');
  },
};
