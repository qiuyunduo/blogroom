import { isLogin, getToken, setToken, removeToken } from '@/utils/auth'
import { getUserInfo, setUserInfo,removeUserInfo } from '../../utils/auth';
import { logout } from '@/api/login'

const user = {
  state: {
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
    setToken({ commit }, token) {
      commit('SET_TOKEN', token)
      setToken(token)
    },
    setUserInfo({ commit },userInfo) {
      commit('SET_USERINFO',userInfo)
      setUserInfo(userInfo)
    },

    // 登出
    LogOut({ commit, state }) {
      let userId = state.userInfo.id
      logout(userId).then(() => {
        commit('SET_TOKEN', '')
        removeToken()
        removeUserInfo()
        commit('SET_ISLOGIN',false)
        window.location.href = "/"
      }).catch(() => {
        alert('登出出现异常')
      })
          
    },
  }
}

export default user
