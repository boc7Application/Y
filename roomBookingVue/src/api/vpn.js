import axios from '../utils/axiosService'

// 获取指定日期的vpn列表
export const getVpnList = (reqParam) => {
  return axios.post(`/vpn/getVpnList`, JSON.stringify(reqParam))
}

// 提交vpn申请
export const submitVpn = (reqParam) => {  
  return axios.post(`/vpn/submitVpn`, JSON.stringify(reqParam))
}

