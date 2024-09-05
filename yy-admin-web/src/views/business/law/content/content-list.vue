<!--
  * 内容表
  *
  * @Author:    yy
  * @Date:      2024-08-24 16:08:00
  * @Copyright  yy-studio
-->
<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            <a-form-item label="关键字查询" class="smart-query-form-item">
                <a-input style="width: 150px" v-model:value="queryForm.keywords" placeholder="标题/副标题" />
            </a-form-item>
            <a-form-item label="收费类型" class="smart-query-form-item">
                <a-select style="width: 150px" v-model:value="queryForm.type" placeholder="请选择类型">
                    <a-select-option :value="1">付费</a-select-option>
                    <a-select-option :value="0">免费</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="创建时间" class="smart-query-form-item">
                <a-range-picker v-model:value="queryForm.createTime" :presets="defaultTimeRanges" style="width: 250px"
                    @change="onChangeCreateTime" />
            </a-form-item>
            <a-form-item label="分类" class="smart-query-form-item">
                <DictSelect keyCode="CONTENT_CATEGORY" placeholder="分类" v-model:value="queryForm.categoryId"
                    width="150px" />
            </a-form-item>
            <a-form-item class="smart-query-form-item">
                <a-button type="primary" @click="queryData">
                    <template #icon>
                        <SearchOutlined />
                    </template>
                    查询
                </a-button>
                <a-button @click="resetQuery" class="smart-margin-left10">
                    <template #icon>
                        <ReloadOutlined />
                    </template>
                    重置
                </a-button>
            </a-form-item>
        </a-row>
    </a-form>
    <!---------- 查询表单form end ----------->

    <a-card size="small" :bordered="false" :hoverable="true">
        <!---------- 表格操作行 begin ----------->
        <a-row class="smart-table-btn-block">
            <div class="smart-table-operate-block">
                <a-button @click="showForm" type="primary" size="small">
                    <template #icon>
                        <PlusOutlined />
                    </template>
                    新建
                </a-button>
                <!-- <a-button @click="confirmBatchDelete" type="danger" size="small"
                    :disabled="selectedRowKeyList.length == 0">
                    <template #icon>
                        <DeleteOutlined />
                    </template>
                    批量删除
                </a-button> -->
            </div>
            <div class="smart-table-setting-block">
                <TableOperator v-model="columns" :tableId="null" :refresh="queryData" />
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table size="small" :dataSource="tableData" :columns="columns" rowKey="id" bordered :loading="tableLoading"
            :pagination="false" :scroll="{ x: 2000 }">
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'title'">
                    <a @click="toDetail(record.id)">{{ text }}</a>
                </template>
                <template v-else-if="column.dataIndex === 'type'">
                    {{ text ? '付费' : '免费' }}
                </template>
                <template v-else-if="column.dataIndex === 'status'">
                    {{ text ? '已发布' : '草稿' }}
                </template>
                <template v-if="column.dataIndex === 'categoryId'">
                    <span>{{ text && text.length > 0 ? text[0].valueName : '' }}</span>
                </template>
                <template v-if="column.dataIndex === 'coverImage'">
                    <FilePreview :fileList="text" type="picture" />
                </template>
                <template v-if="column.dataIndex === 'action'">
                    <div class="smart-table-operate">
                        <a-button @click="showForm(record)" type="link">编辑</a-button>
                        <a-button v-if="record.status" @click="offLine(record)" type="link">下线</a-button>
                        <a-button @click="onDelete(record)" danger type="link">删除</a-button>
                    </div>
                </template>
            </template>
        </a-table>
        <!---------- 表格 end ----------->

        <div class="smart-query-table-page">
            <a-pagination showSizeChanger showQuickJumper show-less-items :pageSizeOptions="PAGE_SIZE_OPTIONS"
                :defaultPageSize="queryForm.pageSize" v-model:current="queryForm.pageNum"
                v-model:pageSize="queryForm.pageSize" :total="total" @change="queryData" @showSizeChange="queryData"
                :show-total="(total) => `共${total}条`" />
        </div>

        <ContentForm ref="formRef" @reloadList="queryData" />

    </a-card>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { SmartLoading } from '/@/components/framework/smart-loading';
