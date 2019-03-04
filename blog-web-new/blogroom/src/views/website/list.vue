<template>
    <div class="content">
        <simple-article v-for="item in list" :key="item" :baseInfo="item"/>
        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    </div>
</template>


<script>
import { SimpleArticle } from '@/components/website'
import Pagination from '@/components/Pagination'
import { allArticles } from '@/api/article'

export default {
    name: 'List',
    components: {
        Pagination,
        SimpleArticle
    },
    data() {
        return {
            list: null,
            total: 0,
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
        getClassId() {
            alert(this.$route.query.classId)
            var classid = this.$route.query.classId
            this.listQuery.classId = classid
        },
        getList() {
            // this.listQuery.classId = this.$route.query.classId
            this.getClassId()
            allArticles(this.listQuery).then(response => {
                this.list = response.data.pagingData.item
                this.total = response.data.pagingData.total
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
