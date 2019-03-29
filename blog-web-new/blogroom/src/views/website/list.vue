<template>
    <div class="content">
        <div class="loading" v-if="total === -1"><img src="../../images/loading.gif" width="100px" height="100px" style="margin-left:400px;margin-top:300px"/></div>
        <div class="blankA" v-if="total === 0"></div>
        <simple-article v-for="index in pageSize" :key="index" v-bind="list[index-1]"/>
        <pagination v-show="total>10" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
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
            total: -1,
            pageSize: 0,
            listQuery: {
                classId: undefined,
                keyWord: undefined,
                userId: undefined,
                orderByUpdateTime: false,
                status: 2,
                page: 1,
                limit: 10
            }

        }
    },
    mounted() {
        this.getList()
    },
    methods: {
        getClassId() {
            let classid = this.$route.query.classId
            let keyWord = this.$route.query.keyWord
            this.listQuery.classId = classid
            this.listQuery.keyWord = keyWord
        },
        getList() {
            this.getClassId()
            allArticles(this.listQuery).then(response => {
                this.list = response.data.pagingData.item
                this.total = response.data.pagingData.total
                this.pageSize = response.data.pagingData.pageSize
                console.log(this.list)
            }).catch(response => {
                console.error("文章信息获取出错")
            })
        }
    },
    watch: {
        '$route':'getList'
    },
}
</script>

<style scoped>
.blankA {
    background-image: url('../../images/timg.jpeg');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    background-color:rgb(255, 255, 255);
    width: 100%;
    height: 700px;
    align-content: center;
}
.loading {
    background: white;
    width: 100%;
    height: 800px;
    align-content: center;
}
</style>

