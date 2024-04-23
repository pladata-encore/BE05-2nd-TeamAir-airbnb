import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'

const vue = createApp(App);
vue.use(store);
vue.use(router);
vue.mount('#app');