<template>
    <main>
        <div class="filter-box d-flex align-items-center" style="margin-top:5px">
            <form action="https://blog.csdn.net/qydcsdn" id="seeOriginal">
                <label class="chk-box" for="chkOriginal">
                    <input  type="hidden" name="t" value="1" id="chkOriginal">
                </label>
            </form>
            <dl class="filter-sort-box d-flex align-items-center">
                <dt>排序：</dt>
                <dd><a href="javascript:void(0);" class="btn-filter-sort active" target="_self">默认</a></dd>
                <dd><a href="https://blog.csdn.net/qydcsdn?orderby=UpdateTime" class="btn-filter-sort " target="_self">按更新时间</a></dd>
                <dd><a href="https://blog.csdn.net/qydcsdn?orderby=ViewCount" class="btn-filter-sort " target="_self">按访问量</a></dd>
            </dl>
        </div>


        <div class="d-flex align-items-center" style="width: 900px;">
            <div class="content-wrap">
                <div class="content" style="margin-top: 1px">
                    <div v-if="total === 0" style="background: white;width: 900px;height: 500px;text-align: center;padding-top: 200px">
                        <h6>空空如也</h6>
                        <p>该博主很懒,什么文章都没有留下</p>
                    </div>
                    <simple-article v-for="index in pageSize" :key="index" v-bind="list[index-1]" style="width: 865px"/>
                    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" style="margin-top:-8px;width: 865px"/>
                </div>
            </div>
        </div>
    </main>
</template>

<script>
import { SimpleArticle } from '@/components/website'
import Pagination from '@/components/Pagination'
import { allArticles } from '@/api/article'

export default {
    name: 'Articles',
    components: {
        SimpleArticle,
        Pagination
    },
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
    created() {     
        this.getList()
    },
    methods: {
        getUserId() {
            let userId = this.$route.params.id
            this.listQuery.userId = userId
        },
        getList() {
            this.getUserId()
            allArticles(this.listQuery).then(response => {
                this.list = response.data.pagingData.item
                this.total = response.data.pagingData.total
                this.pageSize = response.data.pagingData.pageSize
                // console.log(this.list)
            }).catch(response => {
            this.$notify.error({
              title: '错误',
              message: '文章信息获取出错'
            })
          })
        }
    },
    watch: {
        '$route':'getList'
    },
}
</script>

<style>

</style>


<style scoped>
    @import '../../style/blog001.css';
    @import '../../style/blog002.css';
    @import '../../style/blog003.css';
</style>

