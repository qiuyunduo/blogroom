<template>
    <aside>
		<div id="asideProfile" class="aside-box">
            <h3 class="aside-title">个人资料</h3>
            <div class="profile-intro d-flex">
                <div class="avatar-box d-flex justify-content-center flex-column">
                    <img :src="userInfo.headImage" class="avatar_pic" id="user_head">
                </div>
                <div class="user-info d-flex justify-content-center flex-column">
                    <p class="name csdn-tracking-statistics tracking-click" data-mod="popu_379">
                        <span id="user_name">{{ userInfo.nickName }}</span>
                    </p>
                </div>
                <div v-if="isLogin" style="width: 120px">
                    <div v-if="isOther">
                        <a v-if="isFollow" data-v-0c56b7f6='' href="javascript:;" class='watch_btn' @click="cancelAttention">取消关注</a>
                        <a v-else data-v-0c56b7f6='' href="javascript:;" class='watch_btn' @click="addAttention">关注</a>
                    </div>
                </div>
                <div v-else style="width: 120px">
                    <!-- <a data-v-0c56b7f6='' class='watch_btn' onclick=''>关注</a> -->
                    <a data-v-0c56b7f6='' href="javascript:;" class='watch_btn' @click="addAttention">关注</a>
                </div>
            </div>
            <div class="data-info d-flex item-tiling">
                <dl class="text-center" title="0">
                    <dt>文章</dt>
                    <dd><span class="count" id="wenzhang">{{ blogInfo.articleNumber }}</span></dd>
                </dl>
                 <dl class="text-center" title="0">
                    <dt>粉丝</dt>
                    <dd><span class="count" id="fensi">{{ blogInfo.fansNumber }}</span></dd>
                </dl>
                <dl class="text-center" title="0">
                    <dt>喜欢</dt>
                    <dd><span class="count" id="xihuan">{{ blogInfo.likeNumber }}</span></dd>
                </dl>
                <dl class="text-center" title="0">
                    <dt>评论</dt>
                    <dd><span class="count" id="pinglun">{{ blogInfo.commentNumber }}</span></dd>
                </dl>
            </div>
            <div class="grade-box clearfix">
                <dl>
                    <dt>等级：</dt>
                    <dd id="dengji">
                        {{ this.rankInfo.level }}
                    </dd>
                </dl>
                <dl>
                    <dt>称号：</dt>
                    <dd title="0" id="chenghao">
                        {{ this.rankInfo.name }}           
                    </dd>
                </dl>
                <dl>
                    <dt>积分：</dt>
                    <dd title="2" id="jifen">
                        {{ blogInfo.integral }}            
                    </dd>
                </dl>
                <dl title="2978609">
                    <dt>排名：</dt>
                    <dd>297万+</dd>
                </dl>
            </div>
        </div>
        <div style="background-color: white;margin-top: -7px;height: 150px">
            <div style="border-bottom: 1px  gray;padding: 5px 0 10px 10px;">
                <strong>用户简介</strong>
            </div>
            <div style="padding: 5px 0 10px 10px;">
                <p v-if="userInfo.description !== null" id="jianjie">{{ userInfo.description}}</p>
                <p v-else id="jianjie">还没有添加任何语录</p>
                
            </div>
        </div>

        <div style="background-color: white;margin-top: 1px;height: 220px">
            <div style="border-bottom: 1px  gray;padding: 3px 0 10px 10px;">
                <strong>关注</strong>
            </div>
            <div id="guanzhu">
                <p v-if="isHaveFollowers()" style="padding-left: 10px">还没有添加任何关注</p>
                <a v-else v-for="index in followersLength" :key="index" :href="'/blog/room/'+followers[index-1].id" style="float: left">
                    <div style="padding-left:20px;padding-top:8px">
                        <img :src="followers[index-1].headImage" alt="博主头像" style='width: 65px;height: 60px;border-radius:50%'/>
                    </div>
                    <div style="height: 20px;text-align:center;width:90px">{{ splitText(followers[index-1].nickName) }}</div>
                </a>
            </div>
        </div>

        <div style="background-color: white;margin-top: 1px;height: 220px">
            <div style="border-bottom: 1px  gray;padding: 5px 0 10px 10px;">
                <strong>粉丝</strong>
            </div>
            <div id="fans">
                <p v-if="isHaveFans()" style="padding-left: 10px">还没有一个粉丝</p>
                <a v-else v-for="index in fansLength" :key="index" :href="'/blog/room/'+fans[index-1].id" style="float: left;">
                    <div style="padding-left:20px;padding-top:8px">
                        <img :src="fans[index-1].headImage" alt="博主头像" style='width: 65px;height: 60px;border-radius:50%'/>
                    </div>
                    <div style="height: 20px;text-align:center;width:90px">{{ splitText(fans[index-1].nickName) }}</div>
                </a>
            </div>
        </div>
    </aside>
