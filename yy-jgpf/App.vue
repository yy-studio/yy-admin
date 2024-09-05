<script>
	
  import { useUserStore } from '@/store/user';
	export default {
		onLaunch: function() {
			console.log('App Launch')
			useUserStore().getLoginInfo();
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import 'static/iconfont.css';
	@import 'static/uni.css'
</style>
