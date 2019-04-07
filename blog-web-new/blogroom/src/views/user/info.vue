<template>
    <div data-v-357a65ed="" class="view-container" id="main_content" style="margin-top: 10px;min-height: 800px;">
        <div data-v-0d738edb="" data-v-357a65ed="" class="cont view">
            <div data-v-0d738edb="" class="right_cont">
                <h3 data-v-0d738edb="" class="title">个人资料</h3>

                <div data-v-0d738edb="" class="user_info">
                    <div data-v-0d738edb="" class="header">
                        <img data-v-0d738edb="" :src="userInfo.headImage" id="shead" class="head">
                        <p data-v-0d738edb="" class="modify"><a title="修改头像" data-toggle="modal" data-target="#updatehead" @click="openUpdateImage"><svg-icon icon-class="edit" style="height:20px;height:15px"></svg-icon>修改头像</a></p>
                    </div>

                    <!---->
                    <div data-v-0d738edb="" class="right_c">
                        <div data-v-0d738edb="" class="right_info">

                        </div>

                        <div data-v-0d738edb="" class="line"></div>

                        <div data-v-0d738edb="" class="nick">
                            <span><h3>基础信息</h3></span>
                            <span data-v-0d738edb="" class="mod"><a @click="showUpdateDialog" title="查看或修改个人信息" data-toggle="modal" data-target="#seeUserInfo" id="updateInfo"><svg-icon icon-class="edit" style="height:20px;height:15px"></svg-icon>修改</a></span>
                        </div>
                            <table>
                                <tr>
                                    <td class="leftTd">昵称：</td>
                                    <td class="rightTd">{{ userInfo.nickName }}</td>
                                    <td/>
                                    <td class="leftTd">性别：</td>
                                    <td class="rightTd">{{ sexMap[userInfo.sex] }}</td>
                                </tr>
                                <tr>
                                    <td class="leftTd">生日：</td>
                                    <td class="rightTd">{{ userInfo.birthday }}</td>
                                    <td/>
                                    <td class="leftTd">邮箱：</td>
                                    <td class="rightTd">{{ userInfo.email }}</td>
                                </tr>
                                <tr>
                                    <td class="leftTd">地区：</td>
                                    <td class="rightTd">{{ userInfo.area }}</td>
                                    <td/>
                                    <td class="leftTd">注册时间：</td>
                                    <td class="rightTd">{{ userInfo.addTime }}</td>
                                </tr>
                                <tr>
                                    <td class="leftTd">职业：</td>
                                    <td class="rightTd">{{ userInfo.occupation }}</td>
                                    <td/>
                                    <td class="leftTd">最近一次登录的ip：</td>
                                    <td class="rightTd">{{ userInfo.lastLoginIp }}</td>
                                </tr>
                                <tr>
                                    <td class="leftTd">简介：</td>
                                    <td colspan="4">{{ userInfo.description }}</td>
                                </tr>
                            </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- 修改头像对话框 -->
        <el-dialog :title="'修改头像'" :visible.sync="imageDialog">
            <div style="padding-left:25%;padding-top:50px;background:#f3f3f3">
                <div>
                    <el-upload
                    class="avatar-uploader"
                    :action="uploadPath"
                    accept=".jpg,.jpeg,.png,.gif"
                    :show-file-list="false"
                    :on-success="onSuccessUpload">
                        <img v-if="newHeadImage" :src="newHeadImage" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon el-upload" style="width:350px;height:350px"/>
                    </el-upload>
                </div>
                <div style="padding-top:50px;padding-left:50px;width:300px">
                    <el-button @click="imageDialog = false">取消</el-button>
                    <el-button type="primary" @click="updateImage">确定</el-button>
                </div>
            </div>
        </el-dialog>

        <!-- 编辑对话框 -->
        <el-dialog :title="'修改信息'" :visible.sync="updateDialog">
            <el-form :rules="rules" :model="updateInfo" status-icon label-position="left" label-width="100px" style="width: 80%; margin-left:50px;">
                <el-form-item label="昵称"  style="width:100%" prop="nickName">
                    <el-input v-model="updateInfo.nickName"/>
                </el-form-item>
                <el-form-item label="性别" style="width:100%" prop="sex">
                    <el-select v-model="updateInfo.sex">
                        <el-option :value="0" label=" "/>
                        <el-option :value="1" label="男"/>
                        <el-option :value="2" label="女"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="生日" prop="birthday">
                    <el-date-picker v-model="updateInfo.birthday" type="date" value-format="yyyy-MM-dd"/>
                </el-form-item>
                <el-form-item label="地区" prop="area">
                    <el-input v-model="updateInfo.area"/>
                </el-form-item>
                <el-form-item label="职业" prop="occupation">
                    <el-input v-model="updateInfo.occupation"/>
                </el-form-item>
                <el-form-item label="简介" prop="description">
                    <textarea v-model="updateInfo.description" style="height:100px;width:100%" placeholder="简单的介绍一下自己吧!..."/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="padding-right:40%">
                <el-button @click="updateDialog = false">取消</el-button>
                <el-button type="primary" @click="updateData">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { uploadPath } from '@/api/storage'
