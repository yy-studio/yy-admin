<template>
	<view class="container">

		<uni-list :border="false">
			<uni-list-item showArrow ellipsis="1" v-for="(item, index) in searchResults" :key="index" :border="true"
				:title="item.title" :note="item.tags"  rightText=""
				:to="`/pages/detail/detail?id=${item.contentId}`" />
		</uni-list>
	</view>
</template>

<script>
import { userApi } from '@/api/user-api';
export default {
	data() {
		return {

			searchResults: [
			],
		}
	},
	mounted() {
		this.search();
	},
	methods: {
		async search() {

			try {
				let queryParam = {
					pageNum: 1,
					pageSize: 10,
				}
				const res = await userApi.queryCollectList(queryParam);
				// console.log(res)
				this.searchResults = res.data.list;

			} catch (e) {

			}
		},

	}
}
</script>

<style>
	.container {
		padding: 0;
	}
</style>
