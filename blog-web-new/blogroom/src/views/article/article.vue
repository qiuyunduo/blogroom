<template>
    <!--单个文章详情-->
   <div class="content">
		<header class="article-header">
			<h1 class="article-title"><a href="#" id="article_title">{{ detailInfo.title }}</a></h1>
			<div class="meta">
				<span class="muted"><i class="fa fa-user"></i> <a href="#" id="article_author">{{ detailInfo.author }}</a></span>
				<time class="muted"><i class="fa fa-clock-o"></i> <span id="article_time">{{ detailInfo.publishTime }}</span></time>
				<span class="muted"><i class="fa fa-comments-o"></i> <a href="javaScript:;" @click="goComment"><span id="comment_count">{{ detailInfo.comments }}</span>评论</a></span>							
			</div>
		</header>

		<article class="article-content" id="article_content">
			<div v-html="detailInfo.content"></div>

			<div style="background: #f3f3f3;height:30px;padding-top:10px">
				<i class="fa fa-list-alt" style="margin-left:-50px"></i>
				<span id="mute-category" class="muted">
					<span style="margin-left: 40px">文章标签：</span>
					<span id="article_keyword">
						{{ detailInfo.keyword }}
					</span>
				</span>
			</div>
			<div class="article-social">
				<a href="javascript:;" data-action="ding" data-id="393" id="Addlike" class="action" data-original-title="" title=""><i class="fa fa-heart-o"></i>喜欢 (<span class="count">{{ detailInfo.thumbs }}</span>)</a>	
			</div>
		</article>

		<comment :id="detailInfo.id"/>
   </div>
</template>


<script>
import Comment from './comment'
import { readOne } from '@/api/article'
export default {
  name: 'Article',
  data() {
	  return {
		  detailInfo: {
				id: undefined,
				title: undefined,
				author: undefined,
				publishTIme: undefined,
				keyword: undefined,
				comments: 0,
				content: '',
				thumbs: 0
		  }
	  }
  },
  components: {
	  Comment
  },
  watch: {
		'$route':'getChange',
		detailInfo: function(){
			this.$nextTick(function() {
				let action = this.$route.query.action
				if(action !== undefined) {
					if(action === 'comment') {
						document.getElementById("respond").scrollIntoView();
						document.getElementById("comment").focus();
					}

					if(action === 'thumb') {
						document.getElementById("Addlike").scrollIntoView();
					}
				}
			})
		}
  },
  mounted() {
		this.getDetail()
  },
  methods: {
	  getChange() {
		  if(this.$route.params.id !== null) {
			this.getDetail()
		  }
	  },
	  getDetail() {
		  var id = this.$route.params.id
		  readOne(id).then(response => {
			  this.detailInfo = response.data.data
			  // console.log(this.detailInfo)
		  }).catch(response => {
            // this.$notify.error({
            //   title: '异常',
            //   message: '获取该文章出错'
            // })
			})

			
		},
		goComment() {
			document.getElementById("respond").scrollIntoView();
			document.getElementById("comment").focus();
      // // Firefox
      // document.documentElement.scrollTop = 10800
      // // Safari
      // window.pageYOffset = total[index] + 108
		}
  }
}
</script>
