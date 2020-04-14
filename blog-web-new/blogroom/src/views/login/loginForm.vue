<template>
    <div v-if="loginFormShow == 1" class="pop" id="login">
        <div class="pop-wp" style="left: 50%;height:250px">
            <a @click="closeLoginForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">登录</div>

            <el-form :model="loginInfo" ref="loginForm"  label-width="60px" class="demo-ruleForm">
                <el-form-item label="账号:" prop="account">
                    <el-input v-model="loginInfo.account" auto-complete="off" class="account-input" v-focus></el-input>
                </el-form-item>
                <el-form-item label="密码:" prop="password">
                    <el-input type="password" v-model="loginInfo.password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="margin-top:-20px">
                        <span class="fr"><a href="javascript:;" @click="goResetPwd()" class="red-link">忘记密码</a></span>
                    <button type="button" class="account-btn" @click="doLogin()">登 录</button>
                </el-form-item>
            </el-form>
        </div>
    </div>

    <div v-else-if="loginFormShow === 2" class="pop">
        <div class="pop-wp" style="left: 50%;width:300px">
            <a @click="closeLoginForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">重置密码(验证邮箱)</div>

            <el-form ref="emailForm" label-width="70px" class="demo-ruleForm">
                <el-form-item label="邮箱:" prop="email">
                    <el-input v-model="email" auto-complete="off"></el-input>
                    <!-- <el-input v-mode="phone" auto-complete="on"></el-input>-->
                </el-form-item>
                <el-form-item label="验证码:" prop="validateCode">
                    <el-input v-model="validateCode" style="width:120px" auto-complete="off"></el-input>
                    <el-button v-if="isSendCode" style="margin-top:-10px;width:100px" disabled>{{ codeTime}}</el-button>
                    <el-button v-else style="margin-top:-10px;width:100px" @click="sendCode()">获取验证码</el-button>
                </el-form-item>
                 <el-button style="width:100px" @click="loginFormShow = 1">返回</el-button>
                 <el-button style="width:100px" @click="validateEven()" :disabled="!isSendCode">验证</el-button>
            </el-form>
        </div>
    </div>

    <div v-else-if="loginFormShow === 3" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;width:300px">
            <a @click="closeLoginForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">重置密码(设置新密码)</div>

            <el-form  ref="passwordForm" label-width="90px" class="demo-ruleForm">
                <el-form-item label="密码:" prop="password">
                    <el-input :type="password1" v-model="password" style="width:180px" auto-complete="off"></el-input>
                    <span class="show-pwd" @click="showPwd1()">
                        <svg-icon icon-class="eye" />
                    </span>
                </el-form-item>

                <el-form-item label="确认密码:" prop="rePassword">
                    <el-input :type="password2" v-model="rePassword" auto-complete="off" style="width:180px"></el-input>
                    <span class="show-pwd" @click="showPwd2">
                        <svg-icon icon-class="eye" />
                    </span>
                </el-form-item>
                 <button type="button" class="account-btn" style="width:100px" @click="sureReset">重置密码</button>
            </el-form>
        </div>
    </div>
</template>

<script>
import { login, sendValidateCode, checkValidateCode, resetPwd } from "@/api/login"
export default {
    name: 'LoginForm',
    data() {
        return {
            loginFormShow: 0,
            loginInfo: {
                account: undefined,
                password: undefined
            },
            email: undefined,
            type: 2,
            validateCode: undefined,
            password: undefined,
            rePassword: undefined,
            isSendCode: false,
            password1: 'password',
            password2: 'password',
            codeTime: 60,
            myTimer: undefined,
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
            this.loginFormShow = 1
        },

        closeLoginForm() {
            this.loginFormShow = 0
        },
        doLogin() {
            if(this.checkLoginFiled()){
                login(this.loginInfo).then(response => {
                    let data = {};
                    Object.assign(data,response.data.data)
                    console.log(data)
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
            }
        },
        checkLoginFiled() {
            let account = this.loginInfo.account
            let password = this.loginInfo.password
            if(account === undefined || account === null || account === '') {
                alert("请输入账号")
                return false
            }
            if(password === undefined || password === null || password === '') {
                alert("请输入账号密码")
                return false
            }
            return true
        },
        resetLoginInfo() {
            this.loginInfo.account = undefined
            this.loginInfo.password = undefined
        },
        goResetPwd() {
            this.loginFormShow = 2
        },
        sendCode() {
            if(this.checkEmail()){
                sendValidateCode(this.email,this.type).then(res => {
                    this.isSendCode = true
                    this.expireTimes()
                }).catch(res => {
                    this.$notify.error({
                        title: "异常",
                        message: res.status.msg,
                        type: 'error'
                    })
                })
            }
        },
        checkEmail() {
            let email = this.email
            if(email === undefined || email === null || email ==='') {
                alert("请输入邮箱")
                return false
            }
            let regex = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/
            if(!regex.test(this.email)) {
                alert("请输入正确的邮箱")
                return false
            }

            return true
        },
        validateEven() {
            if(this.checkEmail()){
                if(this.validateCode === undefined) {
                    alert("请输入验证码")
                    return
                }
                checkValidateCode(this.email,this.type,this.validateCode).then(res => {
                    this.loginFormShow = 3
                }).catch(res => {
                    this.$notify.error({
                        title: "异常",
                        message: res.status.msg,
                        type: 'error'
                    })
                })
            }
        },

        showPwd1() {
            if (this.password1 === 'password') {
                this.password1 = ''
            } else {
                this.password1 = 'password'
            }
        },
        showPwd2() {
            if (this.password2 === 'password') {
                this.password2 = ''
            } else {
                this.password2 = 'password'
            }
        },

        checkPassword() {
            if(this.password === undefined) {
                alert("请输入新的密码")
                return false
            }
            if(this.rePassword === undefined) { 
                alert("请输入确认密码")
                return false
            }

            if(this.rePassword !== this.password){
                alert("两次密码不一致")
                return false
            }

            return true
        },

        sureReset() {
            if(this.checkPassword()){
                resetPwd(this.email,this.password).then(res => {
                    this.$confirm('重置密码成功,是否去登录', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'success'
                    }).then(() => {
                        window.location.reload()
                    }).catch(() => {
                        window.location.reload()
                    })
                   
                }).catch(res => {
                    console.error("重置密码失败")
                })
            } 
        },

        // 定时器函数
        expireTimes(){
          let _this = this;
          this.myTimer = setInterval(function() {
            _this.codeTime--
            if(_this.codeTime <= 0) {
                _this.isSendCode = false
                clearInterval(_this.myTimer)
                _this.codeTime = 60
            }
          }, 1000)
        }
    }
}
</script>

