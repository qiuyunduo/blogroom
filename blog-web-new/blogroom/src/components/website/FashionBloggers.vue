<template>
    <div class="widget widget_archive">
        <div class="title"><h2>时尚博主</h2></div>
        <ul id="goodauthor">
        <!--时尚博主-->
            <li v-for="index in total" :key="index">
                <a :href="'/blog/room/'+list[index-1].id">
                    <img :src='list[index-1].headImage' alt='博主头像' style='height: 60px;width: 60px'/>
                </a>
            </li>
        </ul>
    </div>
</template>


<script>    
import　{ fashionBlogger } from '@/api/user'
export default {
    name: 'FashionBloggers',
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
            fashionBlogger().then(response => {
                this.list = response.data.data
                this.total = this.list === null ? 0 : this.list.length
            }).catch(() => {
                
            })
        }
    },
}
</script>
