<template>
	<view class="container">
		<uni-section title="个人信息">
			<uni-list class="list-style">
				<uni-list-item :border="false" title="头像" clickable>
					<!-- 自定义 body -->
					<template v-slot:body>
						<view class="uni-flex uni-row text-center">
							<text class="slot-box slot-text">头像</text>
							<uni-file-picker limit="1" :del-icon="false" disable-preview :imageStyles="imageStyles"
								v-model="avatarFile" @select="onSelectFile" file-mediatype="image"></uni-file-picker>
						</view>
					</template>
				</uni-list-item>
				<uni-list-item title="昵称">
					<!-- 自定义 body -->
					<template v-slot:body>
						<view class="uni-flex uni-row text-center">
							<text class="slot-box slot-text">昵称</text>
							<input class="uni-input" v-model="userStore.username" name="username" @blur="onblur" />
						</view>
					</template>
				</uni-list-item>
			</uni-list>
		</uni-section>

		<uni-section title="绑定账号">
			<uni-list :border="false" class="list-style">
				<uni-list-item class="has-arrow" showArrow title="手机号" link to="/pages/my/bindMobile/bindMobile">
					<template v-slot:body>
						<view class="uni-flex uni-row text-center">
							<text class="slot-box slot-text">手机号</text>
							<text class="slot-box slot-text">{{ userStore.mobile }}</text>
						</view>
					</template>
				</uni-list-item>
				<uni-list-item class="has-arrow" showArrow title="邮箱">
					<template v-slot:body>
						<view class="uni-flex uni-row text-center">
							<text class="slot-box slot-text">邮箱</text>
							<text class="slot-box slot-text">{{ userStore.email }}</text>
						</view>
					</template>
				</uni-list-item>
				<uni-list-item title="绑定账号">
					<template v-slot:body>
						<view class="uni-flex uni-row text-center">
							<text class="slot-box slot-text">绑定账号</text>
							<uni-icons class="bind-icon" :color="userStore.wechatOpenid ? 'green' : defaultColor"
								type="weixin" size="40"></uni-icons>
							<uni-icons class="bind-icon" :color="userStore.weiboUid ? 'red' : defaultColor" type="weibo" size="40"></uni-icons>
						</view>
					</template>
				</uni-list-item>
			</uni-list>
		</uni-section>

		<uni-section title="">
			<uni-list class="list-style">
				<uni-list-item class="has-arrow" showArrow title="修改密码" link to="/pages/my/changePassword/changePassword" />
			</uni-list>
		</uni-section>

	</view>
</template>

<script>
import { fileApi } from '@/api/file-api';
import { userApi } from '@/api/user-api';
import { useUserStore } from '@/store/user';
import { USER_TOKEN } from '@/common/local-storage-key-const.js';
export default {
	data() {
		return {
			userStore: {},
			avatarFile: [],
			imageStyles: {
				width: 100,
				height: 100,
				border: {
					radius: '50%'
				}
			},
			defaultColor: 'gray',

		}
	},
	onShow() {
		let token = uni.getStorageSync(USER_TOKEN);
		if (!token) {
			uni.navigateTo({
				url: "/pages/login/login"
			})
		}
		this.setData();
	},
	methods: {

		async getUserDetail() {
			useUserStore().getLoginInfo();
			this.setData();
		},
		setData() {

			this.userStore = useUserStore();
			this.username = this.userStore.username;
			let avatar = {
				url: this.userStore.avatar,
			}
			this.avatarFile.push(avatar);
		},
		// --------------------- 文件 ---------------------
		onSelectFile(param) {
			console.log("onselect", param.tempFilePaths);
			let tempFilePaths = param.tempFilePaths;
			let tempFiles = param.tempFiles;
			const maxSize = 3 * 1024 * 1024; // 3MB in bytes

			for (let i = 0; i < tempFiles.length; i++) {
				if (tempFiles[i].size > maxSize) {
					uni.showToast({
						title: '文件大小不能超过3MB',
						icon: 'none'
					});
					this.avatarFile = [];
					return;
				}
			}

			for (const tempFilePath of tempFilePaths) {
				this.upload(tempFilePath);
			}

			console.log(param, 2);
			// console.log(feedbackFile, 2);
		},

		async upload(tempFilePath) {
			try {
				uni.showLoading({
					title: '上传中'
				});
				let res = await fileApi.upload(tempFilePath, 2);
				// res.data.tempFilePath = tempFilePath;
				// this.avatarFile = res.data;
			} catch (e) {
				console.error(e);
			} finally {
				uni.hideLoading();
			}
		},
		async onblur() {
			
			try {
				let res = await userApi.updateUsername(this.userStore.username);
				uni.showToast({
					title: res.data,
					position:'bottom',
					icon: 'none'
				});
			} catch (e) {
				console.error(e);
			}
		}
	}
}
</script>

<style>
.container {
	background-color: #eeeeee;
}

.list-style {
	padding: 10rpx 30rpx;
}

.text-center {

	align-items: center;
}

.slot-box {
	width: 150rpx;
	margin-right: 40rpx;
}

.slot-text {
	font-size: 14px;
}

.has-arrow {
	margin-right: 30rpx;
}

.bind-icon {
	width: 80rpx;
	color: red;
}
</style>