</template>

<script>
import { detailUser } from "@/api/user"
import { blogDetail } from "@/api/blog"
import { rankDetail } from "@/api/rank"
import { allFollowersOfUser, allFansOfUser, findOne, attention, removeAttention } from "@/api/attention"
export default {
    name: 'BaseInfo',
    data() {
        return {
            userId: undefined,
            blogInfo: {},
            userInfo: {},
            rankInfo: {},
            followers: null,
            followersLength: 0,
            fans: null,
            fansLength: 0,
            isOther: true,
            isFollow: false,
            attentionQuery: {
                userId: undefined,
                page: 1,
                limit: 6
            },
            attentionDate: {
                user1Id: undefined,
                user2Id: undefined
            }
        }
    },
    computed: {
        isLogin() {
            return this.$store.state.user.isLogin
        }
    },
    mounted() {
        this.getall()
    },
    methods: {
        addAttention() {
            attention(this.attentionDate).then(res => {
                this.getall()
                this.isFollow = true
            }).catch(() => {
                alert("关注失败")
            })
        },
        cancelAttention() {
            removeAttention(this.attentionDate).then(res => {
                this.getall()
                this.isFollow = false
            }).catch(() => {
                alert("取消关注失败")
            })
        },
        isHaveFollowers(){
            return this.followers === null || this.followers.length === 0
        },
        isHaveFans(){
            return this.fans === null || this.fans.length === 0
        },
        getall() {
            let that = this
            this.getUserId().then(function(data){
                that.getUser()
                that.getBlog()
                that.getFollowers()
                that.getFans()
            });
        },
        checkIsFollow() {
            findOne(this.attentionDate).then(res => {
                if(res.data.data !== null && res.data.data !== undefined) {
                        this.isFollow = true
                }
            }).catch(() => {
                alert("获取双方关系发生异常")
            })
        },
        
        getUserId() {
            let that = this
            let userInfo = this.$store.state.user.userInfo
            return new Promise(function(resolve,reject){
                let id = that.$route.params.id
                that.userId = id
                if(that.isLogin) {
                    if(id === userInfo.id.toString()) {
                        that.isOther = false
                    }
                    that.attentionDate.user1Id = userInfo.id.toString()
                    that.attentionDate.user2Id = id
                    that.checkIsFollow()
                }
                that.attentionQuery.userId = id
                resolve("sucess")
            })
        },
        getUser() {
            detailUser(this.userId).then(res => {
                this.userInfo = res.data.data
            }).catch(() => {

            })
        },
        getBlog() {
            blogDetail(this.userId).then(response => {
                this.blogInfo = response.data.data
                rankDetail(this.blogInfo.rankId).then(response1 => {
                    this.rankInfo = response1.data.data
                }).catch(response1 => {
                    this.$notify.error({
                    title: '错误',
                    message: '用户博客等级获取出错'
                    })
                })
                // console.log(this.blogInfo)
            }).catch(response => {
                this.$notify.error({
                title: '错误',
                message: '用户博客信息获取出错'
                })
            })
        },
        getFollowers() {
            allFollowersOfUser(this.attentionQuery).then(response => {
                this.followers = response.data.pagingData.item
                this.followersLength = this.followers !== null ? this.followers.length : 0
            }).catch(response => {
            this.$notify.error({
              title: '错误',
              message: '用户关注获取出错'
            })
          })
        },
        getFans() {
            allFansOfUser(this.attentionQuery).then(response => {
                this.fans = response.data.pagingData.item
                this.fansLength = this.fans !== null ? this.fans.length : 0
            }).catch(response => {
            this.$notify.error({
              title: '错误',
              message: '用户粉丝获取出错'
            })
          })
        },
        splitText(str) {
            let size = str !== undefined && str !== null ? str.length : 0
            if(size > 5) {
                str = str.substr(0,5) + "..."
            }
            return str
        }
    },
    watch: {
        '$route':'getall'
    },
}
</script>

<style scoped>
    @import '../../style/blog001.css';
    @import '../../style/blog002.css';
    @import '../../style/blog003.css';
    .watch_btn[data-v-0c56b7f6]{float:right;border:1px solid #999;border-radius:4px;font-size:14px;color:#999;width:90px;height:32px;margin-top:10px;background:#fff;text-align:center;line-height:32px}
    .watch_btn[data-v-0c56b7f6]:hover{color:#ca0c16;background:#fde3e4;border:1px solid #ca0c16}
    .watch_btn[data-v-0c56b7f6]:active{color:#ca0c16;background:#fcc7ca;border:1px solid #ca0c16}
</style>
