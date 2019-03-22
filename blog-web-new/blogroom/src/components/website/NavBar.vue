<template>
    <div id="nav-header" class="navbar">
        <ul class="nav">
            <li class="menu-item menu-item-home" :class="{'current_page_item':classId === 0}">
                <a href="javascript:;" @click="articlesOfClass(0)">全部</a>
            </li>

            <li class="menu-item menu-item-type-taxonomy menu-item-object-category" :class="{'current_page_item':classId === classMap[index-1].id}" v-for="index in classMapSize" :key="index">
                <a href="javascript:;" @click="articlesOfClass(classMap[index-1].id)">{{ classMap[index-1].name }}</a>
            </li>

            <li style="float:left;margin-top:10px;margin-left:300px; ">
                <input style="width:230px; height:25px" type="text"  placeholder="搜索博主文章" v-model="keyWord">
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
            keyWord: undefined
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
            }).catch(() => {
                // this.$notify.error({
                //     title: '异常',
                //     message: '获取文章类别出错'
                // })
            })
        },
        articlesOfClass(id) {
            this.classId = id
            this.search()
            
        },
        search() {
            if(this.classId === 0) {
                this.$router.push({ path: '/index', query: {keyWord: this.keyWord}})   
            } else {
                this.$router.push({ path: '/articles', query: {classId:this.classId,keyWord: this.keyWord}})    
            }   
        }
    },
}
</script>

