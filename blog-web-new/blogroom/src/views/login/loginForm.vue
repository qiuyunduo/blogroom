<template>
    <div v-if="loginFormShow" class="pop" id="login">
        <div class="pop-wp" style="left: 50%;">
            <a @click="closeLoginForm()" rel="nofollow" title="关闭" class="pop-close">×</a>
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
</template>

<script>
import { login } from "@/api/login"
export default {
    name: 'LoginForm',
    data() {
        return {
            loginFormShow: false,
            loginInfo: {
                userName: undefined,
                password: undefined
            }
        }
    },
    methods: {
        openLoginForm() {
            this.resetLoginInfo()
            this.loginFormShow = true
        },
        closeLoginForm() {
            this.loginFormShow = false
        },
        doLogin() {
            login(this.loginInfo).then(response => {
                if(response.data.data === null) {
                    this.$notify.error({
                    title: '失败',
                    message: response.data.status.msg
                    })
                } else {
                    let returnUser = Object.assign({},response.data.data)
                    let token = returnUser.token
                    this.$store.dispatch('setToken', token)
                    this.$store.dispatch('setUserInfo', returnUser)
                    this.$store.commit('SET_ISLOGIN', true)
                    this.closeLoginForm()
                    this.$notify.success({
                    title: '成功',
                    message: '登录成功'
                    })
                }
            }).catch(response => {
                this.$notify.error({
                    title: '请求异常',
                    message: response.data
                })
            })
            
        },
        resetLoginInfo() {
            this.loginInfo.userName = undefined
            this.loginInfo.password = undefined
        }
    }
}
</script>
