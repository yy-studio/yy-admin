<template>
	<view class="container">

		<uni-search-bar @confirm="search" :focus="true" v-model="searchValue" @blur="blur" @focus="focus" @input="input"
			@cancel="cancel" @clear="clear">
		</uni-search-bar>
		<view class="line-h"></view>
		<view class="search-history" v-if="!searchResults.length">
			<text class="history-title">历史搜索</text>
			<view v-for="(item, index) in searchHistory" :key="index" class="history-item" @click="performSearch(item)">
				{{ item }}
			</view>
		</view>
		<view class="search-results" v-if="searchResults.length">

			<uni-list :border="false">
				<uni-list-item ellipsis="1" v-for="(item, index) in searchResults" :key="index" :border="true"
					:title="item.title" :note="item.tags" :thumb="item.coverImage[0].fileUrl" thumb-size="lg"
					rightText="" :to="`/pages/detail/detail?id=${item.id}`"  />
			</uni-list>
		</view>
	</view>
</template>

<script>
import { contentApi } from '@/api/content-api';
export default {
	data() {
		return {
			searchValue: '',
			searchHistory: [],
			searchResults: [
				// {
				// 	title: "",
				// 	coverImage: [
				// 		{
				// 			fileUrl: "",
				// 		}
				// 	]
				// },
			],
		};
	},
	mounted() {
		this.loadSearchHistory();
	},
	methods: {
		async search(res) {
			const query = res.value;
			this.addToSearchHistory(query);

			try {
				let itemListParam = {
					keywords: query,
					pageNum: 1,
					pageSize: 10,
				}
				const res = await contentApi.queryPage(itemListParam);
				// console.log(res)
				this.searchResults = res.data.list;

			} catch (e) {

			}
		},
		input(res) {
			// console.log('----input:', res)
		},
		clear(res) {
			// uni.showToast({
			// 	title: 'clear事件，清除值为：' + res.value,
			// 	icon: 'none'
			// })
		},
		blur(res) {
			// uni.showToast({
			// 	title: 'blur事件，输入值为：' + res.value,
			// 	icon: 'none'
			// })
		},
		focus(e) {
			// uni.showToast({
			// 	title: 'focus事件，输出值为：' + e.value,
			// 	icon: 'none'
			// })
		},
		cancel(res) {
			uni.navigateBack();
		},
		performSearch(query) {
			this.searchValue = query;
			//触发搜索
			this.search({
				value: query
			});
			// this.searchResults = [query + ' Result 1', query + ' Result 2', query + ' Result 3'];
		},
		loadSearchHistory() {
			const history = uni.getStorageSync('searchHistory');
			if (history) {
				this.searchHistory = JSON.parse(history);
			}
		},
		addToSearchHistory(query) {
			if (!this.searchHistory.includes(query)) {
				this.searchHistory.unshift(query);
				if (this.searchHistory.length > 10) {
					this.searchHistory.pop();
				}
				uni.setStorageSync('searchHistory', JSON.stringify(this.searchHistory));
			}
		},
	},
	watch: {
		searchQuery(newQuery) {
			if (newQuery === '') {
				this.searchResults = [];
			}
		}
	}
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow-y: auto;
  padding-top: calc(5px + env(safe-area-inset-top));
}


.line-h {
	margin-top: 4rpx;
	height: 2rpx;
	background-color: #cccccc;
}

.search-history,
.search-results {
	margin: 26rpx 16rpx;

}

.history-title {
	margin-bottom: 18rpx;
	color: #555555;
}

.history-item {
	padding: 16rpx;
	cursor: pointer;
	color: #aaaaaa;
}

.result-item {
	padding: 16rpx;
	cursor: pointer;
}
</style>