<template>
  <view class="container">
    <!-- 顶部的灰色实线 -->
    <view class="top-line"></view>
    <image class="top-image" mode="aspectFit" :src="article.image"></image>
    <!-- 标题行 -->
    <view class="title-line">
      <!-- 根据类型设置颜色 -->
      <!-- <view class="color-bar"></view> -->
      <view class="color-bar" :style="getBackgroundColor()"></view>
      <view>
        <text class="title">{{ article.title }}</text>
        <!-- 日期行 -->
        <view class="date-line">
          <text class="date">{{ article.date }}</text>
          <view v-if="article.free" class="separator"></view>
          <view v-if="article.free" class="icon"></view> <!-- 替换为免费图标 -->
        </view>
      </view>
    </view>

    <view class="article-detail">

      <!-- 横虚线 -->
      <view class="dashed-line"></view>

      <!-- 副标题 -->
      <view class="subtitle">{{ article.subtitle }}</view>

      <!-- 全文字数和阅读时间 -->
      <view class="meta-info">
        <text>全文共 {{ article.wordCount }} 字</text>，<text>阅读完大约需要 {{ article.readTime }} 分钟</text>
      </view>

      <!-- 文章内容 -->
      <view class="content" v-html="article.content"></view>

      <!-- 如果只展示部分内容，需要订阅 -->
      <view v-if="article.displayType === 0" class="subscription-area">
        <view class="bottom-line"></view>
        <view class="uni-flex uni-row subscription-btn-view"
          style="-webkit-justify-content: space-between;justify-content: space-between;" @click="toSubscription">
          <text class="subscription-btn">立即订阅</text>
          <uni-icons color="#f5f5f5" type="arrow-right" size="20"></uni-icons>
        </view>
      </view>

      <view class="tags">
        <view v-for="(tag, index) in article.tags" :key="index" class="tag-item">
          <text>{{ tag }}</text>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import { contentApi } from '@/api/content-api';
export default {
  data() {
    return {
      article: {
        title: '',
        categoryId: '',
        image: '',
        free: false,
        date: '',
        subtitle: ' ',
        content: ' ',
        wordCount: 0,
        readTime: 0,
        displayType: 1,
        tags: [],
        type: 'blog' // 文章类型，用于设置颜色
      },
    };
  },
  onLoad(options) {
    // console.log(options.id); 
    let id = options.id ? options.id : 13;
    this.getDetail(id);
  },
  // computed: {
  //   wordCount() {
  //     // 计算字数
  //     const text = this.article.content.replace(/<[^>]*>/g, ''); // 移除 HTML 标签
  //     return text.length;
  //   },
  //   readingTime() {
  //     // 计算阅读时间，假设平均阅读速度为 200 字/分钟
  //     return Math.ceil(this.wordCount / 200);
  //   }
  // },
  methods: {

    async getDetail(id) {

      try {
        const res = await contentApi.getDetail(id);
        let responseData = res.data;
        // 将接口返回的数据赋值给页面变量
        this.article = {
          title: responseData.title,
          categoryId: responseData.categoryId[0].valueCode,
          image: responseData.coverImage[0].fileUrl,
          subtitle: responseData.subtitle,
          // type: responseData.type,
          content: responseData.content,
          date: this.formatDate(responseData.createTime),
          wordCount: responseData.wordCount,
          readTime: responseData.readTime,
          displayType: responseData.displayType,
          tags: responseData.tags.split(','),
          free: responseData.type === 1 ? false : true,
        };
        console.log(this.article)
      } catch (e) {
        console.error(e)
      }
    },
    formatDate(dateString) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(dateString).toLocaleDateString('zh-CN', options);
    },
    toSubscription() {
      uni.navigateTo({
        url: "/pages/pay/subscription/subscription"
      })
    },

    getBackgroundColor() {
      if (!this.article.categoryId) return '';
      const lastDigit = parseInt(this.article.categoryId.slice(-1));
      const remainder = lastDigit % 5;
      switch (remainder) {
        case 0:
          return 'background-color: #1843AB;';
        case 1:
          return 'background-color: #EA030C;';
        case 2:
          return 'background-color: #F99323;';
        case 3:
          return 'background-color: #8CC63E;';
        case 4:
          return 'background-color: #7B1A71;';
        default:
          return '';
      }
    },
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  /* Set the container height to viewport height */
  overflow-y: auto;
  /* Allow vertical scrolling */
}

.article-detail {
  padding: 0 30rpx;
}

.top-line {
  width: 100%;
  height: 2rpx;
  background-color: #DDDDDD;
}

.top-image {
  width: 100%;
  height: 240rpx;
}

.title-line {
  display: flex;
  align-items: start;
  margin: 20rpx 0 10rpx 0;

}

.color-bar {
  width: 18rpx;
  height: 80rpx;
  margin-top: 8rpx;
  margin-right: 10rpx;
  background-color: blue;
}

.title {
  font-size: 36rpx;
  font-weight: bold;
}

.date-line {
  display: flex;
  align-items: center;
  color: #999;
}

.separator {
  width: 1rpx;
  height: 30rpx;
  background-color: #BBBBBB;
  margin: 0 10rpx;
}

.icon {
  width: 80rpx;
  height: 46rpx;
  background: url('/static/beat_free_tag2.svg') no-repeat center center;
  background-size: contain;
}

.dashed-line {
  border-bottom: 1rpx solid #ddd;
  width: 100%;
  margin: 20rpx auto;
}

.subtitle {
  font-size: 28rpx;
  color: #666;
  margin: 10rpx 0;
}

.meta-info {
  font-size: 24rpx;
  color: #999;
  margin-bottom: 20rpx;
}

.content {
  line-height: 1.8;
  color: #333;
}

.subscription-area {
  padding: 20rpx 20rpx;
  background-color: #f5f5f5;
}

.bottom-line {
  width: 100%;
  height: 2rpx;
  border-bottom: 2rpx dotted #0e0b0b;
  margin-bottom: 20rpx;
}

.subscription-btn-view {
  padding: 20rpx 40rpx;
  background-color: #333;
  color: #f5f5f5;
}

.tags {
  margin-top: 20rpx;
  margin-left: 20rpx;
  font-size: 24rpx;
  color: #777;
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag-item {
  background-color: #f0f0f0;
  margin-right: 10rpx;
  border-radius: 12rpx;
  padding: 4px 8px;
  display: inline-block;
}
</style>