import axios from 'axios';
import store from '@/vuex/store'

axios.interceptors.request.use(function (config) {
	store.commit('LOADING_STATUS', true);
	config.headers.Authorization = localStorage.getItem('userToken');
	return config;
});

axios.interceptors.response.use(function (config){
		store.commit('LOADING_STATUS', false);
		return config
});

export default axios;