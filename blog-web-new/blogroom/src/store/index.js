import Vue from 'vue'
import Vuex from 'vuex'
import website from './modules/website'
import user from './modules/user'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    website,
    user
  }
})

export default store