import { contentApi } from '/@/api/business/law/content-api';
import { PAGE_SIZE_OPTIONS } from '/@/constants/common-const';
import { smartSentry } from '/@/lib/smart-sentry';
import TableOperator from '/@/components/support/table-operator/index.vue';
import ContentForm from './content-form.vue';
import DictSelect from '/@/components/support/dict-select/index.vue';
import { defaultTimeRanges } from '/@/lib/default-time-ranges';
import FilePreview from '/@/components/support/file-preview/index.vue';
import { useRouter } from 'vue-router';

// ---------------------------- 表格列 ----------------------------

const columns = ref([
    {
        title: '标题',
        dataIndex: 'title',
        fixed: 'left',
        width: 250,
        ellipsis: true,
    },
    {
        title: '副标题',
        dataIndex: 'subtitle',
        width: 150,
        ellipsis: true,
    },
    {
        title: '付费类型',
        dataIndex: 'type',
        width: 120,
    },
    {
        title: '文章分类',
        dataIndex: 'categoryId',
        width: 120,
    },
    // {
    //     title: '发布者ID',
    //     dataIndex: 'userId',
    //     ellipsis: true,
    // },
    {
        title: '封面图片',
        dataIndex: 'coverImage',
        width: 120,
    },
    {
        title: '标签',
        dataIndex: 'tags',
        width: 150,
    },
    {
        title: '状态',
        dataIndex: 'status',
        width: 120,
    },
    {
        title: '阅读量',
        dataIndex: 'views',
        width: 120,
    },
    {
        title: '点赞量',
        dataIndex: 'likes',
        width: 120,
    },
    {
        title: '收藏量',
        dataIndex: 'collections',
        width: 120,
    },
    {
        title: '创建时间',
        dataIndex: 'createTime',
        width: 200,
    },
    {
        title: '更新时间',
        dataIndex: 'updateTime',
        width: 200,
    },
    {
        title: '操作',
        dataIndex: 'action',
        fixed: 'right',
        width: 90,
    },
]);

// ---------------------------- 查询数据表单和方法 ----------------------------

const queryFormState = {
    keywords: undefined, //关键字查询
    type: undefined, //类型
    createTime: [], //创建时间
    createTimeBegin: undefined, //创建时间 开始
    createTimeEnd: undefined, //创建时间 结束
    categoryId: undefined, //分类
    pageNum: 1,
    pageSize: 10,
};
// 查询表单form
const queryForm = reactive({ ...queryFormState });
// 表格加载loading
const tableLoading = ref(false);
// 表格数据
const tableData = ref([]);
// 总数
const total = ref(0);

// 重置查询条件
function resetQuery() {
    let pageSize = queryForm.pageSize;
    Object.assign(queryForm, queryFormState);
    queryForm.pageSize = pageSize;
    queryData();
}

// 查询数据
async function queryData() {
    tableLoading.value = true;
    try {
        let queryResult = await contentApi.queryPage(queryForm);
        tableData.value = queryResult.data.list;
        total.value = queryResult.data.total;
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        tableLoading.value = false;
    }
}

function onChangeCreateTime(dates, dateStrings) {
    queryForm.createTimeBegin = dateStrings[0];
    queryForm.createTimeEnd = dateStrings[1];
}


onMounted(queryData);

// ---------------------------- 添加/修改 ----------------------------
const formRef = ref();

function showForm(data) {
    formRef.value.show(data);
}

// ---------------------------- 单个删除 ----------------------------
//确认删除
function onDelete(data) {
    Modal.confirm({
        title: '提示',
        content: '确定要删除吗?',
        okText: '删除',
        okType: 'danger',
        onOk() {
            requestDelete(data);
        },
        cancelText: '取消',
        onCancel() { },
    });
}

//请求删除
async function requestDelete(data) {
    SmartLoading.show();
    try {
        let deleteForm = {
            goodsIdList: selectedRowKeyList.value,
        };
        await contentApi.delete(data.id);
        message.success('删除成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}

// ---------------------------- 下线 ----------------------------
//确认下线
function offLine(data) {
    Modal.confirm({
        title: '提示',
        content: '确定要下线吗?',
        okText: '下线',
        okType: 'danger',
        onOk() {
            requestOffLine(data);
        },
        cancelText: '取消',
        onCancel() { },
    });
}

//请求下线
async function requestOffLine(data) {
    SmartLoading.show();
    try {
        await contentApi.offLine(data.id);
        message.success('下线成功');
        queryData();
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        SmartLoading.hide();
    }
}

// ------------------ 详情 ------------------

// 进入详情
const router = useRouter();
function toDetail(contentId) {
    router.push({
        path: '/content/detail',
        query: { id: contentId },
    });
}

</script>
