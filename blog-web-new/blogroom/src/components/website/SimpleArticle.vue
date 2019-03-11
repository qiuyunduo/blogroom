<template>
    <!--单个文章显示组件-->
    <article class='excerpt'>
        <header>
            <slot name="statusLabel">
                
            </slot>
            <h2><a @click="readOne(id)">{{ title }}</a></h2>
        </header>

        <div class='focus'><a @click="readOne(id)">
            <!-- <img class='thumb' :src=' headPhoto' style='width: 150px;height: 100px'></a> -->
            <img class='thumb' src='@/images/001.png' style='width: 150px;height: 100px'></a>
        </div>

        <span class='note' @click="readOne(id)">
            {{  describe }}
        </span>

        <slot name="functionMenu">
            <p class='auth-span'>
                <span class='muted'>
                    <i class='fa fa-user'></i>
                    <a href='/BlogRoom/apps/userblog.html'>{{  author }}</a>
                </span> 
                <span class='muted'>
                    <i class='fa fa-clock-o'></i>
                    {{  publishTime }}
                </span>
                <span class='muted'>
                    <i class='fa fa-comments-o'></i>
                    <a target='_blank' href="/BlogRoom/apps/article.html评论">{{  comments }}评论</a>
                </span>
                <span class='muted'> 
                    <a href='javascript:;' data-action='ding' data-id='393' id='Addlike' class='action'>
                        <i class='fa fa-heart-o'></i>
                        <span class='count'>{{  thumbs }}</span>
                        喜欢
                    </a>
                </span>
            </p>
        </slot>
    </article>
</template>


<script>
export default {
  name: 'SimpleArticle',
  props: [
    'id',
    'title',
    'describe',
    'author',
    'publishTime',
    'comments',
    'thumbs'
  ],
  data() {
      return {
          
      }
  },
  created() {
    //   alert(this.id)
    //   alert(title)
    //   alert(author)
    //   alert(thumbs)
  },
  methods: {
      readOne(id) {
          this.$router.push("/article/"+id)
      }
  },
}
</script>

<style>
    @import '../../style/basestyle.css';
    @import '../../style/default.css';
</style>