import { detailUser, updateUserBaseInfo, updateHeadImage } from '@/api/user'
import { updateAuthor } from '@/api/article'
import { Message } from 'element-ui'

const sexMap = {
    0: "",
    1: "男",
    2: "女"
}
export default {
    name: 'UserInfo',
    data() {
        return {
            sexMap,
            userInfo: {},
            uploadPath,
            newHeadImage: undefined,
            imageDialog: false,
            updateDialog: false,
            updateInfo: {
                id: undefined,
                nickName: undefined,
                sex: undefined,
                birthday: undefined,
                area: undefined,
                occupation: undefined,
                description: undefined
            },
            rules: {
                nickName: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
                sex: [{ required: true, message: '手机号码不能为空', trigger: 'blur' }],
                email: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
            },
        }
    },
    mounted() {
        this.getUserInfo()
    },
    methods: {
        getUserInfo() {
            let userInfo = this.$store.state.user.userInfo
            this.userInfo = userInfo
        },
        showUpdateDialog() {
            this.updateDialog = true

            this.updateInfo.id = this.userInfo.id
            this.updateInfo.nickName = this.userInfo.nickName
            this.updateInfo.sex = this.userInfo.sex
            this.updateInfo.birthday = this.userInfo.birthday
            this.updateInfo.area = this.userInfo.area
            this.updateInfo.occupation = this.userInfo.occupation
            this.updateInfo.description = this.userInfo.description
        },
        openUpdateImage() {
            this.imageDialog = true
            this.newHeadImage = undefined
        },
        updateImage() {

        },
        onSuccessUpload(response, file, fileList) {
            this.newHeadImage = response.data
        },
        onHandleRemove() {

        },
        handleUpload() {
            this.$refs.imageUpload.submit()
        },
        updateArticlesAuthor() {
            let _this = this
            return new Promise(function(resolve,reject) {
                updateAuthor(_this.updateInfo.nickName,_this.userInfo.id).then(res => {
                    resolve("成功")
                }).catch(() => { reject("昵称发生改变，更新用户文章中作者名字出错") })
            })
        },
        updateUser() {
            updateUserBaseInfo(this.updateInfo).then(res => {
                this.userInfo.nickName = this.updateInfo.nickName
                this.userInfo.sex = this.updateInfo.sex
                this.userInfo.birthday = this.updateInfo.birthday
                this.userInfo.area = this.updateInfo.area
                this.userInfo.occupation = this.updateInfo.occupation
                this.userInfo.description = this.updateInfo.description
                this.$store.dispatch('setUserInfo', this.userInfo)
                this.getUserInfo()
                this.updateDialog = false
                this.$notify.success({
                    title: '成功',
                    message: '修改信息成功',
                    duration: 1 * 1000
                })
            }).catch(() => {
                console.error("更新用户信息出错")
                
            })
        },
        updateData() {
            if(this.updateInfo.nickName !== this.userInfo.nickName) {
                let _this = this
                this.updateArticlesAuthor().then(function(data) {
                    // alert(data)
                    _this.updateUser()
                })
            } else {
                this.updateUser()
            }
        },
        updateImage() {
            if(this.newHeadImage === undefined) {
                Message({
                    message: "请选择一张图片",
                    type: 'error',
                    duration: 1 * 1000
                })
            }
            updateHeadImage(this.userInfo.id,this.newHeadImage).then(res => {
                this.userInfo.headImage = this.newHeadImage
                this.$store.dispatch('setUserInfo', this.userInfo)
                this.getUserInfo()
                this.imageDialog = false
                this.$notify.success({
                    title: '成功',
                    message: '修改信息成功',
                    duration: 1 * 1000
                })
            }).catch(res => {
                console.error("图片地址保存到数据库失败")
            })
        },
    },  
}
</script>

<style scoped>
@import '../../style/user001.css';
@import '../../style/user002.css';
table {
    width: 800px;
}
tr {
    height: 50px;
}
.leftTd {
    text-align: right;
    margin-left: -100px;
}

.rightTd {
    width: 250px;
}
.myLeftDiv{
  width:500px;
  height: 805px;
  margin-left:200px
}
.myRightDiv{
  width:500px;
  height: 773px;
  margin-left: 1300px;
  margin-top: -1070px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 350px;
  height: 350px;
  line-height: 350px;
  text-align: center;
}
.avatar {
  width: 350px;
  height: 350px;
  display: block;
}
</style>