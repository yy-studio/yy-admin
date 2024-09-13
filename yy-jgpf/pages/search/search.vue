<template>
	<view class="container">
		<view class="search-bar">
			<icon type="search" size="20" />
			<input class="search-input" v-model="searchQuery" placeholder="Search..." />
			<button class="cancel-button" @click="cancelSearch">取消</button>
		</view>
		<view class="search-history" v-if="!searchResults.length">
			<text class="history-title">Search History</text>
			<view v-for="(item, index) in searchHistory" :key="index" class="history-item" @click="performSearch(item)">
				{{ item }}
			</view>
		</view>
		<view class="search-results" v-if="searchResults.length">
			<text class="results-title">Search Results</text>
			<view v-for="(result, index) in searchResults" :key="index" class="result-item">
				{{ result }}
			</view>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			searchQuery: '',
			searchHistory: ['Example 1', 'Example 2', 'Example 3'],
			searchResults: []
		};
	},
	methods: {
		cancelSearch() {
			this.searchQuery = '';
			this.searchResults = [];
		},
		performSearch(query) {
			this.searchQuery = query;
			// Simulate search results
			this.searchResults = ['Result 1', 'Result 2', 'Result 3'];
			if (!this.searchHistory.includes(query)) {
				this.searchHistory.push(query);
			}
		}
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
	padding: 16px;
}

.search-bar {
	display: flex;
	align-items: center;
	margin-bottom: 16px;
}

.search-icon {
	margin-right: 8px;
}

.search-input {
	flex: 1;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.cancel-button {
	margin-left: 8px;
	padding: 8px 16px;
	background-color: #f0f0f0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.search-history,
.search-results {
	margin-top: 16px;
}

.history-title,
.results-title {
	font-weight: bold;
	margin-bottom: 8px;
}

.history-item,
.result-item {
	padding: 8px;
	border-bottom: 1px solid #ccc;
	cursor: pointer;
}
</style>