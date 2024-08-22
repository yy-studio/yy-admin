/**
 * 登录锁定 api 封装
 *
 * @Author:    yy-studio
 * @Date:      2023-10-17 18:02:37
 * @Copyright  yy-studio
 */
import { postRequest, getRequest } from '/src/lib/axios';

export const loginFailApi = {

  /**
   * 分页查询  @author  yy-studio
   */
  queryPage: (param) => {
    return postRequest('/support/protect/loginFail/queryPage', param);
  },

  /**
   * 批量删除  @author  yy-studio
   */
  batchDelete: (idList) => {
    return postRequest('/support/protect/loginFail/batchDelete', idList);
  },

};
