<template>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
        <h1 class="page-header">博客积分配置管理 </h1>
        <div class="table-responsive" style="width:1400px">
            <div class="filter-container">
                <!-- <el-button class="filter-item" type="primary" @click="toAdd" style="margin-left: 20px;margin-bottom:10px">添加</el-button> -->
            </div>
            <!-- 查询结果 -->
            <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
                <el-table-column align="center" width="100px" label="ID" prop="id"/>

                <el-table-column align="center" label="配置type" prop="type"/>

                <el-table-column align="center" label="积分变动描述" prop="illustrate"/>

                <el-table-column align="center" label="积分变动数额" prop="number"/>

                <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                    <el-button type="primary" size="mini" @click="editConfig(scope.row)">修改数额</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <!-- 修改对话框 -->
        <el-dialog :title="'修改配置'" :visible.sync="updateDialog">
            <el-form :rules="rules" :model="updateInfo" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
                <el-form-item label="积分变动数额">
                    <el-input v-model="updateInfo.number"/>
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
import { all, updateConfig } from '@/api/integralConfig'
export default {
    name: 'IntegralConfig',
    data() {
        return {
            list: null,
            listLoading: true,
            updateDialog: false,
            addDialog: false,
            updateInfo: {
                id: undefined,
                number: undefined
            },
            rules: {
                number: [{ required: true, message: '积分要求', trigger: 'blur' }]
            },
        }
    },
    mounted() {
        this.getConfigs()
    },
    methods: {
        getConfigs() {
            all().then(response => {
                this.list = response.data.data
                console.log(this.list)
                this.listLoading = false
            }).catch(() => {
                this.list = []
                this.listLoading = false
            })
        },
        editConfig(config) {
            this.updateInfo.id = config.id
            this.updateInfo.number = config.number

            this.updateDialog = true

        },
        updateData() {
            updateConfig(this.updateInfo).then(res => {
                this.$notify.success({
                    title: '成功',
                    message: '修改配置成功'
                })
                this.getConfigs()
                this.updateDialog = false
            }).catch(res => {
                this.$notify.error({
                    title: '错误',
                    message: '配置更新出现异常'
                })
            })
        }
  }
}
</script>

<style scoped>
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';
@import "element-ui/lib/theme-chalk/index.css";
</style>