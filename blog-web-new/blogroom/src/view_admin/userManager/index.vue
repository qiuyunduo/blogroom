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
      <el-table-column align="center" width="60px" label="ID" prop="id"/>
      <el-table-column align="center" width="100px" label="账号" prop="account"/>
      <el-table-column align="center" width="100px" label="昵称" prop="nickName"/>
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
      <el-table-column align="center" label="操作" width="150" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="getDetail(scope.row)">详情</el-button>
          <el-button v-if="scope.row.status === 0" type="primary" size="mini" @click="updateStatus(scope.row,1)">禁用</el-button>
          <el-button v-if="scope.row.status === 1" type="primary" size="mini" @click="updateStatus(scope.row,0)">启用</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

      <!-- 详细信息框 -->
    <el-dialog :title="'详细信息'" :visible.sync="detailDialog">
      <h3 style="margin-top: -10px;margin-bottom: 20px">用户信息</h3>
      <table class="table" style="width:900px;">
        <tr>
          <td rowspan="5" colspan="2"><img :src="userInfo.headImage" style="width: 200px;height: 200px;border-radius:50%"/></td>
          <td style="text-align:center">ID:</td>
          <td>{{ userInfo.id }}</td>
          <td style="text-align:center">账号:</td>
          <td>{{ userInfo.account }}</td>
          <td style="text-align:center">状态:</td>
          <td>{{ statusMap[userInfo.status] }}</td>
        </tr>
        <tr>
          <td style="text-align:center">昵称:</td>
          <td>{{ userInfo.nickName }}</td>
          <td style="text-align:center">邮箱:</td>
          <td>{{ userInfo.email }}</td>
          <td style="text-align:center">注册时间:</td>
          <td>{{ userInfo.addTime }}</td>
        </tr>
        <tr>
          <td style="text-align:center">性别:</td>
          <td>{{ sexMap[userInfo.sex] }}</td>
          <td style="text-align:center">生日:</td>
          <td>{{ userInfo.birthday }}</td>
          <td style="text-align:center">上一次登录ip:</td>
          <td>{{ userInfo.lastLoginIp }}</td>
        </tr>
        <tr>
          <td style="text-align:center">地区:</td>
          <td>{{ userInfo.area }}</td>
          <td style="text-align:center">职业:</td>
          <td>{{ userInfo.occupation }}</td>
        </tr>
        <tr style="height:50px">
          <td style="text-align:center">自我简介:</td>
          <td colspan="5">{{ userInfo.description}}</td>
        </tr>
      </table>
      <h3 style="margin-top: 50px">博客信息</h3>
      <table class="table" style="width:800px">
        <tr>
          <td style="text-align:center">博客id：</td>
          <td>{{ blogInfo.id }}</td>
          <td style="text-align:center">博客等级：</td>
          <td>{{ rankInfo.level }}</td>
          <td style="text-align:center">等级称号：</td>
          <td>{{ rankInfo.name }}</td>
          
        </tr>
        <tr>
          <td style="text-align:center">积分：</td>
          <td>{{ blogInfo.integral }}</td>
          <td style="text-align:center">文章数：</td>
          <td>{{ countInfo.articleNumber }}</td>
          <td style="text-align:center">访客：</td>
          <td>{{ blogInfo.visitNumber }}</td>
        </tr>
        <tr>
          <td style="text-align:center">粉丝数：</td>
          <td>{{ countInfo.fansNumber }}</td>
          <td style="text-align:center">评论数：</td>
          <td>{{ countInfo.commentNumber }}</td>
          <td style="text-align:center">点赞数：</td>
          <td>{{ countInfo.likeNumber }}</td>
        </tr>
        <tr>
          <td style="text-align:center">状态：</td>
          <td>{{ statusMap[blogInfo.status] }}</td>
          <td colspan="2"></td>
          <td style="text-align:center">创建时间：</td>
          <td>{{ transTIme(blogInfo.addTime) }}</td>
        </tr>

      </table>

    </el-dialog>
  </div>
</template>

<script>
import { query, updateUserStatus } from '@/api/user'
import { blogDetail, updateBlog } from "@/api/blog"
import { countArticles } from "@/api/article"
import { rankDetail } from "@/api/rank"
import { countComment } from "@/api/comment"
import { countThumb } from "@/api/thumb"
import { countFans } from "@/api/attention"
import Pagination from '@/components/Pagination'
const sexMap = {
  0: '',
  1: '男',
  2: '女'
}

const statusMap = {
  0: '启用',
  1: '禁用'
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
      detailDialog: false,
      userInfo: {
        id: undefined,
        account: undefined,
        nickName: undefined,
        sex: undefined,
        birthday: undefined,
        email: undefined,
        headImage: undefined,
        area: undefined,
        occupation: undefined,
        description: undefined,
        status: undefined,
        lastLoginIp: undefined,
        addTime: undefined
      },
      blogInfo: {
        id: undefined,
        rankId: undefined,
        integral: undefined,
        visitNumber: 0,
        status: undefined,
        addTIme: undefined
      },
      rankInfo: {
          level: undefined,
          name: undefined,
      },
      countInfo: {
          articleNumber: 0,
          likeNumber: 0,
          commentNumber: 0,
          fansNumber: 0,
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
    getDetail(user) {
      this.userInfo = user
      blogDetail(user.id).then(res => {
        this.blogInfo = res.data.data
        rankDetail(this.blogInfo.rankId).then(response1 => {
            this.rankInfo = response1.data.data
            this.detailDialog = true
        }).catch(response1 => {
          console.error("用户博客等级获取出错")
        })
      }).catch(() => { console.error("获取博客信息出错") })
      this.getCountInfo(user.id)
    },
    getCountInfo(id) {
      countArticles(id).then(res => {
          // console.log(res.data)
          this.countInfo.articleNumber = res.data.data
      }).catch(() => {
          console.error("统计文章数目出错")
      })
      countComment(id).then(res => {
          // console.log(res.data)
          this.countInfo.commentNumber = res.data.data
      }).catch(() => {
          console.error("统计评论数目出错")
      })
      countThumb(id).then(res => {
          // console.log(res.data)
          this.countInfo.likeNumber = res.data.data
      }).catch(() => {
          console.error("统计点赞数目出错")
      })
      countFans(id).then(res => {
          // console.log(res.data)
          this.countInfo.fansNumber = res.data.data
      }).catch(() => {
          console.error("统计粉丝数目出错")
      })
    },
    updateStatus(user,status) {
      let updateData = { 'userId': user.id, 'status': status }
      updateUserStatus(updateData).then(res => {
        updateBlog(updateData).then(res => {
          this.$notify.success({
            title: '成功',
            message: '用户状态更新成功'
          })
          user.status = status
        }).catch(() => { 
          this.$notify.success({
            title: '错误',
            message: '更新博客状态状态出错'
          })
        })
      }).catch(() => { 
        this.$notify.success({
          title: '错误',
          message: '更新用户状态出错'
        })
      })
      
    },
    transTIme(initTime) {
      if((initTime instanceof Array)){
          let date = initTime[0]+"-"+initTime[1]+"-"+initTime[2]
          let time = initTime[3]+":"+initTime[4]+":"+initTime[5]
          return date+' '+time
      } else {
          return initTime
      }
    }
  },
}
</script>


<style scoped>
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';
@import "element-ui/lib/theme-chalk/index.css";
.mytable tr .leftTd {
  text-align: right;
  margin-left: -100px;
}
.mytable tr .rightTd {
  width: 250px;
}

</style>
