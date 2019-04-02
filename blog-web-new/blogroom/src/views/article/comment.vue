<template>
    <!--文章评论-->
    <div>
        <div id="respond" class="no_webshot">
            <form id="commentform">
                <div class="comt-title">
                    <div class="comt-avatar pull-left"></div>
                    <div class="comt-author pull-left">发表我的评论</div>
                    <a id="cancel-comment-reply-link" class="pull-right" href="javascript:;">取消评论</a>
                </div>
                
                <div class="comt">
                    <input type="hidden" name="commentArticleId" id="commentArticleId">
                    <input type="hidden" name="commentAuthorId" id="commentAuthorId">
                    <div class="comt-box">
                        <textarea v-model="myComment.content" placeholder="写点什么..." class="input-block-level comt-area" name="commentContent" id="comment" cols="100%" rows="3" tabindex="1"></textarea>
                        <div class="comt-ctrl">
                            <input class="btn btn-primary pull-right" type="button" id="addCommentBtn" tabindex="5" value="提交评论" @click="submitComment"/>
                            <div class="comt-tips pull-right">
                                <div class="comt-tip comt-error" style="display: none;">#</div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div id="postcomments">
            <div id="comments">
                <i class="fa fa-comments-o"></i>
            </div>
            <ol class="commentlist" id="comment-content">
                <!--所有评论-->
                <div class="blankC" v-if="total === 0">
                    <p>还没有小伙伴吐槽</p>
                </div>
                <div v-else>
                    <!-- <b>4</b>个小伙伴在吐槽; -->
                    <li v-for="index in total" :key="index" class='comment odd alt thread-odd thread-alt depth-1'>
                        <div class='c-avatar'>
                            <a target='_blank' :href="'/blog/room/'+list[index-1].userId">
                                <img v-if="list[index-1].user" class='avatar avatar-54 photo avatar-default' height='54' width='54' :src="list[index-1].user.headImage" style='display: block;'>
                            </a>
                            <div class='c-main'>
                                {{ list[index-1].content }}
                                <div class='c-meta'>
                                    <a v-if="list[index-1].user" :href="'/blog/room/'+list[index-1].userId"><span class='c-author'>{{ list[index-1].user.nickName }}</span></a>
                                    <span class='c-author'>{{ list[index-1].addTime }}</span>
                                    <span class='c-author'>#{{ list[index-1].floor }}楼</span>
                                </div>
                            </div>
                        </div>
                    </li>
                </div>
            </ol>
        </div>
    </div>
</template>


<script>
import { allComments, createComment } from '@/api/comment'
import { simpleUser } from '@/api/user'
export default {
    name: 'Comment',
    props: [
        'id',
        'userId'
    ],
    data() {
        return { 
            list: null,
            total: 0,
            myComment: {
                articleId: undefined,
                authorId: undefined,
                userId: undefined,
                content: undefined
            }
        }
    },
    computed: {
        loginUser() {
            return this.$store.state.user.userInfo
        }
    },
    watch: {
        'id': 'getList'
    },
    methods: {
        getList() {
            allComments(this.id).then(response => {
                this.list = response.data.data
                this.extendList(this.list)
                
                // console.log(this.list)
            }).catch(response => {
                alert(response.data)
            })
        },
        extendList(list) {
            let i = 1
            for(let index in list) {
                simpleUser(list[index].userId).then(res => {
                    let user = res.data.data
                    this.$set(this.list[index],"user",user)
                    
                }).catch(res => {
                    alert("获取评论中用户的头像和昵称出错")
                })
                if(i === list.length) {
                    console.log(this.list)
                    this.total = this.list === null ? 0 : this.list.length
                }
                i++
                
            }
        },
        submitComment() {
            this.myComment.articleId = this.id
            this.myComment.authorId = this.userId
            this.myComment.userId = this.loginUser.id
            createComment(this.myComment).then(res => {
                this.myComment.content = undefined
                this.getList()
            }).catch(res => {
                this.$notify.error({
                    title: '错误',
                    message: '提交评论出现异常'
                })
            })
        }
    }
}
</script>

<style scoped>
.blankC{
    background-image: url('../../images/timg.jpeg');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    background-color:rgb(255, 255, 255);
    width: 100%;
    height: 300px;
    align-content: center;
}
</style>

