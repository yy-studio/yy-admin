/*
 * 心跳
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 21:55:47
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import { postRequest } from '/src/lib/axios';

export const heartBeatApi = {
  // 分页查询 @author 卓大
  queryList: (param) => {
    return postRequest('/support/heartBeat/query', param);
  },
};
