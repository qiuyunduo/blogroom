<template>
    <!--注册-->
    <div v-if="registerFormShow === 1" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;width:300px;height:230px">
            <a @click="closeRegisterForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">注册</div>
            <el-form :model="registerInfo" ref="firstForm" label-width="50px" class="demo-ruleForm">
                <el-form-item label="账号:">
                    <el-input v-model="registerInfo.account" auto-complete="off" v-focus></el-input>
                </el-form-item>
                <el-form-item label="昵称:">
                    <el-input v-model="registerInfo.nickName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item>
                 <button type="button" class="account-btn" style="width:200px" @click="nextFoot">下一步</button>
                </el-form-item>
            </el-form>
        </div>
    </div>

    <div v-else-if="registerFormShow === 3" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;width:300px">
            <a @click="closeRegisterForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">注册(验证邮箱)</div>

            <el-form :model="registerInfo" ref="secondForm" label-width="60px" class="demo-ruleForm">
                <el-form-item label="邮箱:">
                    <el-input v-model="registerInfo.email" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="验证码:">
                    <el-input v-model="registerInfo.email" style="width:120px" auto-complete="off"></el-input>
                    <el-button style="margin-top:-10px">获取验证码</el-button>
                </el-form-item>
                 <label><input type="checkbox" name="angree" id="angree" value="1" checked="true" readonly> 我已认真阅读并同意博客屋的<a href="/" class="red-link" target="_blank">《使用协议》</a></label>
                <button type="button" class="account-btn" style="width:100px;margin-right:40px" @click="preFoot">上一步</button>
                <button v-if="loading" type="button" class="account-btn" style="width:80px" @click="doRegister()">
                    <i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
                </button>
                 <button v-else type="button" class="account-btn" style="width:100px" @click="doRegister()">注册</button>
            </el-form>
        </div>
    </div>

    <div v-else-if="registerFormShow === 2" class="pop" id="register">
        <div class="pop-wp" style="left: 50%;width:300px">
            <a @click="closeRegisterForm()" rel="nofollow" title="关闭" class="pop-close" href="javascript:;" style="cursor:default">×</a>
            <div class="pop-title">注册(填写密码)</div>

            <el-form :model="registerInfo" ref="threeForm" label-width="80px" class="demo-ruleForm">
                <el-form-item label="密码:">
                    <el-input v-model="registerInfo.password" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="确认密码:">
                    <el-input v-model="rePassword" auto-complete="off"></el-input>
                </el-form-item>
                <button type="button" class="account-btn" style="width:100px;margin-right:40px" @click="preFoot">上一步</button>
                 <button type="button" class="account-btn" style="width:100px" @click="nextFoot">下一步</button>
            </el-form>
        </div>
    </div>

            <!-- <form id="registerForm" novalidate="novalidate">
                <div class="account-form">
                    <div>
                        <i class="fa fa-home" aria-hidden="true"></i>
                        <input type="text" name="account" ref="raccount" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" class="account-input" placeholder="账号名称" v-model="registerInfo.account" autofocus>
                        <div　style="display:inline;padding-top:10px">
                            <svg-icon iconClass="success" style="width:30px;height:30px;float:right;margin-top:10px"></svg-icon>
                        </div>
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
                    <label><input type="checkbox" name="angree" id="angree" value="1" checked="true"> 我已认真阅读并同意博客屋的<a href="/" class="red-link" target="_blank">《使用协议》</a></label>
                    <div id="error_tip_angree" class="error-tip"></div>
                </div>
                
                <button v-if="loading" type="button" class="account-btn" @click="doRegister()">
                    <i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
                </button>
                <button v-else type="button" class="account-btn" @click="doRegister()">注册</button>
            </form> -->
      
</template>

<script>
import { register } from '@/api/login'
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
                email: undefined
            },
            validateCode: undefined,
            rePassword: undefined,
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
            this.registerFormShow++
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
            // this.loading = true
            // if(this.checkFiled()) {
            //     register(this.registerInfo).then(res => {
            //         let data = res.data.data
            //         let token = data.token
            //         let userInfo = data.user
            //         //为该用户生成一个博客账号
            //         addBlog(userInfo.id).then(res => {
            //             this.$store.dispatch('setToken', token)
            //             this.$store.dispatch('setUserInfo', userInfo)
            //             this.$store.commit('SET_ISLOGIN', true)
            //             this.closeRegisterForm()
            //             this.$notify.success({
            //                 title: '成功',
            //                 message: '注册成功'
            //             })
            //         }).catch(() => {
            //             this.loading = false
            //         })
            //     }).catch(() => {
            //         this.loading = false
            //         this.$notify.error({
            //             title: '异常',
            //             message: '注册失败'
            //         })
            //     })
            // }
        },
    },
}
</script>

