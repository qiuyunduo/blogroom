<template>
    <div class="widget widget_recent_entries">
        <div class="title"><h2>最新博文</h2></div>
            <ul id="goodarticle">
            <!--时尚博文-->
                <li v-for="index in total" :key="index">
                    <a :href="'/article/'+list[index-1].id">{{ list[index-1].title }}</a>
                </li>
            </ul>
    </div>
</template>


<script>
import { fashionArticles } from '@/api/article'

export default {
    name: 'FashionArticles',
    data() {
        return {
            list: null,
            total: 0
        }
    },
    mounted() {
        this.getList()
    },
    methods: {
        getList() {
            fashionArticles().then(response => {
                this.list = response.data.data
                this.total = this.list === null ? 0 : this.list.length
            }).catch(() => {
                
            })
        }
    },
}
</script>


