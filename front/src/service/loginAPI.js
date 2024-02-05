import common from "@/util/common";
import axios from 'axios'

const getUserInfo = (userId, password) => {
	const reqData = {
		'userId': userId,
		'password': password
	}
	const serverUrl = '//localhost:8080';
	return axios.post( serverUrl + '/user/login', JSON.stringify(reqData), {
		headers: {
			'Content-type': 'application/json'
		}
	});
}

export default {
	async doLogin(userId, password) {
		try {
			const getUserInfoPromise = getUserInfo(userId, password)
			const [userInfoResponse] = await Promise.all([getUserInfoPromise])

			// 인증안되면 Exception 처리하기때문에 사용안함
			// if (userInfoResponse.data.length === 0 ) {
			// 	return 'notFound'
			// } else {
			// 	localStorage.setItem('userToken', userInfoResponse.headers.getAuthorization());
			// 	return userInfoResponse
			// }

			localStorage.setItem('userToken', userInfoResponse.headers.getAuthorization());
			return userInfoResponse
		} catch (err) {
			common.commonAxiosError(err);
		}
	}
}

