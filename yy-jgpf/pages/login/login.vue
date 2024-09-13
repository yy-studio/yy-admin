<template>
	<view>
		<view class="uni-flex"
			style="-webkit-flex: 1;flex: 1;-webkit-justify-content: center;justify-content: center;-webkit-align-items: flex-start;align-items: flex-start;">
			<view class="top">
				<view class="login-top">
					<uni-icons class="top-right-close" size="30" color="#595959" type="closeempty"
						@click="onClose"></uni-icons>
				</view>
				<view class="login-title">
					<text class="uni-h4">登录 建工普法</text>
				</view>
				<view v-if="loginForm.loginType == '2'">
					<view class="login-input mobile-view">
						<input class="uni-input " focus placeholder="手机号/邮箱" :placeholder-style="placeholderStyle"
							v-model="loginForm.loginName" />
					</view>
					<view class="login-input password-view">
						<input class="uni-input" placeholder="密码" :password="showPassword"
							:placeholder-style="placeholderStyle" v-model="loginForm.password" />
						<uni-icons class="content-clear-icon" :type="showPassword ? 'eye-filled' : 'eye-slash-filled'"
							:size="22" color="#808080" @click="showPasswordEye"></uni-icons>
					</view>
				</view>
				<view v-if="loginForm.loginType == '1'">
					<view class="login-input mobile-view">
						<input class="uni-input " focus placeholder="请输入手机号" :placeholder-style="placeholderStyle"
							v-model="form.mobile" />
					</view>
					<view class="login-input password-view">
						<input class="uni-input" placeholder="请输入验证码"
							:placeholder-style="placeholderStyle" v-model="form.code" />
						<text class="get-verify-code" @click="getCode">{{getCodeText}}</text>
					</view>
				</view>

				<view class="uni-flex" style="-webkit-justify-content: flex-end;justify-content: flex-end;">
					<view class="uni-link forget-password" @click="forgetPassword">忘记密码</view>
				</view>
				<view class="login-btn-view">
					<button class="login-btn" @click="login">登录</button>
				</view>
				<view class="uni-flex" style="-webkit-justify-content: flex-end;justify-content: flex-end;">
					<view class="click-text forget-password" @click="changeLoginType">{{loginTypeText}}</view>
				</view>
			</view>
		</view>

		<view class="uni-flex"
			style="-webkit-flex: 1;flex: 1;-webkit-justify-content: center;justify-content: center;-webkit-align-items: flex-end;align-items: flex-end;position: absolute; bottom: 0; width: 100%;">
			<view style="width: 100%;">

				<view class="uni-flex uni-row" style="-webkit-justify-content: center;justify-content: center;">
					<view class="centered-line"></view>
					<view class="click-text">使用其他方式登录</view>
					<view class="centered-line"></view>
				</view>
				<view class="uni-flex uni-row"
					style="margin-top: 20rpx; -webkit-justify-content: center;justify-content: center;">
					<image class="apple-id-login" src="../../static/login/appleid_button@2x.png"></image>
					<image class="wechat-login" src="../../static/login/wechat_icon_512.png"></image>
					<image class="qq-login" src="../../static/login/brand_qq_icon_512.png"></image>
					<image class="qq-login" src="../../static/login/weibo_512.png"></image>
				</view>
				<view class="uni-flex bottom-register" style="-webkit-justify-content: center;justify-content: center;">
					<view class="click-text-register" @click="register">还没有账户？立即注册</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { loginApi } from '@/api/login-api';
import { smartSentry } from '@/common/yy-sentry.js';
import { useUserStore } from '@/store/user';
import { regular } from '@/common/yy-constant.js';


const loginForm = reactive({
	loginName: '',
	mobile: '',
	email: '',
	password: '',
	loginType: '2',
	loginDevice: 4,
	userNameType: 0,
});

const form = reactive({
	code: '',
	mobile: '',
	loginDevice: 4,
});

const placeholderStyle = "color:#9a9a9a;font-size:14px";
const showPassword = ref(true);
const loginTypeText = ref('手机验证码登录');
const getCodeText = ref('获取验证码');
const countdown = ref(60);

function validateInput() {

	if (regular.mobilePattern.test(loginForm.loginName)) {
		loginForm.mobile = loginForm.loginName;
		loginForm.email = ''; // Clear email field
		loginForm.userNameType = 1;
		return true;
	} else if (regular.emailPattern.test(loginForm.loginName)) {
		loginForm.email = loginForm.loginName;
		loginForm.mobile = ''; // Clear mobile field
		loginForm.userNameType = 2;
		return true;
	} else {
		return false;
	}
}

