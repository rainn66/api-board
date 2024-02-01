import axios from 'axios'

const getUserInfo = (userId, password) => {
	const reqData = {
		'userId': userId,
		'password': password
	}
	const serverUrl = '//localhost:8080';
	return axios.post( serverUrl + '/user/login', JSON.stringify(reqData), {
		headers: {
			'Content-type': 'application/json',
			'Authorization': ''
		}
	});
}

export default {
	async doLogin(userId, password) {
		try {
			const getUserInfoPromise = getUserInfo(userId, password)
			const userInfoResponse = await getUserInfoPromise;

			console.log(userInfoResponse.headers.getAuthorization());
			console.log(userInfoResponse.request.Authorization);
			console.log(userInfoResponse.request.headers);
			console.log(userInfoResponse);

			if (userInfoResponse.data.length === 0) {
				console.log('notFound');
				return 'notFound'
			} else {
				localStorage.setItem('userToken', userInfoResponse.data.userToken);
				// localStorage.setItem('userRole', userInfoResponse.data.userRole);
				return userInfoResponse
			}
		} catch (err) {
			console.error(err);
		}
	}
}

