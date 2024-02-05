import { createRouter, createWebHistory } from 'vue-router'
import AppHome from '@/views/AppHome.vue'
import AppLogin from '@/views/common/AppLogin.vue'
import SignUp from '@/views/common/AppSignUp.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardAdd from '@/views/board/BoardAdd.vue'
import BoardEdit from '@/views/board/BoardEdit.vue'
import store from '@/vuex/store'

const requireAuth = () => (from, to, next) => {
	const token = localStorage.getItem("userToken");
	if (token) {
		store.state.isLogin = true;
		return next();
	} else {
		store.state.isLogin = false;
	}
}


const routes = [
	{
		path: '/',
		name: 'AppHome',
		component: AppHome,
	},
	{
		path: '/login',
		name: 'AppLogin',
		component: AppLogin,
	},
	{
		path: '/about',
		name: 'AppAbout',
		component: () => import('../views/AppAbout.vue'),
	},
	{
		path: '/board',
		name: 'BoardList',
		component: BoardList,
	},
	{
		path: '/boardAdd',
		name: 'BoardAdd',
		component: BoardAdd,
		afterEnter: requireAuth()
	},
	{
		path: '/boardEdit',
		name: 'BoardEdit',
		component: BoardEdit,
		afterEnter: requireAuth()
	},
	{
		path: '/signUp',
		name: 'AppSignUp',
		component: SignUp
	}
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
	linkActiveClass: "active",
	linkExactActiveClass: "exact-active"
})

export default router