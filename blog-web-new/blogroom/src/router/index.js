import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

// import Layout from '@/view/layout/Layout'
// import HelloWorld from '@/components/HelloWorld'

export const constantRouterMap = [
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
        path: 'blog/room/:id',
        component: () => import('@/views/blog/index'),
        name: 'blogroom',
        meta: { title: '个人博客空间', noCache: true },
      },

      {
        path: 'blog/user/:id',
        component: () => import('@/views/user/index'),
        name: 'blogroom',
        meta: { title: '个人中心', noCache: true },
        children: [
          {
            path: '',
            redirect: 'info'
          },
          {
            path: 'info',
            component: () => import('@/views/user/info'),
            name: 'userInfo',
            meta: { title: '个人资料', noCache: true }
          },
          {
            path: 'followers',
            component: () => import('@/views/user/followers'),
            name: 'followers',
            meta: { title: '我的关注', noCache: true }
          },
          {
            path: 'fans',
            component: () => import('@/views/user/fans'),
            name: 'fans',
            meta: { title: '我的粉丝', noCache: true }
          },
          {
            path: 'mArticles',
            component: () => import('@/views/user/mArticles'),
            name: 'mArticles',
            meta: { title: '博客管理', noCache: true }
          },
          {
            path: 'security',
            component: () => import('@/views/user/security'),
            name: 'security',
            meta: { title: '账号安全', noCache: true }
          },
        ]
      },
      {
        path: 'blog/article/create',
        component: () => import('@/views/article/create'),
        name: 'writing',
        meta: { title: '撰写博客', noCache: true },
      },
    ]
  },
]

export default new Router({
  mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
