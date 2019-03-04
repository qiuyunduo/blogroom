<template>
    <div id="nav-header" class="navbar">
        <ul class="nav">
            <li class="menu-item menu-item-home" :class="{'current_page_item':classId === 0}">
                <a @click="articlesOfClass(0)">首页</a>
            </li>

            <li class="menu-item menu-item-type-taxonomy menu-item-object-category" :class="{'current_page_item':classId === value}" v-for="(key, value) in classMap" :key="key" :value="value" >
                <a @click="articlesOfClass(value)">{{key}}</a>
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

const classMap = {
    1: '程序人生',
    2: '编程',
    3: '前端',
    4: '数据库',
    5: '算法',
    6: '其他'
}

export default {
    name: 'NavBar',
    data() {
        return {
            classId: 0,
            classMap,
            title: undefined
        }
    },
    methods: {
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

