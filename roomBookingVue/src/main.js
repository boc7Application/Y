// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/store'
// import axios from './utils/axiosService'
import {validToken} from './api/user'
import es6Promise from 'es6-promise'
import '@/styles/index.css'
import { LoadingPlugin } from 'vux'

Vue.use(LoadingPlugin)
Vue.config.productionTip = false
es6Promise.polyfill()
/* eslint-disable no-new */

let isTokenValid = async function (token) {
  let result = false
  await validToken().then(data => {
    result = data.data.success
  }).catch(err => {
    console.log(err)
  })
  return result
}

// vue全局拦截器
router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  if (to.meta.requireAuth) { // 如果需要权限
    if (store.state.token && isTokenValid()) { // 存在的话，直接跳转过去
      next()
    } else {
      // console.log('redirect to login page from %s and params %s', to.path, JSON.stringify(to.params))
      next({name: 'LoginPage', params: {redirectUrl: to.name, urlParams: to.params}})
    }
  } else {
    next()
  }
})

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
