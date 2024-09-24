<template>
	<view class="verify-bg">
		<view class="form">
			<view class="mobile-view">
				<text class="country-code">+86</text>
				<view class="separator"></view>
				<input class="uni-input" placeholder="输入手机号" v-model="form.mobile" />
				<text class="get-verify-code" @click="getCode">{{ getCodeText }}</text>
			</view>
			<view class="verify-view">
				<input class="uni-input" placeholder="输入验证码" v-model="form.code" />
			</view>
		</view>
	</view>
	<view class="finish-btn-view">
		<button class="finish-btn" @click="submitVerifyCode">完成</button>
	</view>
</template>
<script>
import { userApi } from '@/api/user-api';
import { smartSentry } from '@/common/yy-sentry.js';
import { regular } from '@/common/yy-constant.js';
export default {
	data() {
		return {
			getCodeText: '获取验证码',
			form: {
				registerType: 1,
				code: '',
				mobile: '',
			}
		}
	},
	onLoad(options) {

	},
	methods: {
		async getCode() {
			if (this.getCodeText != '获取验证码') {
				return;
			}
			try {
				// 校验手机号格式
				if (!this.form.mobile || this.form.mobile.trim() === '') {
					uni.showToast({
						title: '手机号不能为空',
						icon: 'none',
						position: 'bottom',
					});
					return;
				}
				if (!regular.mobilePattern.test(this.form.mobile)) {
					uni.showToast({
						title: '请输入有效的手机号',
						icon: 'none',
						position: 'bottom',
					});
					return;
				}

				this.getCodeText = '发送中';

				const res = await userApi.getBindMobileVerifyCode(this.form.mobile);
				console.log(res)
				if (res.code == 0) {
					console.log("aaaa")
					// Start 60-second countdown
					this.startCountdown();
				} else {
					console.log("bbbb")
					this.getCodeText = '获取验证码';
					uni.showToast({
						title: res.msg,
						position: 'bottom',
					})
				}
			} catch (e) {

				smartSentry.captureError(e);
			}
		},
		startCountdown() {
			this.countdown = 60;
			this.getCodeText = `${this.countdown}秒后重试`; // Display countdown text

			// Start the countdown interval
			this.countdownInterval = setInterval(() => {
				this.countdown--;

				if (this.countdown > 0) {
					this.getCodeText = `${this.countdown}秒后重试`;
				} else {
					clearInterval(this.countdownInterval); // Clear the interval
					this.getCodeText = '获取验证码'; // Reset to the original text
				}
			}, 1000); // Run every second
		},
		async submitVerifyCode() {
			// Check if verifyCode is empty
			if (!this.form.code || this.form.code.trim() === '') {
				uni.showToast({
					title: '验证码不能为空',
					icon: 'none',
					position: 'bottom',
				});
				return;
			}

			try {
				const res = await userApi.bindMobile(this.form);

				if (res.code == 0) {
					uni.showToast({
						title: '手机号修改成功',
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
	padding: 0 30rpx;
}

.country-code {
	margin: 0 20rpx;
}

.separator {
	width: 1rpx;
	height: 30rpx;
	background-color: #BBBBBB;
	margin: 0 10rpx;
}

.mobile-view {

	display: flex;
	align-items: center;
	/* Vertically centers the input */
	height: 100rpx;
	/* padding: 0 18rpx; */
	margin-top: 40rpx;

	justify-content: space-between;
	/* Ensure the icon is aligned to the right */

	padding-right: 60rpx;
	/* Add some padding to the right for spacing */
}

.verify-view {

	height: 100rpx;
	border-top: 1px solid #d3d3d3;
	/* 添加上边框并设置颜色 */
}

.get-verify-code {
	color: #707070;
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
