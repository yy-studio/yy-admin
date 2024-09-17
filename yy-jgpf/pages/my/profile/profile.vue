<template>
	<view class="content">
		<template v-if="username">
			<view class="upload-avatar">
				<uni-file-picker limit="1" :del-icon="false" disable-preview :imageStyles="imageStyles"
					v-model="avatarFile" @select="onSelectFile" file-mediatype="image"></uni-file-picker>
			</view>
			<view class="text-area">
				<text class="title">{{ username }}</text>
			</view>
		</template>
		<template v-else>
			<image class="logo" src="/static/home/person.png"></image>
			<view class="text-area">
				<text class="title" @click="toLogin">{{ title }}</text>
			</view>
		</template>
		<view class="profile-list">

			<uni-section title="">
				<uni-list>
					<uni-list-item :show-extra-icon="true" showArrow :extra-icon="subIcon" title="我的订阅" clickable />
					<uni-list-item :show-extra-icon="true" showArrow :extra-icon="favIcon" title="收藏文章"  clickable
						@click="toCollect"/>
					<uni-list-item :show-extra-icon="true" showArrow :extra-icon="readIcon" title="阅读记录" clickable
						@click="toReadingRecord" />
				</uni-list>
			</uni-section>

			<uni-section title="">
				<uni-list>
					<uni-list-item :show-extra-icon="true" showArrow :extra-icon="settingIcon" title="设置" />
				</uni-list>
			</uni-section>
		</view>
	</view>
</template>

<script>
import { useUserStore } from '@/store/user';
import { fileApi } from '@/api/file-api';
import { toRaw } from 'vue';
import { USER_TOKEN } from '@/common/local-storage-key-const.js';
export default {
	data() {
		return {
			title: '登录/注册',
			username: '',
			subIcon: {
				color: '#F99323',
				size: '22',
				type: 'cart'
			},
			favIcon: {
				color: '#F99323',
				size: '22',
				type: 'star'
			},
			readIcon: {
				color: '#AAAAAA',
				size: '22',
				type: 'list'
			},

			settingIcon: {
				color: '#AAAAAA',
				size: '22',
				type: 'gear-filled'
			},
			avatarFile: [],
			imageStyles: {
				width: 140,
				height: 140,
				border: {
					radius: '50%'
				}
			},
		}
	},
	onShow() {
		const userStore = useUserStore();
		console.log(userStore.username);
		this.username = userStore.username;
		let avatar = {
			url: userStore.avatar,
		}
		this.avatarFile.push(avatar);
	},
	methods: {
		toLogin() {
			uni.navigateTo({
				url: '/pages/login/login'
			})
		},
		// --------------------- 文件 ---------------------
		onSelectFile(param) {
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
				console.log("aaa", toRaw(this.avatarFile));
				let res = await fileApi.upload(tempFilePath, 2);
				console.log("返回", res);
				// res.data.tempFilePath = tempFilePath;
				// this.avatarFile = res.data;
			} catch (e) {
				console.error(e);
			} finally {
				uni.hideLoading();
			}
		},

		onDeleteFile(param) {
			// if (!param.tempFilePath) {
			//   return;
			// }
			// _.remove(form.feedbackAttachment, (e) => e.tempFilePath === param.tempFilePath);
		},
		toReadingRecord() {
			let token = uni.getStorageSync(USER_TOKEN);
			if (!token) {
				uni.navigateTo({
					url: "/pages/login/login"
				})
			} else {
				uni.navigateTo({
					url: '/pages/my/readingRecord/readingRecord'
				})
			}
		},
		toCollect() {
			let token = uni.getStorageSync(USER_TOKEN);
			if (!token) {
				uni.navigateTo({
					url: "/pages/login/login"
				})
			} else {
				uni.navigateTo({
					url: '/pages/my/collect/collect'
				})
			}
		}
	}
}
</script>

<style>
.content {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: start;
}

.logo {
	height: 140rpx;
	width: 140rpx;
	padding: 40rpx;
	margin-top: 100rpx;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 50rpx;
	background-color: #f0f0f0;
	border-radius: 10%;
}

.circle {
	border-radius: 10%;
}

.upload-avatar {
	margin-top: 100rpx;
	margin-bottom: 30rpx;
}

.text-area {
	display: flex;
	justify-content: center;
}

.title {
	font-size: 30rpx;
	color: #8f8f94;
}

.profile-list {
	width: 100%;
	margin-top: 20rpx;
}
</style>
