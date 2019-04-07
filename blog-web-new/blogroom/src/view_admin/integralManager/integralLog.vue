<template>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
    <h1 class="page-header">操作</h1>

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.blogId" clearable class="filter-item" style="width: 100px" placeholder="博客id"/>
      <el-input v-model="listQuery.articleId" clearable class="filter-item" style="width: 100px" placeholder="文章id"/>
      <el-select v-model="listQuery.type" style="width: 150px" class="filter-item" placeholder="积分变动类型">
        <el-option v-for="(key, value) in typeMap" :key="key" :label="key" :value="value"/>
      </el-select>
      <el-date-picker v-model="listQuery.startTime" type="datetime" style="width: 150px" placeholder="开始日期" value-format="yyyy-MM-dd HH:mm:SS"/>
      <el-date-picker v-model="listQuery.endTime" type="datetime" style="width: 150px" placeholder="结束日期" value-format="yyyy-MM-dd HH:mm:SS" />
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="queryAll" style="margin-left: 20px;margin-top: -10px">查找</el-button>
      <el-button class="filter-item" type="primary" @click="resetQuery" style="margin-left: 20px;margin-top: -10px">重置</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" width="100px" label="序号id" prop="id" sortable/>
      <el-table-column align="center" label="博客id" prop="blogId"/>
      <el-table-column align="center" label="积分变动类型" prop="type">
          <template slot-scope="scope">
              {{ typeMap[scope.row.type] }}
          </template>
      </el-table-column>
      <el-table-column align="center" label="积分变动数额" prop="changeNumber"/>
      <el-table-column align="center" label="文章id" prop="articleId"/>
      <el-table-column align="center" label="积分变动时间" prop="addTime"/>
    </el-table>

    <pagination v-show="total>10" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    </div>
</template>

<script>
import { query } from '@/api/integral'
import Pagination from '@/components/Pagination'
const typeMap = {
  1: '撰写文章'
}

export default {
  name: 'IntegralLog',
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      typeMap,
      listQuery: {
        page: 1,
        limit: 10,
        blogId: undefined,
        type: undefined,
        articleId: undefined,
        changeNumber: undefined,
        startTime: undefined,
        endTime: undefined
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
        // console.log(this.list)
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
      this.listQuery = {
        page: 1,
        limit: 10,
        blogId: undefined,
        type: undefined,
        articleId: undefined,
        startTime: undefined,
        endTime: undefined
      }
    }
  },
}
</script>


<style scoped>
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';
@import "element-ui/lib/theme-chalk/index.css"
</style>
