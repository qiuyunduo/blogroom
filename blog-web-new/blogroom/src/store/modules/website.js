import { isAdminLogin, getAdminInfo, setAdminInfo, removeAdminInfo } from '@/utils/adminAuth'
import { loginOut } from '@/api/admin'

const website = {
  state: {
    isAdminLogin: isAdminLogin(),
    adminInfo: getAdminInfo()
  },
  mutations: {
    SET_IS_ADMIN_LOGIN: (state, data) => {
      state.isAdminLogin = data
    },
    SET_ADMININFO: (state, adminInfo) => {
      state.adminInfo = adminInfo
    },
  },
  actions: {
    setAdminInfo({ commit },adminInfo) {
      return new Promise((resolve,reject) => {
        commit('SET_ADMININFO',adminInfo)
        setAdminInfo(adminInfo)
        resolve()
      })
    },
    adminLogout({ commit, state }) {
      let adminId = state.adminInfo.id
      loginOut(adminId).then(res => {
        removeAdminInfo()
        commit('SET_IS_ADMIN_LOGIN',false)
        window.location.href = "/admin/login"
      }).catch(() => {
         alert("后台登出错误") 
      })
    }
  }
}

export default website
