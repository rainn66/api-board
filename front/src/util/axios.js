import axios from 'axios';
import common from "./common"


axios.interceptors.request.use(function (config) {
	config.headers.Authorization = localStorage.getItem('userToken');
	return config;
});

axios.interceptors.response.use(
	function (config){
		return config;
		},
	function(error) {
		return common.commonAxiosError(error);
	}
);

export default axios;