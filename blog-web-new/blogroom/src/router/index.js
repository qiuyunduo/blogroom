import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    component: () => import('@/views/layout/Layout'),
    children: [
      {
        path: '',
        component: () => import('@/views/website/index'),
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
  {
    path: '/about',
    component: () => import('@/views/website/about'),
  },
  {
    path: '/contact',
    component: () => import('@/views/website/contact'),
  },
  {
    path: '/admin',
    redirect: '/admin/login'
  },

  {
    path: '/admin/login',
    component: () => import('@/view_admin/login'),
    name: 'admin',
    meta: { title: '管理员登录', noCache: true },
  },

  {
    path: '/admin/manage',
    component: () => import('@/view_admin/layout/index'),
    name: 'adminManage',
    meta: { title: '管理员登录', noCache: true },
    children: [
      {
        path: 'index',
        component: () => import('@/view_admin/index'),
        name: 'adminIndex',
        meta: { title: '首页', noCache: true }
      },
      {
        path: 'user',
        component: () => import('@/view_admin/userManager/index'),
        name: 'manageUser',
        meta: { title: '用户管理', noCache: true }
      },
      {
        path: 'article/edit',
        component: () => import('@/view_admin/articleManager/edit'),
        name: 'edit',
        meta: { title: '文章管理', noCache: true }
      },
      {
        path: 'article/check',
        component: () => import('@/view_admin/articleManager/checkArticles'),
        name: 'checkArticle',
        meta: { title: '已审核文章管理', noCache: true }
      },
      {
        path: 'article/uncheck',
        component: () => import('@/view_admin/articleManager/uncheckArticles'),
        name: 'unCheckArticle',
        meta: { title: '待审核文章管理', noCache: true }
      },
    ]
  },
  {
    path: "*",
    component: () => import('@/components/errorPage/404.vue'),
  }
]

let router = new Router({
  mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

router.beforeEach((to, from, next) => {
  // alert(to.path)
  if(to.path.startsWith("/admin")) {
    if(to.path === '/admin/login') {
      next()
    } else {
      if(store.state.website.isLogin) {
        next()
      } else {
        next('/admin/login')
      }
    }
  } else {
    next()
  }
})

export default router
