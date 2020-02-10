/* eslint-disable comma-spacing */
/* eslint-disable space-before-blocks */
/* eslint-disable space-before-function-paren */
import Vue from 'vue'
import Vuex from 'vuex'
import { login,logout } from '../api/user'
Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    token: localStorage.getItem('token'),
    tabbarStatus: true
  },
  mutations: {
    setToken (state, token) {
      state.token = token
    },
    setLogout (state) {
      state.token = ''
    },
    setTabbarStatus(state,status){
      state.tabbarStatus = status
    }

  },
  actions: {
    dologin ({commit}, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password).then((res) => {
          const data = res.data
          if (data.success) {
            commit('setToken', data.data.token)
            localStorage.setItem('token', data.data.token)
          }
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      })
    },
    dologout ({commit}) {
      return new Promise((resolve, reject) => {
        logout().then((res) => {
          if (res.data.success) {
            commit('setLogout')
            localStorage.setItem('token', '')
          }
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
})

export default store
