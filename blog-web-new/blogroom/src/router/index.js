import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

// import Layout from '@/view/layout/Layout'
// import HelloWorld from '@/components/HelloWorld'

export const constantRouterMap = [
  // {
  //   path: '/redirect',
  //   component: Layout,
  //   hidden: true,
  //   children: [
  //     {
  //       path: '/redirect/:path*',
  //       component: () => import('@/views/redirect/index')
  //     }
  //   ]
  // },
  // {
  //   path: '/login',
  //   component: () => import('@/views/login/index'),
  //   hidden: true
  // },
  // {
  //   path: '/auth-redirect',
  //   component: () => import('@/views/login/authredirect'),
  //   hidden: true
  // },
  // {
  //   path: '/404',
  //   component: () => import('@/views/errorPage/404'),
  //   hidden: true
  // },
  {
    path: '/test',
    name: 'layout',
    component: () => import('@/views/layout/Layout'),
    redirect: 'noredirect',
    children: [
      {
        path: 'main',
        component: () => import('@/views/website/index'),
        name: 'main',
        meta: { title: 'main', icon: 'main', noCache: true }
      }
    ]
  },
  {
    path: '/test',
    name: 'test',
    component: () => import('@/components/index')
  },
  {
    path: '/',
    component: () => import('@/views/layout/Layout'),
    redirect: 'noredirect',
    name: 'layout',
    children: [
      {
        path: '',
        component: () => import('@/views/website/index'),
        name: 'index',
        meta: { title: '首页', noCache: true }
      },
    ]
  },
  // {
  //   path: '',
  //   component: Layout,
  //   redirect: 'dashboard',
  //   children: [
  //     {
  //       path: 'dashboard',
  //       component: () => import('@/views/dashboard/index'),
  //       name: 'Dashboard',
  //       meta: { title: 'dashboard', icon: 'dashboard', noCache: true }
  //     }
  //   ]
  // }
]

export default new Router({
  // mode: 'history', // require service support
  // scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
