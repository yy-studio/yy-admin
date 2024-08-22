/*
 * 登录日志
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 21:56:31
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import { postRequest, getRequest } from '/src/lib/axios';

export const loginLogApi = {
  // 分页查询 @author 卓大
  queryList: (param) => {
    return postRequest('/support/loginLog/page/query', param);
  },
  // 分页查询当前登录人信息 @author 善逸
  queryListLogin: (param) => {
    return postRequest('/support/loginLog/page/query/login', param);
  },
};
