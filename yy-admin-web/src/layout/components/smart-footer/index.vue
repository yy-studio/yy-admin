<!--
  * 底部：版权、公司等信息
  *
  * @Author:    yy-studio
  * @Date:      2022-09-06 20:29:12
  * @Wechat:    
  * @Email:     
  * @Copyright  yy-studio
-->
<template>
  <div class="version">
    <a target="_blank" class="smart-copyright" href=""> ©2023-{{ currentYear }} 「yy-studio」
      YyAdmin </a>
    <a target="_blank" class="smart-version" href="https://yyadmin.vip/views/other/change-log.html"> V3.4.0 </a>
  </div>
</template>
<script setup>
import dayjs from 'dayjs';

const currentYear = dayjs().year();
</script>

<style lang="less" scoped>
.version {
  font-size: 14px;
  color: rgba(0, 0, 0, 0.45);

  a {
    color: rgba(0, 0, 0, 0.45);
  }

  a:hover {
    color: @primary-color;
  }
}
</style>
