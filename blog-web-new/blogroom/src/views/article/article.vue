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
			
					<a v-if="isThumb" href="javascript:;" @click="removeThumb" data-action="ding" data-id="393" id="Addlike" class="action" >
						<i class="fa fa-heart fa-5x"></i>
						(<span class="count">{{ detailInfo.thumbs }}</span>)
						取消点赞
					</a>	
		
					<a v-else href="javascript:;" @click="addThumb" data-action='ding' data-id='393' id='Addlike' class='action'>
                        <i class="fa fa-heart fa-5x"></i>
						(<span class="count">{{ detailInfo.thumbs }}</span>)
						点赞
                    </a>
				</div>
			
		</article>

		<comment :id="detailInfo.id" :userId="detailInfo.userId"/>
   </div>
</template>


<script>
import Comment from './comment'
import { findThumb, addOne, delOne } from '@/api/thumb'
import { readOne } from '@/api/article'
export default {
  	name: 'Article',
	data() {
		return {
			detailInfo: {
				id: undefined,
				userId: undefined,
				title: undefined,
				author: undefined,
				publishTIme: undefined,
				keyword: undefined,
				comments: 0,
				content: '',
				thumbs: 0,
			},
			thumbData: {
				id: undefined,
				articleId: undefined,
				authorId: undefined,
				userId: undefined
			},
			isThumb: false
		}
	},
	computed: {
		isLogin() {
			return this.$store.state.user.isLogin
		},
		loginUser() {
			return this.$store.state.user.userInfo
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
				if(this.isLogin) {
					this.checkIsThumb()
				}
			}).catch(response => {})			
		},
		checkIsThumb() {
			findThumb(this.detailInfo.id,this.loginUser.id).then(res => {
				if(res.data.data !== null && res.data.data !== undefined) {
					this.thumbData = res.data.data
					this.isThumb = true
				}
			}).catch(() => {})
		},
		addThumb() {
			this.thumbData.id = undefined
			this.thumbData.articleId = this.detailInfo.id
			this.thumbData.authorId = this.detailInfo.userId
			this.thumbData.userId = this.loginUser.id

			addOne(this.thumbData).then(res => {	
				this.detailInfo.thumbs++
				this.isThumb = true
				this.thumbData = res.data.data
			}).catch(() => { alert("sd") })
		},
		removeThumb() {
			delOne(this.thumbData.id,this.detailInfo.id).then(res => {
				this.detailInfo.thumbs--
				this.isThumb = false
			}).catch(() => {})
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
