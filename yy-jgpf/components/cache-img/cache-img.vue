<template>
	<image :src="img_url" :mode="mode" :lazy-load="lazyLoad" :style="setStyle" @click="clickImg"></image>
</template>
 
<script>
	export default {
		props: {
			mode: { //图片裁剪、缩放的模式
				type: String,
				default: ''
			},
			lazyLoad: { // 懒加载
				type: Boolean,
				default: true
			},
			imgSrc: { // 图片路径
				type: String
			},
			defaultImg: {
				type: String,
				default: '/static/empty-image.png'
			},
			setStyle: { // 图片样式
				type: String,
				default: ''
			}
		},
		data() {
			return {
				img_url: ''
			}
		},
		watch: {
			imgSrc: {
				async handler(src) {
					// console.log("imgsrc:", src)
					const sign = 'cachedimage-';
					const _img = uni.getStorageSync(sign + src); // 同步方法  
					if (_img) {
						// 得到缓存的图片 , 直接使用缓存的图片  
						this.img_url = _img.path;
						console.log("from cache...")
					} else {
						uni.getImageInfo({
								src: src
							})
							.then(res => {
								//data为一个数组，数组第一项为错误信息，第二项为返回数据  
					console.log("getImageInfo:", res)
								// let [error, res] = data;
								// if (error) {
								// 	this.img_url = src
								// } else {
									// #ifdef H5
									uni.setStorage({
										key: sign + src,
										data: {
											path: res.path
										}
									})
									// #endif
									// #ifdef APP-PLUS
									uni.saveFile({
											tempFilePath: res.path
										})
										.then(data => {
											let [saveFile_error, saveFile_res] = data;
											if (saveFile_res) {
												uni.setStorage({
													key: sign + src,
													data: {
														path: saveFile_res.savedFilePath
													}
												})
											}
										})
									// #endif
									// 拿到获取的图片信息  
									this.img_url = res.path;
									// console.log(this.img_url);
								// }
							})
							.catch(err => {
								console.error('uni.getImageInfo err', err);
								this.img_url = src;
							});
					}
				},
				immediate: true
			}
		},
		methods: {
			clickImg() {
				this.$emit("clickImg")
			}
		}
	}
</script>