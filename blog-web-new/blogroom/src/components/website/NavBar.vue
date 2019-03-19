<template>
    <div id="nav-header" class="navbar">
        <ul class="nav">
            <li class="menu-item menu-item-home" :class="{'current_page_item':classId === 0}">
                <a href="javascript:;" @click="articlesOfClass(0)">首页</a>
            </li>

            <li class="menu-item menu-item-type-taxonomy menu-item-object-category" :class="{'current_page_item':classId === classMap[index-1].id}" v-for="index in classMapSize" :key="index">
                <a href="javascript:;" @click="articlesOfClass(classMap[index-1].id)">{{ classMap[index-1].name }}</a>
            </li>

            <li style="float:left;margin-top:10px;margin-left:300px; ">
                <input style="width:230px; height:25px" type="text" name="searchInput" placeholder="请输入搜索关键词" :value="title">
            </li>

            <li style="float:right;" @click="search">
                <div class="toggle-search active" style="width: 50px;">
                    <a id="searchA">
                        <i class="fa fa-search fa-lg"></i>
                    </a>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import { getAllClass } from '@/api/articleClass'
export default {
    name: 'NavBar',
    data() {
        return {
            classId: 0,
            classMap: null,
            classMapSize: 0,
            title: undefined
        }
    },
    mounted() {
        this.getClassMap()
    },
    methods: {
        getClassMap() {
            getAllClass().then(response => {
                this.classMap = response.data.data
                this.classMapSize = this.classMap.length
                this.$store.commit('SET_CLASSMAP',this.classMap)
            }).catch(() => {
                // this.$notify.error({
                //     title: '异常',
                //     message: '获取文章类别出错'
                // })
            })
        },
        articlesOfClass(id) {
            this.classId = id
            if(id === 0 ){
                this.$router.push( '/index')    
            } else {
                this.$router.push({ path: '/articles', query: {classId:id}})    
            }
            
        },
        search() {
            alert("sdsds")
        }
    },
}
</script>

