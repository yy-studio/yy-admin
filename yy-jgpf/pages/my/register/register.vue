<template>
  <view>
	<view class="uni-flex" style="-webkit-flex: 1;flex: 1;-webkit-justify-content: center;justify-content: center;-webkit-align-items: flex-start;align-items: flex-start;">
		<view class="top">
			<view class="login-top">
			  <uni-icons class="top-right-close" size="30" color="#595959" type="closeempty" @click="onClose"></uni-icons>
			</view>
			<view class="login-title">
			  <text class="uni-h4">注册 建工普法</text>
			</view>
			<view class="login-input username-view">
			  <input class="uni-input " focus placeholder="昵称" :placeholder-style="placeholderStyle" v-model="loginForm.username" />
			</view>
			<view class="login-input mobile-view">
			  <input class="uni-input " placeholder="手机号/邮箱" :placeholder-style="placeholderStyle" v-model="loginForm.loginName" />
			</view>
			<view class="login-input password-view">
			  <input class="uni-input" placeholder="密码" :password="showPassword" :placeholder-style="placeholderStyle" v-model="loginForm.password"  />
			  <uni-icons
				class="content-clear-icon"
				:type="showPassword ? 'eye-filled' : 'eye-slash-filled'"
				:size="22"
				color="#808080"
				@click="showPasswordEye"
			  ></uni-icons>
			</view>
			
			<view class="uni-flex " style="-webkit-justify-content: center;justify-content: center;">
				<text class="tips-text">注册即是同意建工普法的</text>
				<view class="uni-link" @click="toUserAgreementPage">用户协议</view>
				<text class="tips-text"> 和 </text>
				<view class="uni-link" @click="toPrivacyStatementPage">隐私声明</view>
			</view>
			<view class="login-btn-view">
				<button class="login-btn" @click="register">注册</button>
			</view>
			<view class="uni-flex" style="-webkit-justify-content: flex-end;justify-content: flex-end;">
				<view class="to-login" @click="toLoginPage">已有账号？登录</view>
			</view>
		</view>
	</view>
	
  </view>
</template>

<script setup>
import { reactive, ref } from 'vue';
  import { loginApi } from '@/api/login-api';
  import { smartSentry } from '@/common/yy-sentry.js';
  import { regular } from '@/common/yy-constant.js';
  import { useUserStore } from '@/store/user';
  
  
  const loginForm = reactive({
	username: '',
	loginName: '',
    mobile: '',
    email: '',
    password: '',
    loginDevice: 4,
  });

const placeholderStyle = "color:#9a9a9a;font-size:14px";
const showPassword = ref(true);
const registerType = ref(0); // 1手机号，2邮箱

function validateInput() {
  
  if (regular.mobilePattern.test(loginForm.loginName)) {
	loginForm.mobile = loginForm.loginName;
	loginForm.email = ''; // Clear email field
	registerType.value = 1;
	return true;
  } else if (regular.emailPattern.test(loginForm.loginName)) {
	loginForm.email = loginForm.loginName;
	loginForm.mobile = ''; // Clear mobile field
	registerType.value = 2;
	return true;
  } else {
	return false;
  }
}
	
async function register() {
    
    if (!validateInput()) {
      uni.showToast({
        icon: 'none',
        title: '请输入有效的手机号或邮箱',
		position: 'bottom',
		mask: true,
      });
      return;
    }

    try {
      uni.showLoading({ title: '注册中' });

      const res = await loginApi.userRegister(loginForm);

      uni.showToast({ title: registerType.value == 1 ? '注册成功，请验证手机号' : '注册成功，请验证邮箱',
				position: 'bottom',});

      uni.navigateTo({ url: '/pages/my/verifyCode/verifyCode?registerType=' + registerType.value + '&mobile=' + loginForm.mobile + '&email=' + loginForm.email });
    } catch (e) {

      smartSentry.captureError(e);
      uni.hideLoading();
    }
  }
  
function onClose() {
	uni.navigateBack();
}

function showPasswordEye() {
  showPassword.value = !showPassword.value;
}

function toUserAgreementPage(){
	console.log("toUserAgreementPage clicked.")
}

function toPrivacyStatementPage() {
	console.log("toPrivacyStatementPage clicked.")
}
function toLoginPage() {
	uni.navigateTo({ url: '/pages/login/login' });
}
</script>

<style>
.top {
	width: 100%;
}
.login-top {
  position: relative;
  height: 160rpx;
}
.top-right-close {
  position: absolute;
  top: 80rpx;
  right: 60rpx;
}
.login-title {
  margin-top: 20rpx;
  margin-left: 60rpx;
  margin-bottom: 60rpx;
}
.login-input {
  display: flex;
  align-items: center; /* Vertically centers the input */
  border: 1rpx solid;
  border-color: darkgrey;
  height: 100rpx;
  margin: 0rpx 60rpx;
  padding: 0 18rpx;
}
.username-view {
  border-top-right-radius: 10rpx;
  border-top-left-radius: 10rpx;
  border-bottom: 0rpx;
}
.mobile-view {
  border-bottom: 0rpx;
}
.password-view {
  justify-content: space-between; /* Ensure the icon is aligned to the right */
  border-bottom-left-radius: 10rpx;
  border-bottom-right-radius: 10rpx;
  padding-right: 40rpx; /* Add some padding to the right for spacing */
  margin-bottom: 30rpx;
}

.tips-text {
	color:#808080;
	font-size:26rpx;
}
.login-btn-view {
	margin: 80rpx 90rpx;
}
.login-btn {
  color: white; /* Text color */
  background-color: black; /* Button background color */
  
  border: none; /* Optional: Remove any border */
  padding: 0 20rpx; /* Optional: Add some padding for better appearance */
  border-radius: 14rpx; /* Optional: Round the corners */
  box-shadow: 8px 4px 60px rgba(0, 0, 0, 0.3); /* Add shadow */
}

.to-login{
	color:#404040;
	font-size:32rpx;
	font-size:24rpx;
	margin-right: 90rpx;
}
</style>