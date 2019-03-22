// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Element from 'element-ui'

import './icons' // icon
import '../static/Ue-utf-jsp/ueditor.config.js'　　　　
import '../static/Ue-utf-jsp/ueditor.all.js'
import '../static/Ue-utf-jsp/lang/zh-cn/zh-cn.js'
import '../static/Ue-utf-jsp/ueditor.parse.js'

import '@/style/basestyle.css'
import '@/style/default.css'
import '@/style/login-register.css'
import "element-ui/lib/theme-chalk/index.css"

Vue.config.productionTip = false

Vue.use(Element)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
