const website = {
  state: {
    classMap: null
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
    SET_CLASSMAP: (state, data) => {
      state.classMap = data
    }
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
    
  }
}

export default website
