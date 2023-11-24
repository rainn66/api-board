import axios from 'axios'

const getUserInfo = (userId, userPassword) => {
	const reqData = {
		'userId': userId,
		'userPassword': userPassword
	}

	let serverUrl = '//localhost:80'

	return axios.post(serverUrl + '/user/login', reqData, {
		headers: {
			'Content-type': 'application/json'
		}
	})

	// return {
	// 	'data': {
	// 		'userId': reqData.userId,
	// 		'userToken': 'user_test_token',
	// 		'userRole': 'ADM'
	// 	}
	// }
}

export default {
	async doLogin(userId, userPassword) {
		try {
			const getUserInfoPromise = getUserInfo(userId, userPassword)
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