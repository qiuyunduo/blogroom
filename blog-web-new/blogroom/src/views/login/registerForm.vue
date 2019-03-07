<template>
    <!--注册-->
    <div v-if="registerFormShow" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;">
            <a @click="closeRegisterForm()" rel="nofollow" title="关闭" class="pop-close">×</a>
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
</template>

<script>
import { register } from "@/api/user"

export default {
    name: 'RegisterForm',
    data() {
        return {
            registerFormShow: false,
            registerInfo: {
                name: undefined,
                password: undefined,
                email: undefined
            },
            rePassword: undefined,
        }
    },
    methods: {
        openRegisterForm() {
            this.registerFormShow = true
        },
        closeRegisterForm() {
            this.registerFormShow = false
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
                    this.closeRegisterForm()
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
    },
}
</script>

