<template>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
    <h1 class="page-header">操作</h1>

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px" placeholder="用户名"/>
      <el-select v-model="listQuery.status" style="width: 200px" class="filter-item" placeholder="账号状态">
        <el-option v-for="(key, value) in statusMap" :key="key" :label="key" :value="value"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="queryAll" style="margin-left: 20px;margin-top: -10px">查找</el-button>
      <el-button class="filter-item" type="primary" @click="resetQuery" style="margin-left: 20px;margin-top: -10px">重置</el-button>
      <el-button class="filter-item" type="primary" @click="resetQuery" style="margin-left: 20px;margin-top: -10px">添加用户</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="ID" prop="id"/>

      <el-table-column align="center" width="100px" label="姓名" prop="name"/>

      <el-table-column align="center" label="头像" prop="headImage">
        <template slot-scope="scope">
          <img :src="scope.row.headImage" width="40">
        </template>
      </el-table-column>

      <el-table-column align="center" label="邮箱" prop="email"/>

      <el-table-column align="center" label="性别" prop="sex">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sex !== 0">{{ sexMap[scope.row.sex] }}</el-tag>
        </template>
      </el-table-column>
      
      <el-table-column align="center" label="生日" prop="birthday"/>

      <el-table-column align="center" label="职业" prop="occupation"/>

      <el-table-column align="center" label="状态" prop="status">
        <template slot-scope="scope">
          <el-tag>{{ statusMap[scope.row.status] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="注册时间" prop="addTime"/>

      <el-table-column align="center" label="最近登录ip" prop="lastLoginIp"/>

      

      

      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="getDetail(scope.row)">编辑</el-button>
          <el-button type="primary" size="mini" @click="getDetail(scope.row)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    </div>
</template>

<script>
import { query } from '@/api/user'
import Pagination from '@/components/Pagination'
const sexMap = {
  0: '',
  1: '男',
  2: '女'
}

const statusMap = {
  0: '正常',
  1: '锁定',
  2: '注销'
}

export default {
  name: 'ManageUser',
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      statusMap,
      sexMap,
      listQuery: {
        page: 1,
        limit: 10,
        name: undefined,
        status: undefined
      },
    }
  },
  components: {
    Pagination
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      query(this.listQuery).then(response => {
        this.list = response.data.pagingData.item
        this.total = response.data.pagingData.total
        this.listLoading = false
      }).catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      })
    },
    queryAll() {
      this.listQuery.page = 1
      this.getList()
    },
    resetQuery() {
      this.listQuery.name = undefined
      this.listQuery.status = undefined
    },
    getDetail() {

    }
  },
}
</script>


<style scoped>
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';
@import "element-ui/lib/theme-chalk/index.css"
</style>
