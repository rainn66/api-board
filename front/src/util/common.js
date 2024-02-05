import router from '../router'

export default {
    commonAxiosError(error) {
        // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거
        if (error.response && error.response.status) {
            switch (error.response.status) {
                // status code가 401인 경우 `logout`을 커밋하고 `/login` 페이지로 리다이렉트
                case 400:
                    alert(error.response.data.message);
                    break;
                case 401:
                    alert(error.response.data.message);
                    if (error.response.data.title === "EXPIRED" || error.response.data.title === "NOT_LOGIN") {
                        localStorage.removeItem("userToken");
                        router.push({name: 'AppLogin'});
                    } else if (error.response.data.title === "EXPIRED_ONLY") {
                        localStorage.removeItem("userToken");
                        location.reload();
                    }
                    break;
                case 404:
                    alert(error.response.data.message);
                    break;
                // eslint-disable-next-line no-fallthrough
                default:
                    alert(error.response.data.message);
                    break;
            }
        }
    }
};
