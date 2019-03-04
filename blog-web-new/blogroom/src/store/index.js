import Vue from 'vue'
import Vuex from 'vuex'
import website from './modules/website'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    website
  }
})

export default store
