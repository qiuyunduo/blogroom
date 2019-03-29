<template>
    <div data-v-357a65ed="" class="view-container" id="main_content" style="margin-top: 10px;min-height: 800px;">
        <div data-v-0c56b7f6="" data-v-357a65ed="" class="content view">
            <div data-v-0d738edb="" class="right_cont" style="width: 900px;padding-top: 20px;border-bottom: 1px solid darkgrey">
                <h3 data-v-0d738edb="" class="title" style="font-size: 26px;border: none">我的关注</h3>

            </div>
            <div style="width: 900px">
            <ul data-v-0c56b7f6="" class="watch_list" id="myattention">
                <div v-if="total === 0" >您还没有关注任何人。。。！！！</div>
                <div v-else>
                    <li data-v-0c56b7f6='' class='item_cont' v-for="i in pageSize" :key="i" @mouseenter="enter" @mouseleave="level">
                        <a data-v-0c56b7f6='' :href="'/blog/room/'+list[i-1].id" target='_blank' class='fans'>
                            <img data-v-0c56b7f6='' :src='list[i-1].headImage' class='header'>
                        </a>
                        <a data-v-0c56b7f6='' :href="'/blog/room/'+list[i-1].id" target='' class='nick'>
                            {{ list[i-1].nickName }}
                        </a>
                        <a v-if="list[i-1].isFollow" data-v-0c56b7f6='' class='watch_btn' style="margin-right: 20px" @click="cancelAttention(list[i-1])">取消关注</a>
                            <a v-else data-v-0c56b7f6='' class='watch_btn' style="margin-right: 20px" @click="addAttention(list[i-1])">关注</a>
                    </li>
                    <pagination v-show="total>10" :total="total" :page.sync="attentionQuery.page" :limit.sync="attentionQuery.limit" @pagination="getList" />
                </div>
            </ul>
            </div>
        </div>
    </div>
</template>

<script>
import { allFollowersOfUser , attention, removeAttention } from "@/api/attention"
import Pagination from '@/components/Pagination'

export default {
    name: 'Followers',
    data() {
        return {
            list: null,
            total: 0,
            pageSize: 0,
            attentionQuery: {
                userId: undefined,
                page: 1,
                limit: 10
            },
            attentionDate: {
                user1Id: undefined,
                user2Id: undefined
            }
        }
    },
    computed: {
        loginUser() {
            return this.$store.state.user.userInfo
        }
    },
    components: {
        Pagination
    },
    mounted() {
        this.getList()
    },
    methods: {
        getList() {
            this.attentionQuery.userId = this.loginUser.id
            allFollowersOfUser(this.attentionQuery).then(response => {
                this.list = response.data.pagingData.item
                this.extendList(this.list)
                this.total = this.list !== null ? this.list.length : 0
                this.pageSize = response.data.pagingData.pageSize
            }).catch(response => {
                console.error('用户关注获取出错')
            })
        },
        extendList(list) {
            for(let index in list) {
                this.$set(list[index],"isFollow",true)
            }
        },
        enter() {
            let tareget = event.target
            tareget.style.background = '#f3f3f3'
        },
        level(){
              let tareget = event.target
            tareget.style.background = ''
        },
        addAttention(user) {
            this.attentionDate.user1Id = this.loginUser.id
            this.attentionDate.user2Id = user.id
            attention(this.attentionDate).then(res => {
                user.isFollow = true
            }).catch(() => {
                console.error("关注失败")
            })
        },
        cancelAttention(user) {
            this.attentionDate.user1Id = this.loginUser.id
            this.attentionDate.user2Id = user.id
            removeAttention(this.attentionDate).then(res => {
                user.isFollow = false
            }).catch(() => {
                console.error("取消关注失败")
            })
        },
    },
}
</script>

<style scoped>
    @import '../../style/user001.css';
    @import '../../style/user002.css';
</style>