async function login() {

	if (loginForm.loginType == 2){
		if (!validateInput()) {
			uni.showToast({
				icon: 'none',
				title: '请输入有效的手机号或邮箱',
				position: 'bottom',
				mask: true,
			});
			return;
		}

		if (!loginForm.password) {
			uni.showToast({
				icon: 'none',
				title: '请输入密码',
				position: 'bottom',
			});
			return;
		}
	} else if (loginForm.loginType == 1){
		if (!regular.mobilePattern.test(form.mobile)){
			
				uni.showToast({
					icon: 'none',
					title: '请输入有效的手机号码',
				position: 'bottom',
				});
				return;
		}
		if(!form.code) {
			uni.showToast({
				icon: 'none',
				title: '请输入验证码',
				position: 'bottom',
			});
			return;
		}
	}

	try {
		uni.showLoading({ title: '登录中' });
		let res;
		if(loginForm.loginType == 2){
			res = await loginApi.login(loginForm);
		} else if(loginForm.loginType == 1){
			res = await loginApi.loginByVerify(form);
		}
		

		uni.showToast({ title: '登录成功',
				position: 'bottom', });
		//更新用户信息到 pinia
		useUserStore().setUserLoginInfo(res.data);

		uni.switchTab({ url: '/pages/index/index' });
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

function forgetPassword() {
	console.log("forget password clicked.");
}

function changeLoginType() {
	if(loginForm.loginType == '1') {
		loginForm.loginType = '2'
		loginTypeText.value = '手机验证码登录'
	} else {
		loginForm.loginType = '1'
		loginTypeText.value = '账号密码登录'
	}
}
function register() {
	uni.navigateTo({ url: '/pages/my/register/register' });
}


async function  getCode() {
	if(getCodeText.value != '获取验证码') {
		return;
	}

	if (!regular.mobilePattern.test(form.mobile)){
		
		uni.showToast({
			icon: 'none',
			title: '请输入有效的手机号码',
		});
		return;
	}
	
	console.log('getcode...' + getCodeText.value)
	try {
	  getCodeText.value = '发送中';

	  const res = await loginApi.getLoginVerifyCode(form.mobile);

		if (res.code == 0) {
		  // Start 60-second countdown
		  startCountdown();
		} else {
			uni.showToast({
				title: res.msg,
				position: 'bottom',
			})
		  getCodeText.value = '获取验证码';
		}
	} catch (e) {

	  getCodeText.value = '获取验证码';
	  smartSentry.captureError(e);
	}
}
function startCountdown() {
  countdown.value = 60;
  getCodeText.value = `${countdown.value}秒后重试`; // Display countdown text

  // Start the countdown interval
  let countdownInterval = setInterval(() => {
	countdown.value--;

	if (countdown.value > 0) {
	  getCodeText.value = `${countdown.value}秒后重试`;
	} else {
	  clearInterval(countdownInterval); // Clear the interval
	  getCodeText.value = '获取验证码'; // Reset to the original text
	}
  }, 1000); // Run every second
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
	align-items: center;
	/* Vertically centers the input */
	border: 1rpx solid;
	border-color: darkgrey;
	height: 100rpx;
	margin: 0rpx 60rpx;
	padding: 0 18rpx;
}

.mobile-view {
	border-top-right-radius: 10rpx;
	border-top-left-radius: 10rpx;
	border-bottom: 0rpx;
}

.password-view {
	justify-content: space-between;
	/* Ensure the icon is aligned to the right */
	border-bottom-left-radius: 10rpx;
	border-bottom-right-radius: 10rpx;
	padding-right: 40rpx;
	/* Add some padding to the right for spacing */
}

.forget-password {
	margin-top: 20rpx;
	margin-right: 100rpx;
	margin-bottom: 30rpx;
}

.login-btn-view {
	margin: 30rpx 90rpx;
}

.login-btn {
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

.click-text {
	color: #404040;
	font-size: 28rpx;
}

.centered-line {
	width: 70px;
	/* Adjust the width as needed */
	height: 1px;
	/* Set the height to 1px for a thin line */
	background-color: #bfbfbf;
	/* Set the line color */
	margin: auto 20rpx;
	/* Center the line horizontally */
}

.click-text-register {
	color: #808080;
	font-size: 24rpx;
}

.apple-id-login {
	height: 40px;
	width: 160px;
	margin: auto 16rpx;
}

.wechat-login {
	height: 48px;
	width: 48px;
	margin: auto 16rpx;
}

.qq-login {
	height: 36px;
	width: 36px;
	margin: auto 16rpx;
}


.bottom-register {
	margin: 50rpx 0;
}

.get-verify-code {
	color: #707070;
}
</style>