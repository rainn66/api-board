import { createRouter, createWebHistory } from 'vue-router'
import AppHome from '@/views/AppHome.vue'
import AppLogin from '@/views/common/AppLogin.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardForm from '@/views/board/BoardForm.vue'
import store from '@/vuex/store'

const requireAuth = () => (from, to, next) => {
	const token = localStorage.getItem("userToken");
	if (token) {
		store.state.isLogin = true;
		return next();
	}
	alert('로그인이 필요합니다.');
	next('/login');
}



const routes = [
	{
		path: '/',
		name: 'AppHome',
		component: AppHome
	},
	{
		path: '/login',
		name: 'AppLogin',
		component: AppLogin
	},
	{
		path: '/about',
		name: 'AppAbout',
		component: () => import('../views/AppAbout.vue')
	},
	{
		path: '/bbsMainList',
		name: 'BoardList',
		component: BoardList
	},
	{
		path: '/bbsMainInfo',
		name: 'BoardForm',
		component: BoardForm,
		beforeEnter: requireAuth()
	}
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
	linkActiveClass: "active",
	linkExactActiveClass: "exact-active"
})

export default router