import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'
import { getToken, refreshToken, getUserInfo } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 10000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Do something before request is sent
    if (store.state.user.isLogin) {
      // 让每个请求携带token
      // alert(getToken())
      // alert(getUserInfo())
      config.headers['User_Access_Token'] = getToken()
      config.headers['User_Id'] = getUserInfo().id
      // alert(config.method)
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
      alert(response.headers.refresh_token)
      refreshToken(response.headers.refresh_token)
    }
    const res = response.data
    console.log(res)

    if (res.status.code !== 0){

      if (res.status.code === 212) {
        MessageBox.alert('系统未登录，请重新登录', '错误', {
          confirmButtonText: '确定',
          type: 'error'
        }).then(() => {
          store.dispatch('LogOut')
        })
      } else if (res.status.code === 213) {
        Message({
          message: '您还未登录，请登录后重新执行此操作',
          type: 'error',
          duration: 2 * 1000
        })
      }else {
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
