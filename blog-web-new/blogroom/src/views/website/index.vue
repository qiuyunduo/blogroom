<template>
    <div>
        <nav-bar/>
        <section class="container">
            <div class="speedbar" style="height: 18px;margin-top:-15px">
                <website-notice/>
            </div>
            <div class="content-wrap">
                <keep-alive>
                    <router-view/>
                </keep-alive>
            </div>
            <aside class="sidebar" style="margin-top:-5px">
                <fashion-articles/>
                <fashion-bloggers/>
                <friendly-link/>
            </aside>
        </section>
    </div>
</template>


<script>
import { WebsiteNotice, FashionArticles, FashionBloggers, FriendlyLink, NavBar, SimpleArticle } from '@/components/website'
import Pagination from '@/components/Pagination'

export default {
  name: 'index',
  components: {
      WebsiteNotice,
      FashionArticles, 
      FashionBloggers, 
      FriendlyLink, 
      NavBar,
      Pagination,
      SimpleArticle
  },
  data() {
      return {
          list: null,
          total: 5,
          listQuery: {
              classId: 0,
              page: 1,
              limit: 10
          },
          tests: [1,2,3,4,5,6]

      }
  },
  created() {     
  },
  methods: {

  },
  activated() {
  },
}
</script>
