<template>
  <header id="header" class="header">
    <div class="container-inner">
      <div class="yusi-logo">
        <a href="/BlogRoom/apps/index.html">
          <h1>
            <span class="yusi-mono">博客屋</span>
            <span class="yusi-bloger">欢迎您</span>
          </h1>
        </a>

        <div v-if="isLogin" style="float: right;padding-top: 40px;margin-right:80px;" id="log_user"  @click="test()">
            <el-dropdown class="avatar-container right-menu-item" trigger="click">
            <img id="log_img" src="@/images/default.jpg" style="width: 30px;height: 30px;border-radius:50%">
            <i class="el-icon-caret-bottom"/>
              <el-dropdown-menu slot="dropdown">
                <router-link :to="blogBasePath+returnUser.id">
                  <el-dropdown-item>
                  <span style="display:block;" @click="myBlog()">我的博客</span>
                  </el-dropdown-item>
                </router-link>
                <el-dropdown-item divided>
                    <span style="display:block;" @click="logout">个人中心</span>
                </el-dropdown-item>
                <el-dropdown-item>
                    消息
                </el-dropdown-item>
                <el-dropdown-item divided>
                    反馈
                </el-dropdown-item>
                <el-dropdown-item divided>
                    帮助
                </el-dropdown-item>
                <el-dropdown-item divided>
                  <span style="display:block;" @click="logout">退出</span>
                </el-dropdown-item>
              </el-dropdown-menu>
          </el-dropdown>
        </div>

        <div v-else style="float: right;padding-top: 40px;font-size: 20px" id="log_reg">
          <a href="javascript:;" data-pop="login"><span style="color: white" @click="showLogin()">登录</span></a>
          &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
          <a href="javascript:;" data-pop="register"><span style="color: white" @click="showRegister()">注册</span></a>
        </div>
      </div>

       <!--登录-->
        <div class="pop" id="login" :style="{'display': loginStatus}">
        <div class="pop-wp" style="left: 50%;">
            <a @click="closeLogin()" rel="nofollow" title="关闭" class="pop-close">×</a>
            <div class="pop-title">登录</div>

            <form id="loginForm">
            <div class="account-form">
                <div>
                <i class="fa fa-user" aria-hidden="true"></i>
                <input type="text" name="lusername" class="account-input" placeholder="用户名" v-model="loginInfo.userName">
                <div id="error_tip_lusername" class="error-tip"></div>
                </div>

                <div class="account-line"></div>

                <div>
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" name="lpassword" class="account-input" placeholder="密码" v-model="loginInfo.password">
                <div id="error_tip_lpassword" class="error-tip"></div>
                </div>
            </div>
            <div class="account-other">
                <span class="fr"><a href="http://yuedu.fm/retrieve/" class="red-link" target="_blank">忘记密码</a></span>
                <label><input type="checkbox" value="1" name="nextauto"> 下次自动登录</label>
            </div>
            <button type="button" class="account-btn" @click="doLogin()">登 录</button>
            </form>
        </div>
        </div>
        <!--注册-->
        <div class="pop" id="register" :style="{'display': registerStatus}">
        <div class="pop-wp" style="left: 50%;">
            <a @click="closeRegister()" rel="nofollow" title="关闭" class="pop-close">×</a>
            <div class="pop-title">注册</div>
            <form id="registerForm" novalidate="novalidate">
            <div class="account-form">
            <div>
                <i class="fa fa-user" aria-hidden="true"></i>
                <input type="text" name="userName" class="account-input" placeholder="昵称" v-model="registerInfo.name">
                <div id="error_tip_userName" class="error-tip"></div>
            </div>
                <div class="account-line"></div>
                <div>
                <i class="fa fa-envelope" aria-hidden="true"></i>
                <input type="text" name="userEmail" class="account-input" placeholder="邮箱" v-model="registerInfo.email">
                <div id="error_tip_userEmail" class="error-tip"></div>
                </div>
                <div class="account-line"></div>
                <div>
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" name="userPwd" id="rpassword" class="account-input" placeholder="密码" v-model="registerInfo.password">
                <div id="error_tip_userPwd" class="error-tip"></div>
                </div>
                <div class="account-line"></div>
                <div>
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" name="repassword" class="account-input" placeholder="确认密码" v-model="rePassword">
                <div id="error_tip_repassword" class="error-tip"></div>
                </div>
            </div>
            <div class="account-other">
                <label><input type="checkbox" name="angree" id="angree" value="1" checked="true"> 我已认真阅读并同意博客屋的<a href="http://yuedu.fm/agreement/" class="red-link" target="_blank">《使用协议》</a></label>
                <div id="error_tip_angree" class="error-tip"></div>
            </div>
            <button type="button" class="account-btn" @click="doRegister()">注册</button>
            </form>
        </div>
        </div>
    </div>
</header>
</template>
<script>
import { login, register } from "@/api/user"
import DropList from 'vue-droplist'

export default {
  name: 'AppHead',
  components: {
    DropList
  },
  data() {
    return {
      isLogin: false,
      loginStatus: 'none',
      registerStatus: 'none',
      loginInfo: {
        userName: undefined,
        password: undefined
      },
      registerInfo: {
        name: undefined,
        password: undefined,
        email: undefined
      },
      returnUser: {
        id: undefined,
        headImage: undefined
      },
      rePassword: undefined,
      blogBasePath: '/blog/',
      userBasePath: '/user/'
    }
  },
  created() {
    this.checkLogin()
  },
  methods: {
    checkLogin() {
    },
    showLogin() {
      this.loginStatus = 'block'
    },
    closeLogin() {
      this.loginStatus = 'none'
    },
    showRegister() {
      this.registerStatus = 'block'
    },
    closeRegister() {
      this.registerStatus = 'none'
    },

    doLogin() {
      login(this.loginInfo).then(response => {
          if(response.data.data === null) {
            this.$notify.error({
              title: '失败',
              message: response.data.status.msg
            })
          } else {
            this.$store.state.user.isLogin = true
            this.isLogin = true
            this.closeLogin()
            this.$notify.success({
              title: '成功',
              message: '登录成功'
            })
          }
        }).catch(response => {
          this.$notify.error({
            title: '请求异常',
            message: response.data.data
          })
        })
    },

    doRegister() {
      if(this.registerInfo.password === this.rePassword) {
        register(this.registerInfo).then(response => {
          if(response.data.data === null) {
            this.$notify.error({
              title: '失败',
              message: response.data.status.msg
            })
          } else {
            this.isLogin = true
            this.returnUser.id = response.data.data.id
            this.returnUser.headImage = response.data.data.headImage
            this.closeRegister()
            this.$notify.success({
              title: '成功',
              message: '注册成功'
            })
          }
        }).catch(() => {
          this.$notify.error({
            title: '异常',
            message: '注册失败'
          })
        })
      }else {
        this.$notify.error({
            title: '参数错误',
            message: '确认密码出错'
          })
      }
    },

    myBlog() {
      this.$router.push('/blog'+this.returnUser.id)
    },

    personal() {
      this.$router.push('/user'+this.returnUser.id)
    },

    logout() {
      alert("退出登录")
    }

  }
}
</script>
