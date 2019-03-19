import { isLogin, getAdminInfo, setAdminInfo, removeAdminInfo } from '@/utils/adminAuth'

const website = {
  state: {
    isLogin: isLogin(),
    adminInfo: getAdminInfo()
  },
  mutations: {
    SET_ISLOGIN: (state, data) => {
      state.isLogin = data
    },
    SET_ADMININFO: (state, adminInfo) => {
      state.adminInfo = adminInfo
    },
  },
  actions: {
    setAdminInfo({ commit },adminInfo) {
      return new Promise((resolve,reject) => {
        commit('SET_ISLOGIN',true)
        commit('SET_ADMININFO',adminInfo)
        setAdminInfo(adminInfo)
        resolve()
      })
    },
    adminLogout({ commit }) {
      return new Promise((resolve, reject) => {
          removeAdminInfo()
          commit('SET_ISLOGIN',false)
          resolve()
      })
    },
  }
}

export default website
