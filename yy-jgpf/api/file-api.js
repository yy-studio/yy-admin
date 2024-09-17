/**
 * 系统更新日志 api 封装
 *
 * @Author:    卓大
 * @Date:      2022-09-26 14:53:50
 * @Copyright  yy-studio
 */
import { uploadRequest } from '@/common/yy-request';

export const fileApi = {
  upload: (file, folder) => {
    return uploadRequest(file, folder);
  },
};
