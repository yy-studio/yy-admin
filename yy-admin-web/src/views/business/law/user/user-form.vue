<!--
  * 用户表
  *
  * @Author:    yy
  * @Date:      2024-08-24 16:07:47
  * @Copyright  yy-studio
-->
<template>
  <a-modal :title="form.id ? '编辑' : '添加'" width="500px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
    :destroyOnClose="true">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">

      <a-form-item label="昵称" name="username">
        <a-input style="width: 100%" v-model:value="form.username" placeholder="昵称" />
      </a-form-item>
      <a-form-item label="手机号" name="mobile">
        <a-input style="width: 100%" v-model:value="form.mobile" placeholder="手机号" />
      </a-form-item>
      <!-- <a-form-item label="邮箱" name="email">
        <a-input style="width: 100%" v-model:value="form.email" placeholder="邮箱" />
      </a-form-item> -->

      <a-form-item label="真实姓名" name="realName">
        <a-input style="width: 100%" v-model:value="form.realName" placeholder="真实姓名" />
      </a-form-item>
    </a-form>
    <a-alert>
      <template v-slot:description>
        <p class="tips" v-if="!form.id">初始密码默认为：111111，第一次登录时修改</p>
      </template>
    </a-alert>

    <template #footer>
      <a-space>
        <a-button @click="onClose">取消</a-button>
        <a-button type="primary" @click="onSubmit">保存</a-button>
      </a-space>
    </template>
  </a-modal>
</template>
<script setup>
import { reactive, ref, nextTick } from 'vue';
import _ from 'lodash';
import { message } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { userApi } from '/@/api/business/law/user-api';
import { smartSentry } from '/@/lib/smart-sentry';
import { regular } from '/@/constants/regular-const';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['reloadList']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(rowData) {
  Object.assign(form, formDefault);
  if (rowData && !_.isEmpty(rowData)) {
    Object.assign(form, rowData);
  }
  visibleFlag.value = true;
  nextTick(() => {
    formRef.value.clearValidate();
  });
}

function onClose() {
  Object.assign(form, formDefault);
  visibleFlag.value = false;
}

// ------------------------ 表单 ------------------------

// 组件ref
const formRef = ref();

const formDefault = {
  id: undefined, //用户ID
  username: undefined, //昵称
  mobile: undefined, //手机号
  email: undefined, //邮箱
  realName: undefined, //真实姓名
  wechatOpenid: undefined, //微信openid
  weiboUid: undefined, //微博uid
};

let form = reactive({ ...formDefault });

const rules = {
  username: [{ required: true, message: '昵称 必填' }],
  password: [{ required: true, message: '密码 必填' }],
  mobile: [
    { required: true, message: '手机号 必填' },
    { pattern: regular.phone, message: '请输入正确的手机号', trigger: 'blur' },
  ],
  // email: [
  //   { pattern: regular.email, message: '请输入正确的邮箱', trigger: 'blur' },
  // ],
};

// 点击确定，验证表单
async function onSubmit() {
  try {
    await formRef.value.validateFields();
    save();
  } catch (err) {
    message.error('参数验证错误，请仔细填写表单数据!');
  }
}

// 新建、编辑API
async function save() {
  SmartLoading.show();
  try {
    if (form.id) {
      await userApi.update(form);
    } else {
      await userApi.add(form);
    }
    message.success('操作成功');
    emits('reloadList');
    onClose();
  } catch (err) {
    smartSentry.captureError(err);
  } finally {
    SmartLoading.hide();
  }
}

defineExpose({
  show,
});
</script>
