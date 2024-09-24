<template>
	<view class="verify-bg">
		<view class="form">
			<view class="old-password">
				<input class="uni-input" placeholder="输入旧密码" password="true" v-model="form.oldPassword" />
			</view>
			<view class="new-password">
				<input class="uni-input" placeholder="输入新密码" password="true" v-model="form.newPassword" />
			</view>
		</view>
	</view>
	<view class="finish-btn-view">
		<button class="finish-btn" @click="submitPassword">修改密码</button>
	</view>
</template>
<script>
import { userApi } from '@/api/user-api';
import { smartSentry } from '@/common/yy-sentry.js';
import { regular } from '@/common/yy-constant.js';
export default {
	data() {
		return {
			form: {
				oldPassword: '',
				newPassword: '',
			}
		}
	},
	onLoad(options) {

	},
	methods: {
		async submitPassword() {
			if (!this.form.oldPassword || this.form.oldPassword.trim() === '') {
				uni.showToast({
					title: '旧密码不能为空',
					icon: 'none',
					position: 'bottom',
				});
				return;
			}
			if (!this.form.newPassword || this.form.newPassword.trim() === '') {
				uni.showToast({
					title: '新密码不能为空',
					icon: 'none',
					position: 'bottom',
				});
				return;
			}

			try {
				const res = await userApi.modifyPassword(this.form);

				if (res.code == 0) {
					uni.showToast({
						title: '密码修改成功',
						position: 'bottom',
					})
					uni.navigateTo({
						url: "/pages/my/userDetail/userDetail"
					})
				} else {
					uni.showToast({
						title: res.msg,
						position: 'bottom',
					})
				}
			} catch (e) {

				smartSentry.captureError(e);
			}
		}
	}
}
</script>


<style>
page {
	background-color: #f5f5f5;
	/* Replace with your desired color */
}

.verify-bg {
	background-color: #ffffff;
	margin-bottom: 120rpx;
}

.form {
	width: 90%;
	margin: 0 30rpx;
}


.new-password {

	height: 100rpx;
	border-top: 1px solid #d3d3d3;
	/* 添加上边框并设置颜色 */
}


.finish-btn-view {
	margin: 30rpx 60rpx;
}

.finish-btn {
	color: white;
	/* Text color */
	background-color: black;
	/* Button background color */

	border: none;
	/* Optional: Remove any border */
	padding: 0 20rpx;
	/* Optional: Add some padding for better appearance */
	border-radius: 14rpx;
	/* Optional: Round the corners */
	box-shadow: 8px 4px 60px rgba(0, 0, 0, 0.3);
	/* Add shadow */
}
</style>
