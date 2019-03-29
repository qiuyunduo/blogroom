<template>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
        <h1 class="page-header">博客等级管理 </h1>
        <div class="table-responsive" style="width:1400px">
        <!-- 查询结果 -->
            <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
            <el-table-column align="center" width="100px" label="ID" prop="id"/>

            <el-table-column align="center" width="100px" label="等级" prop="level"/>

            <el-table-column align="center" label="等级称号" prop="name"/>

            <el-table-column align="center" label="积分要求" prop="integral"/>

            <el-table-column align="center" label="上一次修改时间" prop="updateTime"/>

            <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                <el-button type="primary" size="mini" @click="editRank(scope.row)">编辑</el-button>
                <el-button type="primary" size="mini" @click="detail(scope.row)">详情</el-button>
                </template>
            </el-table-column>
            </el-table>
        </div>

        <!-- 编辑对话框 -->
        <el-dialog :title="'修改信息'" :visible.sync="updateDialog">
            <el-form :rules="rules" :model="updateInfo" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
                <el-form-item label="等级id">
                    <el-input v-model="detailInfo.id" readonly/>
                </el-form-item>
                <el-form-item label="等级">
                    <el-input v-model="detailInfo.level" readonly/>
                </el-form-item>
                <el-form-item label="等级名称" prop="name">
                    <el-input v-model="updateInfo.name"/>
                </el-form-item>
                <el-form-item label="积分要求" prop="integral">
                    <el-input v-model="updateInfo.integral"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="updateDialog = false">取消</el-button>
                <el-button type="primary" @click="updateData">确定</el-button>
            </div>
        </el-dialog>

        <!-- 详情对话框 -->
        <el-dialog :title="'等级详情信息'" :visible.sync="detailDialog">
            <el-form :model="detailInfo" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
                <el-form-item label="等级id">
                    <el-input v-model="detailInfo.id" readonly/>
                </el-form-item>
                <el-form-item label="等级">
                    <el-input v-model="detailInfo.level" readonly/>
                </el-form-item>
                <el-form-item label="等级名称" readonly>
                    <el-input v-model="detailInfo.name" readonly/>
                </el-form-item>
                <el-form-item label="积分要求" prop="integral">
                    <el-input v-model="detailInfo.integral" readonly/>
                </el-form-item>
                <el-form-item label="创建时间" prop="addTime">
                    <el-input v-model="detailInfo.addTime" readonly/>
                </el-form-item>
                <el-form-item label="更新时间" prop="updateTime">
                    <el-input v-model="detailInfo.updateTime" readonly/>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import { allRanks, updateRank } from '@/api/rank'
export default {
    name: 'ranks',
    data() {
        return {
            list: null,
            listLoading: true,
            detailDialog: false,
            detailInfo: {
                id: undefined,
                level: undefined,
                name: undefined,
                integral: undefined,
                addTime: undefined,
                updateTime: undefined
            },
            updateDialog: false,
            updateInfo: {
                id: undefined,
                name: undefined,
                integral: undefined
            },
            rules: {
                name: [{ required: true, message: '等级名称不能为空', trigger: 'blur' }],
                integral: [{ required: true, message: '积分要求', trigger: 'blur' }]
            },
        }
    },
    mounted() {
        this.getList()
    },
    methods: {
        getList() {
            this.listLoading = true
            allRanks().then(response => {
                this.list = response.data.data
                this.listLoading = false
            }).catch(() => {
                this.list = []
                this.total = 0
                this.listLoading = false
            })
        },
        detail(rank){
            this.detailInfo = rank
            this.detailDialog = true
        },
        editRank(rank) {
            this.detailInfo = rank
            this.updateInfo.id = rank.id
            this.updateInfo.name = rank.name
            this.updateInfo.integral = rank.integral

            this.updateDialog = true

        },
        updateData() {
            updateRank(this.updateInfo).then(res => {
                this.$notify.success({
                    title: '成功',
                    message: '等级修改成功'
                })
                this.updateDialog = false
                this.getList()
            }).catch(res => {
                this.$notify.error({
                    title: '错误',
                    message: '等级修改出现异常'
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