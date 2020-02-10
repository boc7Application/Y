import axios from '../utils/axiosService'

export const login = (username, password) => {
  const data = {
    username,
    password
  }
  return axios.post('/user/login', JSON.stringify(data))
}

// export const getUserInfo = () => {
//   return axios.get('/user/getUserInfo')
// }

export const logout = () => {
  return axios.get(`/user/logout`)
}

export const validToken = () => {
  return axios.get('/user/validToken')
}
