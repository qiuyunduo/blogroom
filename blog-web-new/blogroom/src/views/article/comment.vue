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
                        <textarea v-model="myComment" placeholder="写点什么..." class="input-block-level comt-area" name="commentContent" id="comment" cols="100%" rows="3" tabindex="1"></textarea>
                        <div class="comt-ctrl">
                            <input class="btn btn-primary pull-right" type="button" id="addCommentBtn" tabindex="5" value="提交评论"/>
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
                        <a target='_blank' href=''>
                            <img class='avatar avatar-54 photo avatar-default' height='54' width='54' src='@/images/default.jpg' style='display: block;'>
                        </a>
                        <div class='c-main'>
                            {{ list[index-1].content }}
                            <div class='c-meta'>
                                <span class='c-author'>{{ list[index-1].nickName }}</span>
                                {{ list[index-1].addTime }}
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
import { allComments } from '@/api/comment'
export default {
    name: 'Comment',
    props: [
        'id'
    ],
    data() {
        return { 
            list: null,
            total: 0,
            myComment: undefined
        }
    },
    watch: {
        'id': 'getList'
    },
    methods: {
        getList() {
            allComments(this.id).then(response => {
                // alert(JSON.stringify(response.data))
                this.list = response.data.data
                this.total = this.list === null ? 0 : this.list.length
                //   console.log(this.list)
            }).catch(response => {
                // alert(JSON.stringify(response.data))
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

