<template>
    <div data-v-357a65ed="" class="left_box" style="margin-top: 10px;margin-left: -50px">
        <ul data-v-357a65ed="" class="aside">
            <li data-v-357a65ed="" :class="{'router-link-active':index === 1}" @click="setIndex(1)"><router-link to="info"><a data-v-357a65ed="" class="zl">个人资料</a></router-link></li>
            <li data-v-357a65ed="" :class="{'router-link-active':index === 2}" @click="setIndex(2)"><router-link to="followers"><a data-v-357a65ed="" class="zl">我的关注</a></router-link></li>
            <li data-v-357a65ed="" :class="{'router-link-active':index === 3}" @click="setIndex(3)"><router-link to="fans"><a data-v-357a65ed="" class="zl">我的粉丝</a></router-link></li>
        </ul>
        <ul data-v-357a65ed="" class="aside">
            <li data-v-357a65ed="" :class="{'router-link-active':index === 4}" @click="setIndex(4)"><router-link to="/blog/article/create"><a data-v-357a65ed="" target="_blank" class="zl">写博客</a></router-link></li>
            <li data-v-357a65ed="" :class="{'router-link-active':index === 5}" @click="setIndex(5)"><router-link to="mArticles"><a data-v-357a65ed="" class="zl">博客管理</a></router-link></li>
            <li data-v-357a65ed="" :class="{'router-link-active':index === 6}" @click="setIndex(6)"><router-link to="security"><a data-v-357a65ed="" class="zl">账号设置</a></router-link></li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'LeftMenu',
    data() {
        return {
            index: 1,
            userId: undefined,
            routePath: ''
        }
    },
    watch: {
        '$route':function () {
            alert(this.$route.path)
        }
    },
    created() {
        this.getUserId()
    },
    methods: {
        getUserId() {
            this.userId = this.$store.state.user.userInfo.id
        },
        setIndex(index) {
            this.index = index
        }
    },
}
</script>

