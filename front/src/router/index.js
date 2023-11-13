import { createRouter, createWebHistory } from 'vue-router'
import AppHome from '@/views/AppHome.vue'
import BoardList from "@/views/BoardList.vue";

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
		path: '/bbs/list',
		name: 'BoardList',
		component: BoardList
	}
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
})

export default router