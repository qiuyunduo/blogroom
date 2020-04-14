import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'
import { isLogin, getToken, refreshToken, getUserInfo } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  // baseURL: process.env.BASE_API, // api 的 base_url
  baseURL: '/api', // api 的 base_url
  timeout: 100000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Do something before request is sent
    if (isLogin()) {
      // 让每个请求携带token
      config.headers['User_Access_Token'] = getToken()
      config.headers['User_Id'] = getUserInfo().id
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    if(response.headers.refresh_token !== undefined) {
      refreshToken(response.headers.refresh_token)
    }
    const res = response.data

    if (res.status.code !== 0){

      if (res.status.code === 211) {
        MessageBox.alert('您的账号已在其他地方登录，请重新登录', '错误', {
          confirmButtonText: '确定',
          type: 'error'
        }).then(() => {
          store.dispatch('LogOut')
          return Promise.reject(res)
        })
      }　else if (res.status.code === 212) {
        MessageBox.alert('系统未登录，请重新登录', '错误', {
          confirmButtonText: '确定',
          type: 'error'
        }).then(() => {
          store.dispatch('LogOut')
          return Promise.reject(res)
        })
      } else if (res.status.code === 213) {
        Message({
          message: '您还未登录，请登录后重新执行此操作',
          type: 'error',
          duration: 2 * 1000
        })
        return Promise.reject(res)
      } else {
        return Promise.reject(res)
      }
     
    } else {
      return response
    }
  }, error => {
    console.log('err' + error)// for debu
    Message({
      message: error,
      type: 'error',
      duration: 1 * 1000
    })
  })

export default service
