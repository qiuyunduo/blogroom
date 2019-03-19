import { isLogin, getToken, setToken, removeToken } from '@/utils/auth'
import { getUserInfo, setUserInfo,removeUserInfo } from '../../utils/auth';

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
    LogOut({ commit }) {
          commit('SET_TOKEN', '')
          removeToken()
          removeUserInfo()
          commit('SET_ISLOGIN',false)
    },
  }
}

export default user
