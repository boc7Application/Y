import axios from 'axios'
import store from '../store/store'

// const baseURL = process.env.BASE_URL
const baseURL = '/api/'

// 创建axios实例
const service = axios.create({
  baseURL: baseURL,
  headers: {'Content-Type': 'application/json;charset=UTF-8'},
  timeout: 30000
})

// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'//'application/json;charset=UTF-8'

// request拦截器
service.interceptors.request.use(config => {
  if (store.state.token) {
    // 让每个请求头部都携带token信息
    config.headers.common['Authorization'] = store.state.token
  }
  return config
}, error => {
  Promise.resolve(error)
})

// respone拦截器
service.interceptors.response.use(response => {
  return response
}, error => {
  if (error.response) {
    const status = error.response.status
    if (status === 403) { // 后台返回状态吗403 token验证失败
      store.commit('setLogout')
      localStorage.setItem('token', '')
    }
  }
  return Promise.reject(error)
})

export default service
