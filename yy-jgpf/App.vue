<script>
import { useUserStore } from '@/store/user';
import { useTabBarsStore } from '@/store/tabBars';

export default {
	onLaunch: function () {
		console.log('App Launch')
		useUserStore().getLoginInfo();
		useTabBarsStore().fetchTabBars();
		// uni.showTabBarRedDot({
		// 	index:1
		// })
		// uni.setTabBarItem({
		//   index: 0,
		//   text: '测试',
		//   iconPath: '/static/home/books-line.png',
		//   selectedIconPath: '/static/home/books-fill.png'
		// })
	},
	onShow: function () {
		console.log('App Show')
	},
	onHide: function () {
		console.log('App Hide')
	}
}
</script>

<style>
/*每个页面公共css */
@import 'static/iconfont.css';
@import 'static/uni.css'
</style>
