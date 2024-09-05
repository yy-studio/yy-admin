<template>
	<view class="uni-flex uni-column">
	  <view class="flex-item flex-item-V uni-bg-red">A</view>
	  <view class="flex-item flex-item-V uni-bg-green">B</view>
	  <view class="flex-item flex-item-V uni-bg-blue">C</view>
	</view>
</template>
<script setup>
  import { reactive, ref } from 'vue';
  import { onShow } from '@dcloudio/uni-app';

  import { loginApi } from '@/api/system/login-api';
  import { LOGIN_DEVICE_ENUM } from '@/constants/system/login-device-const';
  import { encryptData } from '@/lib/encrypt';
  import { useUserStore } from '@/store/modules/system/user';
  import { smartSentry } from '@/lib/smart-sentry';

  const loginForm = reactive({
    loginName: 'admin',
    password: '123456',
    captchaCode: '',
    captchaUuid: '',
    loginDevice: LOGIN_DEVICE_ENUM.H5.value,
  });

  const loginCheckBoxRef = ref();
  async function login() {
    if (!loginCheckBoxRef.value.agreeFlag) {
      uni.showToast({
        icon: 'none',
        title: '请阅读并同意《用户协议》、《隐私政策》',
      });
      return;
    }
    if (!loginForm.loginName) {
      uni.showToast({
        icon: 'none',
        title: '请输入用户名',
      });
      return;
    }
    if (!loginForm.password) {
      uni.showToast({
        icon: 'none',
        title: '请输入密码',
      });
      return;
    }

    try {
      uni.showLoading({ title: '登录中' });
      // 密码加密
      let encryptPasswordForm = Object.assign({}, loginForm, {
        password: encryptData(loginForm.password),
      });
      const res = await loginApi.login(encryptPasswordForm);
      stopRefreshCaptchaInterval();
      uni.showToast({ title: '登录成功' });
      //更新用户信息到 pinia
      useUserStore().setUserLoginInfo(res.data);

      uni.switchTab({ url: '/pages/home/index' });
    } catch (e) {
      if (e.data && e.data.code !== 0) {
        loginForm.captchaCode = '';
        getCaptcha();
      }
      smartSentry.captureError(e);
      uni.hideLoading();
    }
  }

  //--------------------- 验证码 ---------------------------------

  const captchaBase64Image = ref('');

  async function getCaptcha() {
    try {
      let captchaResult = await loginApi.getCaptcha();
      captchaBase64Image.value = captchaResult.data.captchaBase64Image;
      console.log(captchaResult.data.captchaBase64Image, 2);
      loginForm.captchaUuid = captchaResult.data.captchaUuid;
      beginRefreshCaptchaInterval(captchaResult.data.expireSeconds);
    } catch (e) {
      console.log(e);
    }
  }

  let refreshCaptchaInterval = null;

  function beginRefreshCaptchaInterval(expireSeconds) {
    if (refreshCaptchaInterval === null) {
      refreshCaptchaInterval = setInterval(getCaptcha, (expireSeconds - 5) * 1000);
    }
  }

  function stopRefreshCaptchaInterval() {
    if (refreshCaptchaInterval != null) {
      clearInterval(refreshCaptchaInterval);
      refreshCaptchaInterval = null;
    }
  }

  onShow(getCaptcha);
</script>
<style lang="scss" scoped>
  
</style>
