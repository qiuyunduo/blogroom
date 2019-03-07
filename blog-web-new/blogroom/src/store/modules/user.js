import { isLogin, getUserInfo, setUserInfo, getToken, setToken, removeToken } from '@/utils/auth'
// import { logout } from '@/api/user'

const user = {
  state: {
    // userId: undefined,
    isLogin: isLogin(),
    token: getToken(),
    userInfo: getUserInfo()
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_ISLOGIN: (state, status) => {
      state.isLogin = status
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
    }
  },
  actions: {
    Login({ commit }, token, userInfo) {
      return new Promise((resolve,reject) => {
        commit('SET_TOKEN', token)
        commit('SET_USERINFO',userInfo)
        setToken(token)
        setUserInfo(userInfo)
        resolve()
      })
    },
    // // 用户名登录
    // LoginByUsername({ commit }, userInfo) {
    //   const username = userInfo.username.trim()
    //   return new Promise((resolve, reject) => {
    //     loginByUsername(username, userInfo.password).then(response => {
    //       const token = response.data.data
    //       commit('SET_TOKEN', token)
    //       setToken(token)
    //       resolve()
    //     }).catch(error => {
    //       reject(error)
    //     })
    //   })
    // },

    // // 获取用户信息
    // GetUserInfo({ commit, state }) {
    //   return new Promise((resolve, reject) => {
    //     getUserInfo(state.token).then(response => {
    //       const data = response.data.data

    //       if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
    //         commit('SET_ROLES', data.roles)
    //       } else {
    //         reject('getInfo: roles must be a non-null array !')
    //       }

    //       commit('SET_NAME', data.name)
    //       commit('SET_AVATAR', data.avatar)
    //       commit('SET_INTRODUCTION', data.introduction)
    //       resolve(response)
    //     }).catch(error => {
    //       reject(error)
    //     })
    //   })
    // },

    // 登出
    LogOut({ commit, state }) {
      // return new Promise((resolve, reject) => {
        // logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          // commit('SET_ROLES', [])
          removeToken()
          commit('SET_ISLOGIN',false)
          // resolve()
        // }).catch(error => {
        //   reject(error)
        // })
      // })
    },

    // // 前端 登出
    // FedLogOut({ commit }) {
    //   return new Promise(resolve => {
    //     commit('SET_TOKEN', '')
    //     removeToken()
    //     resolve()
    //   })
    // },
  }
}

export default user
