import store from "@/vuex/store";
import router from '../router'

export default {
    commonAxiosError(error) {
        store.commit('LOADING_STATUS', false);
        // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거
        if (error.response && error.response.status) {
            switch (error.response.status) {
                // status code가 401인 경우 `logout`을 커밋하고 `/login` 페이지로 리다이렉트
                case 401:
                    alert(error.response.data.message);
                    break;
                case 403:
                    alert(error.response.data.message);
                    localStorage.removeItem("userToken");
                    router.push({name: 'AppLogin'});
                    break;
                // eslint-disable-next-line no-fallthrough
                default:
                    alert(error.response.data.message);
            }
        }
    }
};
