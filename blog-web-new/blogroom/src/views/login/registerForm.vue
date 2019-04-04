<template>
    <!--注册-->
    <div v-if="registerFormShow === 1" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;width:300px;height:230px">
            <a @click="closeRegisterForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">注册</div>
            <el-form :model="registerInfo" status-icon :rules="accountRules" ref="accountForm" label-width="60px" class="demo-ruleForm">
                <el-form-item label="账号:" prop="account">
                    <el-input v-model="registerInfo.account" auto-complete="off" v-focus></el-input>
                </el-form-item>
                <el-form-item label="昵称:" prop="nickName">
                    <el-input v-model="registerInfo.nickName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item>
                 <button type="button" class="account-btn" style="width:200px" @click="nextFoot">下一步</button>
                </el-form-item>
            </el-form>
        </div>
    </div>

    <div v-else-if="registerFormShow === 2" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;width:300px">
            <a @click="closeRegisterForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">注册(填写密码)</div>

            <el-form :model="registerInfo" status-icon :rules="passwordRules" ref="passwordForm" label-width="90px" class="demo-ruleForm">
                <el-form-item label="密码:" prop="password">
                    <el-input :type="password1" v-model="registerInfo.password" auto-complete="off" style="width:180px"></el-input>
                    <span class="show-pwd" @click="showPwd1()">
                        <svg-icon icon-class="eye" />
                    </span>
                </el-form-item>

                <el-form-item label="确认密码:">
                    <el-input :type="password2" v-model="rePassword" auto-complete="off" style="width:180px"></el-input>
                    <span class="show-pwd" @click="showPwd2()">
                        <svg-icon icon-class="eye" />
                    </span>
                </el-form-item>
                <button type="button" class="account-btn" style="width:100px;margin-right:40px" @click="preFoot">上一步</button>
                <button type="button" class="account-btn" style="width:100px" @click="nextFoot">下一步</button>
            </el-form>
        </div>
    </div>

    <div v-else-if="registerFormShow === 3" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;width:300px">
            <a @click="closeRegisterForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">注册(验证邮箱)</div>

            <el-form :model="registerInfo" status-icon :rules="emailRules" ref="emailForm" label-width="60px" class="demo-ruleForm">
                <el-form-item label="邮箱:" prop="email">
                    <el-input v-model="registerInfo.email" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="验证码:" prop="validateCode">
                    <el-input v-model="validateCode" style="width:120px" auto-complete="off"></el-input>
                    <el-button v-if="isSendCode" style="margin-top:-10px;width:100px" disabled>{{ codeTime}}</el-button>
                    <el-button v-else style="margin-top:-10px;width:100px" @click="sendCode()">获取验证码</el-button>
                </el-form-item>
                <label><input type="checkbox" name="angree" id="angree" value="1" checked="true" readonly> 我已认真阅读并同意博客屋的<a href="/" class="red-link" target="_blank">《使用协议》</a></label>
                 <button type="button" class="account-btn" style="width:100px;margin-right:40px" @click="preFoot">上一步</button>
                 <el-button style="width:100px" @click="doRegister()" :disabled="!isSendCode">注册</el-button>
            </el-form>
        </div>
    </div>
      
</template>

<script>
import { register, sendValidateCode, checkValidateCode, checkAccount, checkEmail } from "@/api/login"
import { addBlog } from '@/api/blog'

export default {
    name: 'RegisterForm',
    data() {
        return {
            loading: false,
            registerFormShow: 0,
            registerInfo: {
                account: undefined,
                nickName: undefined,
                password: undefined,
                rePassword: undefined,
                email: undefined
            },
            validateCode: undefined,
            isSendCode: false,
            rePassword: undefined,
            type: 1,
            codeTime: 60,
            myTimer: undefined,
            password1: 'password',
            password2: 'password',
            accountRules: {
                account: { required: true, message: '请输入账号', trigger: 'blur' },
                nickName: { required: true, message: '请输入昵称', trigger: 'blur' },
            },
            passwordRules: {
                password: { required: true, message: '请输入账号密码', trigger: 'blur' },
                rePassword: { required: true, message: '请输入确认密码', trigger: 'blur' },
            },
            emailRules: {
                email: { required: true, message: '请输入注册邮箱', trigger: 'blur' }
            },
            myTimer: undefined
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
        openRegisterForm() {
            this.loading = false
            this.registerFormShow = 1
            this.$nextTick(function() {
                this.$refs.raccount.focus()
            })
        },
        closeRegisterForm() {
            this.registerFormShow = 0
        },
        preFoot() {
            this.registerFormShow--
        },
        nextFoot() { 
            if(this.registerFormShow === 1) {
                this.$refs['accountForm'].validate((valid) => {
                    if (valid) {
                        checkAccount(this.registerInfo.account).then(res => {
                            this.registerFormShow++
                        }).catch(res => {
                            this.$notify.error({
                                title: "错误",
                                message: res.status.msg,
                                type: 'error'
                            })
                        })
                    }
                })
            }

            if(this.registerFormShow === 2) {
                this.$refs['passwordForm'].validate((valid) => {
                    if (valid) {
                        console.log(this.registerInfo.password)
                        console.log(this.rePassword)
                        if(this.registerInfo.password !== this.rePassword) {
                            alert("两次密码不一致")
                        } else {
                            this.registerFormShow++
                        }
                    }
                })
            }
            
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

        doRegister() {
            // this.loading = true
            checkValidateCode(this.registerInfo.email,this.type,this.validateCode).then(res => {
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
            }).catch(() => {
                alert("验证码错误")
            })
        },

        sendCode() {
            if(this.registerInfo.email !== undefined) {
                sendValidateCode(this.registerInfo.email,this.type).then(res => {
                    this.isSendCode = true
                    this.expireTimes()
                }).catch(res => {
                    console.log(res)
                    this.$notify.error({
                        title: "异常",
                        message: res.status.msg,
                        type: 'error'
                    })
                })
            } else {
                alert("请输入您的邮箱,进行注册验证")
            }
        },

         // 定时器函数
        expireTimes(){
          let _this = this;
          this.myTimer = setInterval(function() {
            _this.codeTime--
            if(_this.codeTime === 0) {
                _this.isSendCode = false
                clearInterval(_this.myTimer)
            }
          }, 1000)
        }
    },
}
</script>

