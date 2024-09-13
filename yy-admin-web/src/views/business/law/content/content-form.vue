<!--
  * 内容表
  *
  * @Author:    yy
  * @Date:      2024-08-24 16:08:00
  * @Copyright  yy-studio
-->
<template>
  <a-modal :title="form.id ? '编辑' : '添加'" width="1000px" :open="visibleFlag" @cancel="onClose" :maskClosable="false"
    :destroyOnClose="true">
    <a-form ref="formRef" :model="form" :rules="rules" :label-col="{ span: 5 }">

      <a-form-item label="标题" name="title">
        <a-input style="width: 100%" v-model:value="form.title" placeholder="标题" />
      </a-form-item>
      <a-form-item label="副标题" name="subtitle">
        <a-input style="width: 100%" v-model:value="form.subtitle" placeholder="副标题" />
      </a-form-item>
      <a-form-item label="收费类型" name="type">
        <a-select style="width: 150px" v-model:value="form.type" placeholder="请选择类型">
          <a-select-option :value="1">付费</a-select-option>
          <a-select-option :value="0">免费</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="文章分类" name="categoryId">
        <!-- <a-input-number style="width: 100%" v-model:value="form.categoryId" placeholder="分类ID" /> -->
        <DictSelect keyCode="CONTENT_CATEGORY" placeholder="分类" v-model:value="form.categoryId" width="150px" />
      </a-form-item>
      <a-form-item label="封面图片" name="coverImage">
        <!-- <a-input style="width: 100%" v-model:value="form.coverImage" placeholder="封面图片" /> -->
        <Upload accept=".jpg,.jpeg,.png,.gif,.JPG,.JPEG,.PNG,.GIF" :maxUploadSize="1" buttonText="点击上传封面图片"
          :default-file-list="form.coverImage" @change="imageChange" />
      </a-form-item>
      <a-form-item label="内容正文" name="content">
        <SmartWangeditor ref="contentRef" :modelValue="form.content" :height="300" />
      </a-form-item>
      <a-form-item label="标签" name="tags">
        <a-input style="width: 100%" v-model:value="form.tags" placeholder="多个标签使用半角逗号分隔，除半角逗号以外，不允许输入其他标点符号"
          @input="filterInput" />
      </a-form-item>

    </a-form>

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
import { contentApi } from '/@/api/business/law/content-api';
import { smartSentry } from '/@/lib/smart-sentry';
import SmartWangeditor from '/@/components/framework/wangeditor/index.vue';
import Upload from '/@/components/support/file-upload/index.vue';
import DictSelect from '/@/components/support/dict-select/index.vue';

// ------------------------ 事件 ------------------------

const emits = defineEmits(['reloadList']);

// ------------------------ 显示与隐藏 ------------------------
// 是否显示
const visibleFlag = ref(false);

function show(rowData) {
  Object.assign(form, formDefault);
  if (rowData && !_.isEmpty(rowData)) {
    Object.assign(form, rowData);

    if (rowData.categoryId && rowData.categoryId.length > 0) {
      form.categoryId = rowData.categoryId[0].valueCode;
    }
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
const contentRef = ref();

const formDefault = {
  id: undefined,
  title: undefined, //标题
  subtitle: undefined, //副标题
  type: undefined, //类型
  categoryId: undefined, //分类ID
  coverImage: undefined, //封面图片
  content: undefined, //内容正文
  tags: undefined, //标签
};

let form = reactive({ ...formDefault });

const rules = {
  title: [{ required: true, message: '文章标题 必填' }],
  type: [{ required: true, message: '付费类型 必选' }],
  categoryId: [{ required: true, message: '文章分类 必选' }],
  content: [{ required: true, message: '内容正文 必填' }],
  coverImage: [{ required: true, message: '封面图片 必填' }],
  tags: [{ required: true, message: '标签 必填' }],
};

// 点击确定，验证表单
async function onSubmit() {
  try {
    form.content = contentRef.value.getHtml();
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
      await contentApi.update(form);
    } else {
      await contentApi.add(form);
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

function imageChange(fileList) {
  form.coverImage = fileList;
}

function filterInput(event) {
  const regex = /[^\u4e00-\u9fa5a-zA-Z0-9,]/g; // Regex to allow only Chinese characters, English letters, digits, and half-width commas
  form.tags = event.target.value.replace(regex, '');
}

defineExpose({
  show,
});
</script>
