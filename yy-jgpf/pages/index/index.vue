<template>
	<view>
		<view class="tabs">
			<view class="uni-flex uni-row"
				style="-webkit-justify-content: space-between;justify-content: space-between;">
				<scroll-view id="tab-bar" class="scroll-h" :scroll-x="true" :show-scrollbar="false"
					:scroll-into-view="scrollInto">
					<view v-for="(tab, index) in tabBars" :key="tab.id" class="uni-tab-item"
						:class="tabIndex == index ? 'uni-tab-item-active' : ''" :id="tab.id" :data-current="index"
						@click="ontabtap(tab, index)" :style="getBackgroundColor(tab.id, tabIndex == index)">
						<text class="uni-tab-item-title" :class="tabIndex == index ? 'uni-tab-item-title-active' : ''">
							{{ tab.name }}</text>
					</view>

				</scroll-view>
				<view class="search-icon">
					<icon type="search" size="20" @click="toSearch" />
				</view>
			</view>
		</view>
		<view v-if="tabIndex === 0" class="container">
			<uni-swiper-dot class="uni-swiper-dot-box" @clickItem=clickItem :info="topSwiper" :current="current"
				mode="nav" :dots-styles="dotsStyles" field="title">
				<swiper class="swiper-box" @change="change" :current="swiperDotIndex" autoplay="true" interval=5000
					circular="true">
					<swiper-item v-for="(item, index) in topSwiper" :key="index">
						<view class="swiper-item" :class="'swiper-item' + index">
							<cache-img class="top-image" mode="aspectFill" :imgSrc="item.coverImage[0].fileUrl"
								@click="clickTopSwiper(item)"></cache-img>
						</view>
					</swiper-item>
				</swiper>
			</uni-swiper-dot>
			<!-- New Progress Bar Line -->
			<view class="progress-bar">
				<view class="progress-bar-fill" :style="{ width: progressWidth }"></view>
			</view>

			<view class="line-h"></view>

			<!-- New Scroll View -->
			<scroll-view class="item-scroll-view" scroll-x="true" :scroll-y="false" :show-scrollbar="false">
				<view v-for="(item, index) in middleScroll" :key="index" class="scroll-item"
					@click="clickTopSwiper(item)">
					<cache-img class="scroll-item-image" mode="aspectFill"
						:imgSrc="item.coverImage[0].fileUrl"></cache-img>
					<text class="scroll-item-title">{{ item.title }}</text>
				</view>
			</scroll-view>

			<view class="line-m"></view>

			<view class="content-list">
				<uni-list :border="false">
					<uni-list-item ellipsis="1" v-for="(item, index) in itemList" :key="index" :border="false"
						:title="item.title" :note="item.tags" :thumb="item.coverImage[0].fileUrl" thumb-size="lg"
						rightText="" :to="`/pages/detail/detail?id=${item.id}`" @click="onClick" />
				</uni-list>
			</view>
		</view>
		<view v-else class="container">
			<view class="swiper-item category-image">
				<cache-img class="top-image" mode="aspectFill" :imgSrc="categoryTop.coverImage[0].fileUrl"
					@click="clickTopSwiper(categoryTop)"></cache-img>
				<view class="top-title">
					<text class="overlay-text">{{ ' ' + categoryTop.title }}</text>
				</view>
			</view>
			<view class="line-h"></view>
			<view class="content-list">
				<uni-list :border="false">
					<uni-list-item ellipsis="1" v-for="(item, index) in categoryList" :key="index" :border="false"
						:title="item.title" :note="item.tags" :thumb="item.coverImage[0].fileUrl" thumb-size="lg"
						rightText="" :to="`/pages/detail/detail?id=${item.id}`" @click="onClick" />
				</uni-list>
			</view>
		</view>
	</view>
</template>

<script>
import { useUserStore } from '@/store/user';
import { useTabBarsStore } from '@/store/tabBars';
import { contentApi } from '@/api/content-api';

