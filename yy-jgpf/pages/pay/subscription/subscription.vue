<template>
	<view>
		订阅
	</view>
</template>

<script>
import { useUserStore } from '@/store/user';
import { USER_TOKEN } from '@/common/local-storage-key-const.js';
	export default {
		data() {
			return {
				
			}
		},
		onLoad() {
			let token = uni.getStorageSync(USER_TOKEN);
			if (!token) {
			  uni.navigateTo({
			  	url:"/pages/login/login"
			  })
			}
			console.log("getToken:", token);
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
