<template>
    <aside>
		<div id="asideProfile" class="aside-box">
            <h3 class="aside-title">个人资料</h3>
            <div class="profile-intro d-flex">
                <div class="avatar-box d-flex justify-content-center flex-column">
                    <img src="@/images/default.jpg" class="avatar_pic" id="user_head">
                </div>
                <div class="user-info d-flex justify-content-center flex-column">
                    <p class="name csdn-tracking-statistics tracking-click" data-mod="popu_379">
                        <span id="user_name">风吹起的落叶</span>
                    </p>
                </div>
                <input id="AttentionBtn" type="button" value="关注" onclick="addAttention()" 
                style="background-color: white;color: red;border: 1px solid red;height: 30px;width: 70px;
                text-align: center;font-size: 10px;float: right;margin-right: -10px;display: none">
            </div>
            <div class="data-info d-flex item-tiling">
                <dl class="text-center" title="0">
                    <dt>文章</dt>
                    <dd><span class="count" id="wenzhang">0</span></dd>
                </dl>
                 <dl class="text-center" title="0">
                    <dt>粉丝</dt>
                    <dd><span class="count" id="fensi">0</span></dd>
                </dl>
                <dl class="text-center" title="0">
                    <dt>喜欢</dt>
                    <dd><span class="count" id="xihuan">0</span></dd>
                </dl>
                <dl class="text-center" title="0">
                    <dt>评论</dt>
                    <dd><span class="count" id="pinglun">0</span></dd>
                </dl>
            </div>
            <div class="grade-box clearfix">
                <dl>
                    <dt>等级：</dt>
                    <dd id="dengji">
                        1
                    </dd>
                </dl>
                <dl>
                    <dt>称号：</dt>
                    <dd title="0" id="chenghao">
                        见习会员           </dd>
                </dl>
                <dl>
                    <dt>积分：</dt>
                    <dd title="2" id="jifen">
                        2            </dd>
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
                <p id="jianjie">还没有添加任何语录</p>
            </div>
        </div>

        <div style="background-color: white;margin-top: 1px;height: 220px">
            <div style="border-bottom: 1px  gray;padding: 5px 0 10px 10px;">
                <strong>关注</strong>
            </div>
            <div style="padding: 5px 0 0 10px;" id="guanzhu">
                <p v-if="isHaveFollowers">还没有添加任何关注</p>
                <a v-else v-for="index in followers.length" :key="index" href=''><img src="@/images/default.jpg" alt="博主头像" style='float: left;width: 60px;height: 60px;padding: 5px 5px'/></a>
            </div>
        </div>

        <div style="background-color: white;margin-top: 1px;height: 220px">
            <div style="border-bottom: 1px  gray;padding: 5px 0 10px 10px;">
                <strong>粉丝</strong>
            </div>
            <div style="padding: 5px 0 10px 10px;" id="fans">
                <p v-if="isHaveFans">还没有一个粉丝</p>
                <a v-else v-for="index in fans.length" :key="index" href=''><img src="@/images/default.jpg" alt="博主头像" style='float: left;width: 60px;height: 60px;padding: 5px 5px'/></a>
            </div>
        </div>
    </aside>
</template>

<script>
import { detailUser } from "@/api/user"
import { blogDetail } from "@/api/blog"
import { allFollowersOfUser, allFansOfUser } from "@/api/attention"
export default {
    name: 'BaseInfo',
    data() {
        return {
            userId: undefined,
            blogInfo: {},
            userInfo: {},
            followers: null,
            fans: null,
            followerQuery: {
                user1Id: undefined,
                page: 1,
                limit: 8
            },
            fansQuery: {
                user2Id: undefined,
                page: 1,
                limit: 8
            },
        }
    },
    created() {     
        this.getall()
    },
    methods: {
        isHaveFollowers(){
            return followers === null || followers.length === 0
        },
        isHaveFans(){
            return fans === null || fans.length === 0
        },
        getall() {
            this.getUserId()
            this.getUser()
            this.getBlog()
            this.getFollowers()
            this.getFans()
        },
        getUserId() {
            alert(1)
            let id = this.$route.params.id
            this.userId = id
            this.followerQuery.user1Id = id
            this.fansQuery.user2Id = id
        },
        getUser() {
            alert(2)
            detailUser(this.userId).then(response => {
                this.userInfo = response.data.data
                console.log(this.userInfo)
            }).catch(response => {
            this.$notify.error({
              title: '错误',
              message: '用户信息获取出错'
            })
          })
        },
        getBlog() {
            alert(3)
            blogDetail(this.userId).then(response => {
                this.blogInfo = response.data.data
                console.log(this.blogInfo)
            }).catch(response => {
            this.$notify.error({
              title: '错误',
              message: '用户博客信息获取出错'
            })
          })
        },
        getFollowers() {
            alert(4)
            allFollowersOfUser(this.followerQuery).then(response => {
                this.followers = response.data.pagingData.item
                console.log(this.followers)
            }).catch(response => {
            this.$notify.error({
              title: '错误',
              message: '用户关注获取出错'
            })
          })
        },
        getFans() {
            alert(5)
            allFansOfUser(this.fansQuery).then(response => {
                this.fans = response.data.pagingData.item
                console.log(this.fans)
            }).catch(response => {
            this.$notify.error({
              title: '错误',
              message: '用户粉丝获取出错'
            })
          })
        }
    },
    watch: {
        '$route':'getall'
    },
}
</script>
