<template>
  <header id="header" class="header">
    <div class="container-inner">
      <div class="yusi-logo">
        <router-link to="/index">
        <a href="">
          <h1>
            <span class="yusi-mono">博客屋</span>
            <span class="yusi-bloger">欢迎您</span>
          </h1>
        </a>
        </router-link>

        <div v-if="isLogin" style="float: right;padding-top: 40px;margin-right:80px;" id="log_user">
            <el-dropdown class="avatar-container right-menu-item" trigger="click">
            <img id="log_img" src="@/images/default.jpg" style="width: 30px;height: 30px;border-radius:50%">
            <i class="el-icon-caret-bottom"/>
              <el-dropdown-menu slot="dropdown">
                <router-link :to="blogBasePath+returnUser.id">
                  <el-dropdown-item>
                  <span style="display:block;">我的博客</span>
                  </el-dropdown-item>
                </router-link>
                <router-link :to="userBasePath+returnUser.id">
                <el-dropdown-item >
                    <span style="display:block;">个人中心</span>
                </el-dropdown-item>
                </router-link>
                <el-dropdown-item divided>
                    消息
                </el-dropdown-item>
                <el-dropdown-item >
                    反馈
                </el-dropdown-item>
                <el-dropdown-item divided>
                    帮助
                </el-dropdown-item>
                <el-dropdown-item >
                  <span style="display:block;" @click="logout">退出</span>
                </el-dropdown-item>
              </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div v-else style="float: right;padding-top: 40px;font-size: 20px" id="log_reg">
          <a href="javascript:;" data-pop="login"><span style="color: white" @click="showLoginForm()">登录</span></a>
          &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
          <a href="javascript:;" data-pop="register"><span style="color: white" @click="showRegisterForm()">注册</span></a>
        </div>
      </div>

      <login-form ref="loginForm"/>
      <register-form ref="registerForm"/>
    </div>
</header>
</template>

<script>
import LoginForm from '@/views/login/loginForm'
import RegisterForm from '@/views/login/registerForm'

export default {
  name: 'AppHead',
  components: {
    LoginForm,
    RegisterForm
  },
  computed: {
    loginStatus() {
      return this.$store.state.user.isLogin
    }
  },
  data() {
    return {
      isLogin: false,
      loginFormShow: false,
      registerFormShow: false,
      blogBasePath: '/blog/room/',
      userBasePath: '/user/',
      returnUser: {
          id: undefined,
          name: undefined,
          headImage: undefined,
          token: undefined
      },
    }
  },
  created() {
    this.checkLogin()
  },
  watch: {
    loginStatus(ov, nv){
      this.checkLogin()
    }
  },
  methods: {
    checkLogin() {
      this.isLogin = this.$store.state.user.isLogin
      this.returnUser = JSON.parse(this.$store.state.user.userInfo)
    },
    showLoginForm() {
      this.$refs.loginForm.openLoginForm()
    },
    showRegisterForm() {
      this.$refs.registerForm.openRegisterForm()
    },

    myBlog() {
      this.$router.push('/blog'+this.returnUser.id)
    },

    personal() {
      this.$router.push('/user'+this.returnUser.id)
    },

    logout() {
      this.$store.dispatch('LogOut')
    }

  }
}
</script>
