<template>
    <div class="widget widget_archive">
        <div class="title"><h2>时尚博主</h2></div>
        <ul id="goodauthor">
        <!--时尚博主-->
            <li v-for="a in 10" :key="a">
                <a href='/BlogRoom/apps/article.htmll'>
                    <img src='/upload/userhead' alt='博主头像' style='height: 60px;width: 60px'/>
                </a>
            </li>
        </ul>
    </div>
</template>


<script>
export default {
  name: 'FashionBloggers'
}
</script>
