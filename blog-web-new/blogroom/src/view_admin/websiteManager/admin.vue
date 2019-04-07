<template>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
        <h1 class="page-header">管理员 </h1>
        <div class="table-responsive" style="width:100%">
             <div class="filter-container">
                <el-button class="filter-item" type="primary" @click="toAdd" style="margin-left: 20px;margin-bottom:10px">添加</el-button>
            </div>
        <!-- 查询结果 -->
            <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
            <el-table-column align="center" width="100px" label="ID" prop="id"/>

            <el-table-column align="center" width="100px" label="账号" prop="name"/>

            <el-table-column align="center" label="邮箱" prop="email"/>

            <el-table-column align="center" label="权限身份" prop="status">
                <template slot-scope="scope">
                    <el-tag>{{ statusMap[scope.row.status] }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column align="center" label="登录次数" prop="loginNumber"/>
            <el-table-column align="center" label="最近一次登录ip" prop="lastLoginIp"/>
            <el-table-column align="center" label="最近一次登录时间" prop="lastLoginTime"/>

            <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
                <template slot-scope="scope" v-if="scope.row.id !== 1">
                <el-button type="primary" size="mini" @click="editAdmin(scope.row)">编辑</el-button>
                <el-button type="primary" size="mini" @click="delOne(scope.row)">删除</el-button>
                </template>
            </el-table-column>
            </el-table>
        </div>

       <!-- 添加对话框 -->
        <el-dialog :title="'添加管理员'" :visible.sync="addDialog">
            <el-form :rules="rules" :model="newAdmin" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
                <el-form-item label="管理员账号" prop="name">
                    <el-input v-model="newAdmin.name" />
                </el-form-item>
                <el-form-item label="管理员密码" prop="password">
                    <el-input v-model="newAdmin.password"/>
                </el-form-item>
                <el-form-item label="管理员邮箱" prop="email">
                    <el-input v-model="newAdmin.email"/>
                </el-form-item>
                <el-form-item label="管理员身份">
                    <el-select v-model="newAdmin.status" style="width: 200px" class="filter-item" placeholder="管理员身份">
                        <el-option :value="0" label="超级管理员"/>
                        <el-option :value="1" label="普通管理员"/>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addDialog = false">取消</el-button>
                <el-button type="primary" @click="addOne">确定</el-button>
            </div>
        </el-dialog>

        <!-- 编辑对话框 -->
        <el-dialog :title="'修改管理员'" :visible.sync="updateDialog">
            <el-form :rules="rules" :model="adminInfo" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
                <el-form-item label="管理员ip">
                    <el-input v-model="adminInfo.id" readonly/>
                </el-form-item>
                <el-form-item label="管理员账号">
                    <el-input v-model="adminInfo.name" readonly/>
                </el-form-item>
                <el-form-item label="管理员身份">
                    <el-select v-model="adminInfo.status" style="width: 200px" class="filter-item" placeholder="管理员身份">
                        <el-option :value="0" label="超级管理员"/>
                        <el-option :value="1" label="普通管理员"/>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="updateDialog = false">取消</el-button>
                <el-button type="primary" @click="updateData">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { createAdmin, updateAdmin, findAllAdmin, destoryAdmin} from '@/api/admin'

const statusMap = {
    0: '超级管理员',
    1: '普通管理员'
}

export default {
    name: 'Admin',
    data() {
        return {
            list: null,
            listLoading: true,
            statusMap,
            addDialog: false,
            newAdmin: {
                name: undefined,
                password: undefined,
                email: undefined,
                status: 0
            },
            updateDialog: false,
            adminInfo: {
                id: undefined,
                name: undefined,
                status: undefined
            },
            rules: {
                name: [{ required: true, message: '账号名称', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }]
            },
        }
    },
    mounted() {
        this.getList()
    },
    methods: {
        getList() {
            this.listLoading = true
            findAllAdmin().then(response => {
                this.list = response.data.data
                this.listLoading = false
            }).catch(() => {
                this.list = []
                this.listLoading = false
            })
        },
        editAdmin(admin) {
            this.adminInfo = admin

            this.updateDialog = true
        },
        updateData() {
            let updateInfo = { 'id': this.adminInfo.id, 'status': this.adminInfo.status }
            updateAdmin(updateInfo).then(res => {
                this.$notify.success({
                    title: '成功',
                    message: '修改成功'
                })
                this.updateDialog = false
                this.getList()
            }).catch(res => {
                this.$notify.error({
                    title: '错误',
                    message: '修改管理员出错'
                })
            })
        },
        toAdd() {
            this.newAdmin = {
                name: undefined,
                password: undefined,
                status: 0
            }

            this.addDialog = true
        },
        addOne(){
            // console.log(this.newAdmin)
            createAdmin(this.newAdmin).then(res => {
                this.addDialog = false
                this.getList()
            }).catch(res => {
                this.$notify.error({
                    title: '错误',
                    message: '添加管理员出错'
                })
            })
        },
        delOne(admin){
            console.log(admin)
            this.$confirm('删除该管理员:　“'+admin.name+'”?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                destoryAdmin(admin.id).then(res => {
                    this.getList()
                }).catch(res => {
                    this.$notify.error({
                        title: '错误',
                        message: '删除管理员出错'
                    })
                })
            }).catch(() => {})
        }
  }
}
</script>

<style scoped>
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';
@import "element-ui/lib/theme-chalk/index.css";
</style>