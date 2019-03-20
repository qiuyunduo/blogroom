<template>
    <div data-v-357a65ed="" class="view-container" id="main_content" style="margin-top: 10px;min-height: 800px;">
        <div data-v-0c56b7f6="" data-v-357a65ed="" class="content view">
            <div data-v-0d738edb="" class="right_cont" style="width: 900px;padding-top: 20px;border-bottom: 1px solid darkgrey">
                <h3 data-v-0d738edb="" class="title" style="font-size: 26px;border: none">账户管理</h3>

            </div>

            <div style="width: 900px">
                <div>
                        <el-form :model="updateInfo" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;margin-top: 20px">
                            <el-form-item label="原密码:" prop="oldPassword" style="height:50px">
                                <el-input v-model="updateInfo.oldPassword" :type="password1" placeholder="密码"/>
                                <span class="show-pwd" @click="showPwd1()">
                                    <svg-icon icon-class="eye" />
                                </span>
                            </el-form-item>

                            <el-form-item label="新密码:" prop="newPassword">
                                <el-input v-model="updateInfo.newPassword" :type="password2" placeholder="新密码"/>
                                <span class="show-pwd" @click="showPwd2">
                                    <svg-icon icon-class="eye" />
                                </span>
                            </el-form-item>

                            <el-form-item label="确认密码:">
                                <el-input v-model="secondPassword" :type="password3" placeholder="确认密码"/>
                                <span class="show-pwd" @click="showPwd3">
                                    <svg-icon icon-class="eye" />
                                </span>
                            </el-form-item>
                        </el-form>
                </div>

                        <div slot="footer" class="dialog-footer" style="padding-left:50px">
                            <el-button type="primary" @click="updatePassword">确定修改</el-button>
                        </div>
            </div>
        </div>
                   
    </div>
</template>

<script>
import { updatePwd } from "@/api/user"
import { Message, MessageBox } from 'element-ui'

export default {
    name: 'Security',
    data() {
        return {
            updateInfo: {
                id: undefined,
                oldPassword: undefined,
                newPassword: undefined,
            },
            secondPassword: undefined,
            password1: 'password',
            password2: 'password',
            password3: 'password'
        }
    },
    mounted() {
        this.updateInfo.id = this.$store.state.user.userInfo.id
    },
    methods: {
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
        showPwd3() {
            if (this.password3 === 'password') {
                this.password3 = ''
            } else {
                this.password3 = 'password'
            }
        },
        checkField() {
            if(this.updateInfo.newPassword !== this.secondPassword) {
                Message({
                    message: "确认密码与新密码不一致",
                    type: 'error',
                    duration: 1 * 1000
                })
                return false
            }

            if(this.updateInfo.newPassword === this.updateInfo.oldPassword) {
                Message({
                    message: "新密码和原密码一致",
                    type: 'error',
                    duration: 1 * 1000
                })
                return false
            }

            return true
        },
        updatePassword() {
            if(this.checkField()) {
                this.$confirm('您正在进行修改密码操作, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    updatePwd(this.updateInfo).then(res => {
                        this.$confirm('密码修改成功, 是否重新登录?', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'success'
                        }).then(() => {
                            this.$store.dispatch('LogOut')
                        }).catch(() => {})
                    }).catch(res => {
                        this.$notify.error({
                            title: '修改失败',
                            message: res.status.msg
                        })
                    })
                }).catch(() => {})
            }

        }
    },
}
</script>

<style scoped>
    @import '../../style/user001.css';
    @import '../../style/user002.css';
.show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: #889aa4;
    cursor: pointer;
    user-select: none;
}
</style>
