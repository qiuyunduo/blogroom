import { isLogin, getAdminInfo, setAdminInfo, removeAdminInfo } from '@/utils/adminAuth'

const website = {
  state: {
    classMap: null,
    isLogin: isLogin(),
    adminInfo: getAdminInfo()
    // queryList: {
    //   classId: undefined,
    //   title: undefined,
    //   userId: undefined,
    //   orderByUpdateTime: false,
    //   status: 2,
    //   page: 1,
    //   limit: 10
    // }
  },
  mutations: {
    SET_ISLOGIN: (state, data) => {
      state.isLogin = data
    },
    SET_CLASSMAP: (state, data) => {
      state.classMap = data
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
    adminLogout({ commit, state }) {
      return new Promise((resolve, reject) => {
          removeAdminInfo()
          commit('SET_ISLOGIN',false)
          resolve()
      })
    },
  }
}

export default website
