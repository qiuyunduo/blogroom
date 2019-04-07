<template>
    <!-- 操作 -->
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
        <h1 class="page-header">友情链接管理 </h1>
        <div class="table-responsive" style="width:100%">
            <div class="filter-container">
                <el-button class="filter-item" type="primary" @click="toAdd" style="margin-left: 20px;margin-bottom:10px">添加</el-button>
            </div>
        <!-- 查询结果 -->
            <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
            <el-table-column align="center" width="100px" label="ID" prop="id"/>

            <el-table-column align="center" label="链接地址" prop="url"/>

            <el-table-column align="center" label="链接文本内容" prop="content"/>

            <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                <el-button type="primary" size="mini" @click="editLink(scope.row)">编辑</el-button>
                <el-button type="primary" size="mini" @click="delOne(scope.row)">删除</el-button>
                </template>
            </el-table-column>
            </el-table>
        </div>

         <!-- 添加对话框 -->
        <el-dialog :title="'添加链接'" :visible.sync="addDialog">
            <el-form :rules="rules" :model="newLink" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
                <el-form-item label="链接地址">
                    <el-input v-model="newLink.url" readonly/>
                </el-form-item>
                <el-form-item label="链接文本内容">
                    <el-input v-model="newLink.content" readonly/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addDialog = false">取消</el-button>
                <el-button type="primary" @click="addOne">确定</el-button>
            </div>
        </el-dialog>

        <!-- 编辑对话框 -->
        <el-dialog :title="'修改链接'" :visible.sync="updateDialog">
            <el-form :rules="rules" :model="updateInfo" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
                <el-form-item label="链接地址">
                    <el-input v-model="updateInfo.url" readonly/>
                </el-form-item>
                <el-form-item label="链接文本内容">
                    <el-input v-model="updateInfo.content" readonly/>
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
import { all, updateLink, addLink, delLink } from '@/api/friendlyLink'
export default {
    name: 'friendlyLink',
    data() {
        return {
            list: null,
            listLoading: true,
            updateDialog: false,
            addDialog: false,
            newLink: {
                url: undefined,
                content: undefined
            },
            updateInfo: {
                id: undefined,
                url: undefined,
                content: undefined
            },
            rules: {
                url: [{ required: true, message: '等级名称不能为空', trigger: 'blur' }],
                content: [{ required: true, message: '积分要求', trigger: 'blur' }]
            },
        }
    },
    mounted() {
        this.getList()
    },
    methods: {
        getList() {
            this.listLoading = true
            all().then(response => {
                this.list = response.data.data
                this.listLoading = false
            }).catch(() => {
                this.list = []
                this.listLoading = false
            })
        },
        editLink(link) {
            this.updateInfo.id = link.id
            this.updateInfo.url = link.url
            this.updateInfo.content = link.content

            this.updateDialog = true

        },
        updateData() {
            updateLink(this.updateInfo).then(res => {
                this.$notify.success({
                    title: '成功',
                    message: '修改成功'
                })
                this.updateDialog = false
                this.getList()
            }).catch(res => {
                this.$notify.success({
                    title: '异常',
                    message: '修改链接出错'
                })
            })
        },
        toAdd() {
            this.addDialog = true

            this.newLink.url = undefined
            this.newLink.content = undefined
        },
        addOne(){
            addLink(this.newLink).then(res => {
                this.addDialog = false
                this.getList()
            }).catch(res => {
                this.$notify.success({
                    title: '异常',
                    message: '添加链接出错'
                })
            })
        },
        delOne(link){
            this.$confirm('删除该链接:　“'+link.url+'”?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                delLink(link.id).then(res => {
                    this.getList()
                }).catch(res => {
                    this.$notify.success({
                        title: '异常',
                        message: '删除链接出错'
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