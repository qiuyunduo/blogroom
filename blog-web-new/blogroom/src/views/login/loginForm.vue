<template>
    <div v-if="loginFormShow" class="pop" id="login">
        <div class="pop-wp" style="left: 50%;height:250px">
            <a @click="closeLoginForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">登录</div>

            <el-form :model="loginInfo" ref="numberValidateForm" label-width="50px" class="demo-ruleForm">
                <el-form-item label="账号:">
                    <el-input v-model="loginInfo.account" auto-complete="off" class="account-input" v-focus></el-input>
                </el-form-item>
                <el-form-item label="密码:">
                    <el-input v-model="loginInfo.password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="margin-top:-20px">
                        <span class="fr"><a href="http://yuedu.fm/retrieve/" class="red-link" target="_blank">忘记密码</a></span>
                    <button type="button" class="account-btn" @click="doLogin()">登 录</button>
                </el-form-item>
            </el-form>

            <!-- <form id="loginForm">
            <div class="account-form">
                <div>
                <i class="fa fa-user" aria-hidden="true"></i>
                <input type="text" name="lusername" ref="account" class="account-input" placeholder="账号" v-model="loginInfo.account">
                </div>

                <div class="account-line"></div>

                <div>
                <i class="fa fa-lock" aria-hidden="true"></i>
                <input type="password" name="lpassword" class="account-input" placeholder="密码" v-model="loginInfo.password">
                </div>
            </div>
            <div class="account-other">
                <span class="fr"><a href="http://yuedu.fm/retrieve/" class="red-link" target="_blank">忘记密码</a></span>
            </div>
            <button type="button" class="account-btn" @click="doLogin()">登 录</button>
            </form> -->
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
                account: undefined,
                password: undefined
            }
        }
    },
    directives: {
        // 注册一个局部的自定义指令 v-focus
        focus: {
            // 指令的定义
            inserted: function (el) {
            // 聚焦元素
            el.querySelector('input').focus()
            }
        }
    },
    methods: {
        openLoginForm() {
            this.resetLoginInfo()
            this.loginFormShow = true
            this.$nextTick(function() {
                this.$refs.account.focus()
            })
        },

        closeLoginForm() {
            this.loginFormShow = false
        },
        doLogin() {
            login(this.loginInfo).then(response => {
                let data = Object.assign({},response.data.data)
                let token = data.token
                let userInfo = data.user
                this.$store.dispatch('setToken', token)
                this.$store.dispatch('setUserInfo', userInfo)
                this.$store.commit('SET_ISLOGIN', true)
                this.closeLoginForm()
                this.$notify.success({
                    title: '成功',
                    message: '登录成功',
                    duration: 1 * 1000
                })
                window.setTimeout("location.reload()",700);
            }).catch(response => {
                this.$notify.error({
                    title: '登录失败',
                    message: response.status.msg,
                    duration: 1 * 1000
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
