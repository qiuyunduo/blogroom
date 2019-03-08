import Cookies from 'js-cookie'

const website = {
  state: {
    queryList: {
      classId: undefined,
      title: undefined,
      userId: undefined,
      orderByUpdateTime: false,
      status: 2,
      page: 1,
      limit: 10
    }
  },
  mutations: {
    // TOGGLE_SIDEBAR: state => {
    //   if (state.sidebar.opened) {
    //     Cookies.set('sidebarStatus', 1)
    //   } else {
    //     Cookies.set('sidebarStatus', 0)
    //   }
    //   state.sidebar.opened = !state.sidebar.opened
    //   state.sidebar.withoutAnimation = false
    // }
  },
  actions: {
    // toggleSideBar({ commit }) {
    //   commit('TOGGLE_SIDEBAR')
    // }
  }
}

export default website
