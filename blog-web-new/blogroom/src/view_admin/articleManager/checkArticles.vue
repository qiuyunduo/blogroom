<template>
  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
    <h1 class="page-header">已审核文章管理 </h1>
    <div class="table-responsive" style="width:100%">
      <!-- 查询和其他操作 -->
      <div class="filter-container">
        <el-input v-model="listQuery.title" clearable class="filter-item" style="width: 200px" placeholder="文章标题"/>
         <el-input v-model="listQuery.userId" clearable class="filter-item" style="width: 200px" placeholder="作者id"/>
        <el-select v-model="listQuery.classId" style="width: 200px" class="filter-item" placeholder="文章类别">
          <el-option v-for="(key, value) in classMap" :key="key" :label="key" :value="value"/>
        </el-select>

        <el-button class="filter-item" type="primary" icon="el-icon-search" @click="queryAll" style="margin-left: 20px;margin-top: -10px">查找</el-button>
        <el-button class="filter-item" type="primary" @click="resetQuery" style="margin-left: 20px;margin-top: -10px">重置</el-button>
      </div>

      <!-- 查询结果 -->
      <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
        <el-table-column align="center"  label="ID" prop="id"/>

        <el-table-column align="center" label="标题" prop="title"/>

        <el-table-column align="center" label="作者" prop="author"/>

        <el-table-column align="center" label="文章类别" prop="classId">
          <template slot-scope="scope">
            <el-tag>{{ classMap[scope.row.classId] }}</el-tag>
          </template>
        </el-table-column>

        <el-table-column align="center" label="关键词" prop="keyword"/>
        
        <el-table-column align="center" label="评论数" prop="comments"/>

        <el-table-column align="center" label="点赞数" prop="thumbs"/>

        <el-table-column align="center" label="发布时间" prop="publishTime"/>

        <el-table-column align="center" label="状态" prop="status">
          <template slot-scope="scope">
            <el-tag>{{ statusMap[scope.row.status] }}</el-tag>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="70px" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="primary" size="mini"><a :href="'/article/'+scope.row.id" style="color: white;text-decoration:none" target="_blank">查看</a></el-button>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="150px" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="getDetail(scope.row.id)">编辑</el-button>
            <el-button type="primary" size="mini" @click="delArticle(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    </div>
  </div>
</template>

<script>
import { allArticles, deleteOne } from '@/api/article'
import Pagination from '@/components/Pagination'
import { getAllClass } from '@/api/articleClass'
import { Message } from 'element-ui'

const statusMap = {
  0: '草稿',
  1: '待审核',
  2: '已发布'
}

export default {
  name: 'CheckArticle',
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      statusMap,
      classMap: {},
      listQuery: {
        page: 1,
        limit: 10,
        title: undefined,
        classId: undefined,
        userId: undefined,
        status: 2
      },
    }
  },
  components: {
    Pagination
  },
  mounted() {
    let that = this
    this.getClassMap().then(function(data){
        that.getList()
    });
  },
  methods: {
    getList() {
      this.listLoading = true
      allArticles(this.listQuery).then(response => {
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
      this.listQuery.title = undefined
      this.listQuery.userId = undefined
      this.listQuery.classId = undefined
    },
    getDetail(articleId) {
      this.$router.push({path: '/admin/manage/article/edit', query:{ 'id': articleId }})
    },
    delArticle(article) {
      this.$confirm('删除文章　“'+article.title+'”　, 是否删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
      }).then(() => {
        deleteOne(article.id).then(res => {
          Message({
            message: '成功删除　“' + article.title + '”　',
            type: 'success',
            duration: 2 * 1000
          })
          this.getList()
        }).catch(() => {
          this.$notify.error({
              title: '错误',
              message: '文章删除失败'
          })
        })
      }).catch(() => {})
    },
    getClassMap() {
      let that = this
      return new Promise(function(resolve,reject){
        getAllClass().then(response => {
            let classMap = response.data.data
            for(let index in classMap) {
              that.classMap[classMap[index].id] = classMap[index].name
            }
            resolve("sucess")
        }).catch(() => {
          console.error("获取文章类别出错")
        })
      })
    }
  },
}
</script>


<style scoped>
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';
@import "element-ui/lib/theme-chalk/index.css";
</style>
