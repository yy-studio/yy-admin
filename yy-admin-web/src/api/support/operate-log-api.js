/*
 * 操作日志
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 21:56:45
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import { postRequest, getRequest } from '/src/lib/axios';

export const operateLogApi = {
  // 分页查询 @author 卓大
  queryList: (param) => {
    return postRequest('/support/operateLog/page/query', param);
  },
  // 详情 @author 卓大
  detail: (id) => {
    return getRequest(`/support/operateLog/detail/${id}`);
  },
  // 分页查询当前登录人信息 @author 善逸
  queryListLogin: (param) => {
    return postRequest('/support/operateLog/page/query/login', param);
  },
};
