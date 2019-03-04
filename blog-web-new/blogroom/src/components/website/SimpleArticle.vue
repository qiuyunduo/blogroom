<template>
    <!--单个文章显示组件-->
    <article class='excerpt'>
        <header>
            <h2><a @click="readOne(baseInfo.id)">{{ baseInfo.title }}</a></h2>
        </header>

        <div class='focus'><a @click="readOne(baseInfo.id)">
            <img class='thumb' :src='baseInfo.headPhoto' style='width: 150px;height: 100px'></a>
        </div>

        <span class='note' @click="readOne(baseInfo.id)">
            {{ baseInfo.describe }}
        </span>

        <p class='auth-span'>
            <span class='muted'>
                <i class='fa fa-user'></i>
                <a href='/BlogRoom/apps/userblog.html'>{{ baseInfo.author }}</a>
            </span> 
            <span class='muted'>
                <i class='fa fa-clock-o'></i>
                {{ baseInfo.publishTime }}
            </span>
            <span class='muted'>
                <i class='fa fa-comments-o'></i>
                <a target='_blank' href="/BlogRoom/apps/article.html评论">{{ baseInfo.comments }}评论</a>
            </span>
            <span class='muted'> 
                <a href='javascript:;' data-action='ding' data-id='393' id='Addlike' class='action'>
                    <i class='fa fa-heart-o'></i>
                    <span class='count'>{{ baseInfo.thumbs }}</span>
                    喜欢
                </a>
            </span>
        </p>
    </article>
</template>


<script>
export default {
  name: 'SimpleArticle',
  props: [
      'baseInfo'
  ],
  methods: {
      readOne(id) {
          this.$router.push("/article/"+id)
      }
  },
}
</script>
