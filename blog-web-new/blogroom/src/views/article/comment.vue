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
                        <textarea placeholder="写点什么..." class="input-block-level comt-area" name="commentContent" id="comment" cols="100%" rows="3" tabindex="1"></textarea>
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
            </ol>
        </div>
    </div>
</template>


<script>
export default {
  name: 'Comment'
}
</script>
