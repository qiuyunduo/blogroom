<template>
    <!--单个文章显示组件-->
    <article class='excerpt'>
        <header>
            <slot name="statusLabel">
                
            </slot>
            <h2><a :href="'/article/'+id" target="_blank">{{ title }}</a></h2>
        </header>

        <div class='focus'><a :href="'/article/'+id" target="_blank">
            <img class='thumb' :src='headPhoto' style='width: 150px;height: 100px'></a>
            <!-- <img class='thumb' src='@/images/001.png' style='width: 150px;height: 100px'></a> -->
        </div>

        <span class='note'>
            {{  introduction }}
        </span>

        <slot name="functionMenu">
            <p class='auth-span'>
                <span class='muted'>
                    <i class='fa fa-user'></i>
                    <a :href="'/blog/room/'+userId">{{  author }}</a>
                </span> 
                <span class='muted'>
                    <i class='fa fa-clock-o'></i>
                    {{  publishTime }}
                </span>
                <span class='muted'>
                    <i class='fa fa-comments-o'></i>
                    <a :href="'/article/'+id+'?action=comment'">{{  comments }}评论</a>
                </span>
                <span class='muted'> 
                    <a :href="'/article/'+id+'?action=thumb'" data-action='ding' data-id='393' id='Addlike' class='action' @click="toThumb(id)">
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
    'userId',
    'title',
    'headPhoto',
    'introduction',
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
  },
  methods: {
  },
}
</script>

<style>
    @import '../../style/basestyle.css';
    @import '../../style/default.css';
</style>

