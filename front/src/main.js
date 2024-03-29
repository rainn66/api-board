import './assets/common.css'
import 'mutationobserver-shim'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from './util/axios'
import store from './vuex/store'

import commonPlugins from './util/commonPlugins'

const app = createApp(App)
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$serverUrl = '//localhost:8080'
app.config.globalProperties.$store = store;
app.use(router).use(store).use(commonPlugins).mount('#app')