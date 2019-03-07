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
        path: 'blog/:id',
        component: () => import('@/views/blog/index'),
        name: 'blog',
        meta: { title: '个人博客空间', noCache: true },
      }
    ]
  },
]

export default new Router({
  mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
