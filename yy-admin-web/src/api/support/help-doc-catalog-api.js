/*
 * 帮助文档 目录
 *
 * @Author:    yy-studio
 * @Date:      2022-09-03 21:56:31
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import { postRequest, getRequest } from '/src/lib/axios';

export const helpDocCatalogApi = {
  //帮助文档目录-获取全部 @author zhuoda
  getAll: () => {
    return getRequest('/support/helpDoc/helpDocCatalog/getAll');
  },

  //帮助文档目录-添加 @author zhuoda
  add: (param) => {
    return postRequest('/support/helpDoc/helpDocCatalog/add', param);
  },

  //帮助文档目录-更新 @author zhuoda
  update: (param) => {
    return postRequest('/support/helpDoc/helpDocCatalog/update', param);
  },

  //帮助文档目录-删除 @author zhuoda
  delete: (helpDocCatalogId) => {
    return getRequest(`/support/helpDoc/helpDocCatalog/delete/${helpDocCatalogId}`);
  },
};
