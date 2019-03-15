<template>
    <div data-v-357a65ed="" class="view-container" id="main_content" style="margin-top: 10px;margin-left: 170px;background-color: #ededee;">
        <div data-v-0d738edb="" data-v-357a65ed="" class="cont view" style="width:1000px">
            <div data-v-0d738edb="" class="right_cont" style="background-color: white">
                <h3 data-v-0d738edb="" class="title" style="padding-left:20px">博客管理
                    <div style="float: right;font-size: 15px;padding-right: 20px">
                        <input type="radio" name="status" value="0">未审核
                    </div>
                    <div style="float: right;font-size: 15px;padding-right: 20px">
                        <input type="radio" name="status" value="1">已审核
                    </div>
                    <div style="float: right;font-size: 15px;padding-right:20px">
                        <input type="radio" name="status" checked="checked" value="2">全部
                    </div>
                </h3>

            </div>
            <div class="content-wrap">
                <div class="content">
                    <!--所有文章和分页；-->
                    <div class="blankA" v-if="total === 0"></div>
                    <simple-article v-for="index in pageSize" :key="index" v-bind="list[index-1]" style="padding-left: 20px;padding-top: 10px"/>
                    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { SimpleArticle } from '@/components/website'
import Pagination from '@/components/Pagination'
import { allArticles } from '@/api/article'

export default {
    name: 'mArticles',
    data() {
        return {
            list: null,
            total: 0,
            pageSize: 0,
            listQuery: {
                classId: undefined,
                title: undefined,
                userId: undefined,
                orderByUpdateTime: false,
                status: 2,
                page: 1,
                limit: 10
            }
        }
    },
    components: {
        Pagination,
        SimpleArticle
    },
    mounted() {
        this.getList()
    },
    methods: {
        getClassId() {
            let userInfo
            if(this.$store.state.user.userInfo.id === undefined) {
                userInfo = JSON.parse(this.$store.state.user.userInfo)
            } else {
                userInfo = this.$store.state.user.userInfo
            }
            this.listQuery.userId = userInfo.id
        },
        getList() {
            this.getClassId()
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