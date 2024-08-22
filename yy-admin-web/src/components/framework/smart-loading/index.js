/*
 * loading 组件
 *
 * @Author:    yy-studio
 * @Date:      2022-07-22 20:33:41
 * @Wechat:    
 * @Email:     
 * @Copyright  yy-studio
 */
import { useSpinStore } from "/@/store/modules/system/spin";

export const SmartLoading = {
  show: () => {
    useSpinStore().show();
  },

  hide: () => {
    useSpinStore().hide();
  },
};
