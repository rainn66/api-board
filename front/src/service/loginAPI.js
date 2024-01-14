import axios from 'axios'

const getUserInfo = (userId, password) => {
	const reqData = {
		'userId': userId,
		'password': password
	}

	let serverUrl = '//localhost:8080'

	return axios.post(serverUrl + '/user/login', reqData, {
		headers: {
			'Content-type': 'application/json'
		}
	})
}

export default {
	async doLogin(userId, password) {
		try {
			const getUserInfoPromise = getUserInfo(userId, password)
			const [userInfoResponse] = await Promise.all([getUserInfoPromise])
			if (userInfoResponse.data.length === 0) {
				return 'notFound'
			} else {
				localStorage.setItem('userToken', userInfoResponse.data.userToken);
				localStorage.setItem('userRole', userInfoResponse.data.userRole);
				return userInfoResponse
			}
		} catch (err) {
			console.error(err)
		}
	}
}