<template>
    <div data-v-357a65ed="" class="view-container" id="main_content" style="margin-top: 10px;margin-left: 170px;background-color: #ededee;">
        <div data-v-0d738edb="" data-v-357a65ed="" class="cont view" style="width:1000px">
            <div data-v-0d738edb="" class="right_cont" style="background-color: white">
                <h3 data-v-0d738edb="" class="title" style="padding-left:20px">
                    <span>博客管理</span>
                    <el-radio-group v-model="radioValue" @change="radioChange" style="margin-left: 500px">
                        <el-radio :label="-1">全部</el-radio>
                        <el-radio :label="0">草稿</el-radio>
                        <el-radio :label="1">待审核</el-radio>
                        <el-radio :label="2">已发布</el-radio>
                    </el-radio-group>
                </h3>
                

            </div>
            <div class="content-wrap">
                <div class="content">
                    <!--所有文章和分页；-->
                    <div class="blankA" v-if="total === 0"></div>
                    <simple-article v-for="index in pageSize" :key="index" v-bind="list[index-1]" style="width: 100%;padding:20px;margin-top:5px">
                        <span class='label label-important' slot="statusLabel">{{ statusMap[list[index-1].status] }}<i class='label-arrow'></i></span>

                        <div slot="functionMenu">
                            <p class='auth-span'>
                                <span class='muted'>
                                    <i class='fa fa-clock-o'></i>
                                    {{ list[index-1].updateTime }}
                                </span>
                                <span class='muted'>
                                    <i class='fa fa-edit'></i>
                                    <a :href="'/article/'+list[index-1].userId">编辑</a>
                                </span>
                                <span class='muted'> 
                                    <i class='fa fa-trash-o'></i>
                                    <a href="javascript:;"  @click="DelOne(list[index-1])">删除</a>
                                </span>
                            </p>
                        </div>
                    </simple-article>
                    <pagination v-show="total>5" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { SimpleArticle } from '@/components/website'
import Pagination from '@/components/Pagination'
import { allArticles, deleteOne } from '@/api/article'

const statusMap = {
    0: '草稿',
    1: '待审核',
    2: '已发布',
    3: '审核失败'
}

export default {
    name: 'mArticles',
    data() {
        return {
            list: null,
            total: 0,
            pageSize: 0,
            statusMap,
            radioValue: -1,
            listQuery: {
                classId: undefined,
                title: undefined,
                userId: undefined,
                orderByUpdateTime: true,
                status: undefined,
                page: 1,
                limit: 5
            }
        }
    },
    computed: {
        loginUser() {
            return  this.$store.state.user.userInfo
        }
    },
    components: {
        Pagination,
        SimpleArticle
    },
    mounted() {
        this.listQuery.userId = this.loginUser.id
        this.getList()
    },
    methods: {
        getList() {
            allArticles(this.listQuery).then(response => {
                this.list = response.data.pagingData.item
                this.total = response.data.pagingData.total
                this.pageSize = response.data.pagingData.pageSize
                console.log(this.list)
            }).catch(response => {
                this.$notify.error({
                title: '错误',
                message: '用户文章信息获取出错'
                })
          })
        },
        radioChange(value) {
            if(value < 0) {
                this.listQuery.status = undefined
            } else {
                this.listQuery.status = value.toString()
            }
            this.getList()
        },
        DelOne(article){
            this.$confirm('删除文章　“'+article.title+'”　, 是否删除?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                deleteOne(article.id).then(res => {
                    this.$notify.success({
                        title: '成功',
                        message: '删除文章'+article.title
                    })
                }).catch(res => {
                    this.$notify.error({
                        title: '修改失败',
                        message: res.status.msg
                    })
                })
            }).catch(() => {})
        }
    },
    watch: {
        '$route':'getList'
    },
}
</script>

<style scoped>
@import '../../style/user001.css';
@import '../../style/user002.css';

.blankA {
    background-image: url('../../images/timg.jpeg');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    background-color:rgb(255, 255, 255);
    width: 100%;
    height: 700px;
    align-content: center;
}
</style>