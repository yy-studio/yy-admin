/*
 * 意见反馈
 *
 * @Author:    yy-studio：开云
 * @Date:      2022-09-03 21:56:31
 * 
 * 
 * @Copyright  yy-studio （
 */
import { postRequest } from '/src/lib/smart-request';

export const feedbackApi = {
  // 意见反馈-新增
  addFeedback: (params) => {
    return postRequest('/support/feedback/add', params);
  },
};
