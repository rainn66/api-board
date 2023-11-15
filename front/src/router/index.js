import { createRouter, createWebHistory } from 'vue-router'
import AppHome from '@/views/AppHome.vue'
import BoardList from "@/views/BoardList.vue";
import BoardForm from "@/views/BoardForm.vue";

const routes = [
	{
		path: '/',
		name: 'AppHome',
		component: AppHome
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
		component: BoardForm
	}
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
})

export default router