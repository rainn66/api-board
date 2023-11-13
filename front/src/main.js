import './assets/common.css'

import { createApp } from 'vue'
import 'mutationobserver-shim'
import App from './App.vue'
import router from './router'
import axios from 'axios'

const app = createApp(App)
app.config.globalProperties.$axios = axios;
//api server
app.config.globalProperties.$serverUrl = '//localhost:80'
app.use(router).mount('#app')