import './assets/common.css'
import 'mutationobserver-shim'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from './util/axios'
import store from './vuex/store'

const app = createApp(App)
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$serverUrl = '//localhost:80'
app.config.globalProperties.$store = store;
app.use(router).use(store).mount('#app')