export default {
	data() {
		return {
			tabIndex: 0,
			tabBars: [],
			scrollInto: "",
			topSwiper: [
				{
					title: "",
					coverImage: [
						{
							fileUrl: "",
						}
					]
				},
			],
			middleScroll: [
				{
					title: "",
					coverImage: [
						{
							fileUrl: "",
						}
					]
				},
			],
			itemList: [
				{
					title: "",
					coverImage: [
						{
							fileUrl: "",
						}
					]
				},
			],
			categoryTop: {
				title: "",
				coverImage: [
					{
						fileUrl: "",
					}
				]
			},
			categoryList: [
				{
					title: "",
					coverImage: [
						{
							fileUrl: "",
						}
					]
				},
			],
			current: 0,
			dotsStyles: {},
			swiperDotIndex: 0,
			progressWidth: '0%' // Initialize progress width
		}
	},
	onLoad() {
		// 在某个组件或页面中调用uni.getSystemInfo()方法
		uni.getSystemInfo({
			success: (res) => {
				console.log(res)
				// 获取设备屏幕宽度
				const screenWidth = res.windowWidth;

				// 判断设备是手机还是平板
				const isTablet = screenWidth >= 768; // 一般认为宽度大于等于768px的设备为平板

				// 根据判断结果进行相应的操作
				if (isTablet) {
					console.log("设备是平板");
					// 平板布局和逻辑处理...
				} else {
					console.log("设备是手机");
					// 手机布局和逻辑处理...
				}
			},
		});

		const tabBarsStore = useTabBarsStore();
		const defaultTab = { id: 'tp1000', name: '首页' };
		this.tabBars = [defaultTab, ...tabBarsStore.tabBars];
		// console.log(this.tabBars);
		this.getTopSwiperInfo();
		this.getMiddleScrollList();
		this.getItemList();
	},
	methods: {

		ontabtap(tab, index) {
			// let index = e.target.dataset.current || e.currentTarget.dataset.current;
			console.log("on tab tap...." + index);
			console.log(tab.id);
			this.switchTab(index);
			this.getByCategory(tab.id);
		},
		ontabchange(e) {
			let index = e.target.current || e.detail.current;
			// this.switchTab(index);
		},
		switchTab(index) {

			// console.log("on tab change...." + index)

			if (this.tabIndex === index) {
				return;
			}

			this.tabIndex = index;
			// this.scrollInto = this.tabBars[index].id;

		},
		getBackgroundColor(tabId, isSelected) {
			if (!isSelected) return '';
			const lastDigit = parseInt(tabId.slice(-1));
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
		clearTabData(e) {
			this.newsList[e].data.length = 0;
			this.newsList[e].loadingText = "加载更多...";
		},
		clickTopSwiper(item) {
			uni.navigateTo({
				url: `/pages/detail/detail?id=${item.id}`
			})
		},
		change(e) {
			this.current = e.detail.current
			this.swiperDotIndex = e.detail.current
			this.updateProgress();
		},
		clickItem(e) {
			this.swiperDotIndex = e
			this.updateProgress();
		},
		toSearch() {
			uni.navigateTo({
				url: '/pages/search/search'
			})
		},
		async getTopSwiperInfo() {

			try {
				let topSwiperParam = {
					categoryId: 'tp1101',
					pageNum: 1,
					pageSize: 100,
				}
				const res = await contentApi.queryPage(topSwiperParam);
				// console.log(res)
				this.topSwiper = res.data.list;

			} catch (e) {

			}
		},
		async getMiddleScrollList() {

			try {
				let middleScrollParam = {
					categoryId: 'kp1102',
					pageNum: 1,
					pageSize: 100,
				}
				const res = await contentApi.queryPage(middleScrollParam);
				// console.log(res)
				this.middleScroll = res.data.list;

			} catch (e) {

			}
		},
		async getItemList() {

			try {
				let itemListParam = {

					pageNum: 1,
					pageSize: 10,
				}
				const res = await contentApi.queryPage(itemListParam);
				// console.log(res)
				this.itemList = res.data.list;

			} catch (e) {

			}
		},
		async getByCategory(categoryId) {

			try {
				let queryParam = {
					categoryId: categoryId,
					pageNum: 1,
					pageSize: 10,
				}
				const res = await contentApi.queryPage(queryParam);
				// console.log(res)
				if (res.data.list && res.data.list.length > 0) {
					this.categoryTop = res.data.list[0];
					this.categoryList = res.data.list.slice(1);
				}


			} catch (e) {

			}
		},

		updateProgress() {
			// Calculate the progress width based on the current swiper index
			this.progressWidth = ((this.swiperDotIndex + 1) / this.topSwiper.length) * 100 + '%';
		},
		onClick(e) {
			console.log('执行click事件', e.data)
			// uni.showToast({
			// 	title: '点击反馈'
			// });
		},

	}
}
</script>

<style>
/* #ifndef APP-PLUS */
page {
	width: 100%;
	display: flex;
	flex-direction: column;
	height: 100vh;
	/* Set the container height to viewport height */
	overflow-y: auto;
	/* Allow vertical scrolling */
}

/* #endif */
.container {
	margin-top: calc(50px + env(safe-area-inset-top));
}

.tabs {
	position: fixed;
	/* Fixed at the top */
	top: 0;
	/* Align to the top */
	width: 100%;
	z-index: 1000;
	/* Ensure it stays above other content */
	background-color: white;
	/* Set background color to match */
	box-shadow: 0 2rpx 4rpx rgba(0, 0, 0, 0.1);
	/* Optional: Add shadow for better visibility */
}

.scroll-h {
	width: 80vw;
	height: 80rpx;
	flex-direction: row;
	white-space: nowrap;
	margin-left: 40rpx;
	margin-top: calc(10px + env(safe-area-inset-top));
}

.search-icon {
	width: 20vw;
	padding-left: 20rpx;
	padding-top: 14rpx;
	margin-top: calc(10px + env(safe-area-inset-top));
}

.line-h {
	margin-top: 20rpx;
	height: 2rpx;
	background-color: #cccccc;
}

.line-m {
	margin: 0 20rpx 20rpx 20rpx;
	height: 1rpx;
	border-style: dotted;
	border-color: #cccccc;
}

.uni-tab-item {
	display: inline-block;
	flex-wrap: nowrap;
	padding-left: 20rpx;
	padding-right: 20rpx;
	height: 60rpx;
}

.uni-tab-item-active {
	border-radius: 12rpx;
	background-color: #1843AB;
}

.uni-tab-item-title {
	color: #000;
	font-size: 36rpx;
	height: 60rpx;
	line-height: 60rpx;
	flex-wrap: nowrap;
	white-space: nowrap;
}

.uni-tab-item-title-active {
	color: #fff;
}

.swiper-box {
	height: 200px;
}

.swiper-item {
	/* #ifndef APP-NVUE */
	display: flex;
	/* #endif */
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 200px;
	color: #fff;
}

.swiper-item0 {
	background-color: #cee1fd;
}

.swiper-item1 {
	background-color: #b2cef7;
}

.swiper-item2 {
	background-color: #cee1fd;
}

.top-image {
	width: 100%;
}

.progress-bar {
	width: 100%;
	height: 4rpx;
	background-color: #f0f0f0;
	position: relative;
	overflow: hidden;
}

.progress-bar-fill {
	height: 100%;
	background-color: red;
	/* Set the color for the progress fill */
	transition: width 0.3s ease;
	/* Smooth transition */
}

.item-scroll-view {
	width: 95vw;
	height: 320rpx;
	white-space: nowrap;
	/* Prevent line break */
	overflow-x: scroll;
	/* Enable horizontal scrolling */
	display: flex;
	align-items: flex-start;
	/* Align children to the top */
	padding: 10rpx 20rpx;
	/* margin: 10rpx 20rpx; */
}

.scroll-item {
	width: 300rpx;
	height: 300rpx;
	display: inline-flex;
	/* Allows items to be in a row */
	flex-direction: column;
	/* Align children vertically */
	margin-right: 20rpx;
	/* Spacing between items */
	background-color: #ffffff;
	border-radius: 10rpx;
	box-shadow: 0 2rpx 5rpx rgba(0, 0, 0, 0.1);
	text-align: left;
}

.scroll-item-image {
	width: 100%;
	height: 220rpx;
	/* Adjust height for the image part */
	border-top-left-radius: 10rpx;
	border-top-right-radius: 10rpx;
}

.scroll-item-title {
	display: -webkit-box;
	/* Required for text overflow handling */
	font-size: 20rpx;
	/* Smaller font size */
	color: #333;
	height: 60rpx;
	/* Fixed height to display two lines */
	padding: 0 10rpx;
	/* Padding for some left and right spacing */
	overflow: hidden;
	/* Hide any overflow text */
	text-overflow: ellipsis;
	/* Add ellipsis for overflow text */
	-webkit-line-clamp: 2;
	/* Limit text to two lines */
	-webkit-box-orient: vertical;
	/* Required for the line clamp property */
}

.content-list {
	width: 700rpx;
	height: 400rpx;
	margin-top: 0;
	flex: 1;
}

.category-image {
	/* height: auto; */
	position: relative;
}

.top-title {
	position: absolute;
	bottom: 0;
	width: 100%;
	background: rgba(0, 0, 0, 0.5);
	/* Optional: Add a background for better readability */
	color: white;
	/* Optional: Set text color */
	text-align: left;
	/* Optional: Center the text */
}

.overlay-text {
	padding: 10px;
	font-size: 14px;
	/* Optional: Add padding */
}
</style>
