<template>
    <!--注册-->
    <div v-if="registerFormShow" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;">
            <a @click="closeRegisterForm()" rel="nofollow" title="关闭" class="pop-close">×</a>
            <div class="pop-title">注册</div>
            <form id="registerForm" novalidate="novalidate">
                <div class="account-form">
                    <div>
                        <i class="fa fa-home" aria-hidden="true"></i>
                        <input type="text" name="account" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" class="account-input" id="account" placeholder="账号名称" v-model="registerInfo.account">
                    </div>
                    <div class="account-line"></div>
                    <div>
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <input type="text" name="nickName" class="account-input" id="account" placeholder="昵称" v-model="registerInfo.nickName">
                    </div>
                    <div class="account-line"></div>
                    <div>
                        <i class="fa fa-envelope" aria-hidden="true"></i>
                        <input type="text" name="userEmail" class="account-input" placeholder="邮箱" v-model="registerInfo.email">
                    </div>
                    <div class="account-line"></div>
                    <div>
                        <i class="fa fa-key fa-fw" aria-hidden="true"></i>
                        <input type="password" name="userPwd" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" id="rpassword" class="account-input" placeholder="密码" v-model="registerInfo.password">
                    </div>
                    <div class="account-line"></div>
                    <div>
                        <i class="fa fa-lock" aria-hidden="true"></i>
                        <input type="password" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" name="repassword" class="account-input" placeholder="确认密码" v-model="rePassword">
                    </div>
                </div>
                <div class="account-other">
                    <label><input type="checkbox" name="angree" id="angree" value="1" checked="true"> 我已认真阅读并同意博客屋的<a href="http://yuedu.fm/agreement/" class="red-link" target="_blank">《使用协议》</a></label>
                    <div id="error_tip_angree" class="error-tip"></div>
                </div>
                
                <button v-if="loading" type="button" class="account-btn" @click="doRegister()">
                    <i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
                </button>
                <button v-else type="button" class="account-btn" @click="doRegister()">注册</button>
            </form>
        </div>
    </div>
</template>

<script>
import { register } from '@/api/login'
import { addBlog } from '@/api/blog'

export default {
    name: 'RegisterForm',
    data() {
        return {
            loading: false,
            registerFormShow: false,
            registerInfo: {
                account: undefined,
                nickName: undefined,
                password: undefined,
                email: undefined
            },
            rePassword: undefined,
        }
    },
    methods: {
        openRegisterForm() {
            this.loading = false
            this.registerFormShow = true
        },
        closeRegisterForm() {
            this.registerFormShow = false
        },
        checkFiled() {
            if(this.registerInfo.password !== this.rePassword) {
                this.$notify.error({
                    title: '参数错误',
                    message: '确认密码出错'
                })
                this.loading = false
                return false
            }
            return true
        },
        doRegister() {
            this.loading = true
            if(this.checkFiled()) {
                register(this.registerInfo).then(res => {
                    let data = res.data.data
                    let token = data.token
                    let userInfo = data.user
                    //为该用户生成一个博客账号
                    addBlog(userInfo.id).then(res => {
                        this.$store.dispatch('setToken', token)
                        this.$store.dispatch('setUserInfo', userInfo)
                        this.$store.commit('SET_ISLOGIN', true)
                        this.closeRegisterForm()
                        this.$notify.success({
                            title: '成功',
                            message: '注册成功'
                        })
                    }).catch(() => {
                        this.loading = false
                    })
                }).catch(() => {
                    this.loading = false
                    this.$notify.error({
                        title: '异常',
                        message: '注册失败'
                    })
                })
            }
        },
    },
}
</script>

