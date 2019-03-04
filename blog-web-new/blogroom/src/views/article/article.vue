<template>
    <!--单个文章详情-->
   <div class="content">
		<header class="article-header">
			<h1 class="article-title"><a href="#" id="article_title">{{ detailInfo.title }}</a></h1>
			<div class="meta">
				<span id="mute-category" class="muted"><i class="fa fa-list-alt"></i><span id="article_keyword" style="padding-left: 10px">java</span></span>				<span class="muted"><i class="fa fa-user"></i> <a href="#" id="article_author">匿名</a></span>
				<time class="muted"><i class="fa fa-clock-o"></i> <span id="article_time">{{ detailInfo.publishTime }}</span></time>
				<span class="muted"><i class="fa fa-comments-o"></i> <a><span id="comment_count">{{ detailInfo.comments }}</span>评论</a></span>							
			</div>
		</header>

		<article class="article-content" id="article_content">
			<div v-html="detailInfo.content"></div>
			<div class="article-social">
				<a href="javascript:;" data-action="ding" data-id="393" id="Addlike" class="action" data-original-title="" title=""><i class="fa fa-heart-o"></i>喜欢 (<span class="count">{{ detailInfo.thumbs }}</span>)</a>	
			</div>
		</article>

		<comment/>
   </div>
</template>


<script>
import Comment from './comment'
import { readOne } from '@/api/article'
export default {
  name: 'Article',
  data() {
	  return {
		  detailInfo: null
	  }
  },
  components: {
	  Comment
  },
  watch: {
	  '$route':'getDetail'
  },
  created() {
	  this.getDetail()
  },
  methods: {
	  getDetail() {
		  var id = this.$route.params.id
		//   alert(id)
		  readOne(id).then(response => {
			  this.detailInfo = response.data.data
			  console.log(this.detailInfo)
		  }).catch(response => {
            this.$notify.error({
              title: '异常',
              message: '获取该文章出错'
            })
          })
	  }
  },
}
</script>
