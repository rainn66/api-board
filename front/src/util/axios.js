import axios from 'axios';

axios.interceptors.request.use(function (config) {
	const token = localStorage.getItem('userToken');
	config.headers.Authorization = "Bearer " + token;
	console.log(config);
	return config;
});

axios.interceptors.response.use(function (config) {
	return config
});

export default axios;