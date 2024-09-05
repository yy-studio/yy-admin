<template>
	<view class="verify-bg">
		<view class="verify-view">
		  <input class="uni-input" placeholder="输入验证码" v-model="form.code"  />
		  <text class="get-verify-code" @click="getCode">{{getCodeText}}</text>
		</view>
	</view>
	<view class="finish-btn-view">
		<button class="finish-btn" @click="submitVerifyCode">完成</button>
	</view>
</template>
<script>
  import { loginApi } from '@/api/login-api';
  import { smartSentry } from '@/common/yy-sentry.js';
	export default {
		data() {
			return {
				bannerTitle : '验证',
				getCodeText : '获取验证码',
				form : {
					registerType: 1,
					code: '',
					mobile: '',
					email: '',
				}
			}
		},
		onLoad(options) {
		  this.form.registerType = options.registerType; // Get the registerType parameter
		  // Set banner.title based on registerType
		  if (this.form.registerType === '1') {
			this.bannerTitle = '手机验证'; // Set title for phone verification
		  } else if (this.form.registerType === '2') {
			this.bannerTitle = '邮箱验证'; // Set title for email verification
		  }
		  this.form.mobile = options.mobile;
		  this.form.email = options.email;

		  // Set the navigation bar title dynamically
		  uni.setNavigationBarTitle({
			title: this.bannerTitle,
		  });
		},
		methods: {
			async getCode() {
				if(this.getCodeText != '获取验证码') {
					return;
				}
				try {
				  this.getCodeText = '发送中';

				  const res = await loginApi.getRegisterVerifyCode(this.form);

					if (res.code == 0) {
					  // Start 60-second countdown
					  this.startCountdown();
					} else {
						uni.showToast({
							title: res.msg,
							position: 'bottom',
						})
					  this.getCodeText = '获取验证码';
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
				  });
				  return;
				}
				
				try {
					const res = await loginApi.verifyCode(this.form);
				
					if (res.code == 0) {
						uni.showToast({
							title: '验证成功，请登录',
							position: 'bottom',
						})
						uni.navigateTo({ url: '/pages/login/login'});
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
  background-color: #f5f5f5; /* Replace with your desired color */
}
.verify-bg {
	background-color: #ffffff;
	margin-bottom: 120rpx;
}
.verify-view {
	
  display: flex;
  align-items: center; /* Vertically centers the input */
  height: 100rpx;
  /* padding: 0 18rpx; */
  margin-top: 40rpx;
  
  justify-content: space-between; /* Ensure the icon is aligned to the right */

  padding-right: 60rpx; /* Add some padding to the right for spacing */
}
.get-verify-code {
	color: #707070;
}

.finish-btn-view {
	margin: 30rpx 60rpx;
}
.finish-btn {
  color: white; /* Text color */
  background-color: black; /* Button background color */
  
  border: none; /* Optional: Remove any border */
  padding: 0 20rpx; /* Optional: Add some padding for better appearance */
  border-radius: 14rpx; /* Optional: Round the corners */
  box-shadow: 8px 4px 60px rgba(0, 0, 0, 0.3); /* Add shadow */
}
</style>
