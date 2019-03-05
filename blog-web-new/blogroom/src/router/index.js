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
  // {
  //   path: '/test',
  //   name: 'layout',
  //   component: () => import('@/views/layout/Layout'),
  //   redirect: 'noredirect',
  //   children: [
  //     {
  //       path: 'main',
  //       component: () => import('@/views/website/index'),
  //       name: 'main',
  //       meta: { title: 'main', icon: 'main', noCache: true }
  //     }
  //   ]
  // },
  {
    path: '/',
    name: 'layout',
    component: () => import('@/views/layout/Layout'),
    children: [
      {
        path: '',
        component: () => import('@/views/website/index'),
        name: 'main',
        meta: { title: '首页', noCache: true },
        children: [
          {
            path: '',
            redirect: 'index'
          },
          {
            path: 'index',
            component: () => import('@/views/website/list'),
            name: 'index',
            meta: { title: '首页', noCache: true }
          },
          {
            path: 'articles',
            component: () => import('@/views/website/list'),
            name: 'articles',
            meta: { title: '文章list', noCache: true }
          },
          {
            path: 'article/:id',
            component: () => import('@/views/article/article'),
            name: 'article',
            meta: { title: '文章详情', noCache: true }
          },
        ]
      },
      {
        path: 'blog/:id',
        component: () => import('@/views/blog/index'),
        name: 'blog',
        meta: { title: '个人博客空间', noCache: true },
      }
    ]
  },
  // {
  //   path: '/',
  //   redirect: 'index',
  //   name: 'index',
  // },
  // {
  //   path: '/article',
  //   redirect: 'index/article',
  //   name: 'article',
  // },
  // {
  //   path: '/articles',
  //   redirect: 'index',
  //   name: 'articles',
  // },
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
