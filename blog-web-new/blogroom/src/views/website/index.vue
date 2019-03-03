<template>
    <div>
        <nav-bar/>
        
        <section class="container">
            <div class="speedbar">
            <div class="toptip"><strong class="text-success"><i class="fa fa-volume-up"></i> </strong> </div>
            </div>
            <div class="content-wrap">
            <div class="content">
                <!-- <img src="/BlogRoom/images/loading.gif" style="width: 100% "> -->
                <!--所有文章和分页；-->
                <article v-for="a in 6" class='excerpt' :key="a">
                <header>
                    <h2><a href='/BlogRoom/apps/article.html'>文章标题</a></h2>
                </header>
                <div class='focus'><a href='/BlogRoom/apps/article.html'>
                    <img class='thumb' src='/upload/articlepic' style='width: 150px;height: 100px'></a>
                </div>
                <span class='note'>
                    文章简介
                </span>
                <p class='auth-span'>
                    <span class='muted'>
                    <i class='fa fa-user'></i>
                    <a href='/BlogRoom/apps/userblog.html'>文章作者</a>
                    </span> 
                    <span class='muted'>
                    <i class='fa fa-clock-o'></i>
                    文章发表时间
                    </span>
                    <span class='muted'>
                    <i class='fa fa-comments-o'></i>
                    <a target='_blank' href="/BlogRoom/apps/article.html评论">３评论</a>
                    </span>
                    <span class='muted'> 
                    <a href='javascript:;' data-action='ding' data-id='393' id='Addlike' class='action'>
                        <i class='fa fa-heart-o'></i>
                        <span class='count'>３</span>
                        喜欢
                    </a>
                    </span>
                </p>
                </article>

                <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
            </div>
            </div>


            <aside class="sidebar">
                <fashion-articles/>
                <fashion-bloggers/>
                <friendly-link/>
            </aside>
        </section>
        
    </div>
</template>


<script>
import { FashionArticles, FashionBloggers, FriendlyLink, NavBar, Pagination } from '@/components'
// import Pagination from '@/components/Pagination/index' 

export default {
    
  name: 'index',
  components: {
      FashionArticles, 
      FashionBloggers, 
      FriendlyLink, 
      NavBar,
      Pagination
  },
  data() {
      return {
          total: 5,
          listQuery: {
              page: 1,
              limit: 10
          }
      }
  },
  methods: {
      getList() {
          return null
      }
  }
}
</script>
