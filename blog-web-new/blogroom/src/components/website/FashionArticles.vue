<template>
    <div class="widget widget_recent_entries">
        <div class="title"><h2>时尚博文</h2></div>
            <ul id="goodarticle">
            <!--时尚博文-->
            <!-- <img src="/BlogRoom/images/loading.gif" style="width: 100% " > -->
            <li v-for="a in 10" :key="a">
                <a href='/BlogRoom/apps/article.htmll'>时尚文章标题{{a}}</a>
            </li>
            </ul>
    </div>
</template>


<script>
export default {
  name: 'FashionArticles'
}
</script>
