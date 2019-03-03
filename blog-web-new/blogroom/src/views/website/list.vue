<template>
<div>
  <div id="nav-header" class="navbar">

      <ul class="nav">
        <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home"><a href="/BlogRoom/apps/index.html">首页</a></li>
        <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(1,1)">程序人生</a></li>
        <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(2,1)">编程</a></li>
        <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(3,1)">前端</a></li>
        <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(4,1)">数据库</a></li>
        <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(5,1)">算法</a></li>
        <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(6,1)">其他</a></li>

        <li style="float:right;">
          <div class="toggle-search active" style="width: 50px;"><a id="searchA"><i class="fa fa-search fa-lg"></i></a></div>
          <div class="search-expand" style="display: block;">
            <div class="search-expand-inner">
              <form method="get" class="searchform themeform" onkeydown="if(event.keyCode==13)return false;">
                <div>
                  <input type="text" id="keywords" value="" class="search" name="s" placeholder="search...">
                </div>
              </form>
            </div>
          </div>
        </li>
      </ul>
    </div>
    X
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

            <div class='pagination'>
            <ul>
                <li class='prev-page'>
                <a href='javascript:;'>上一页</a>
                </li>
                <li class='active' v-for="b in 10" :key="b">
                <a href='javascript:;'>{{b}}</a>
                </li>   
                <li class='prev-page'>
                <a href='javascript:(0);'>下一页</a>
                </li>
            </ul>
            </div>
        </div>
        </div>


        <aside class="sidebar">

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

        <div class="widget widget_archive">
            <div class="title"><h2>时尚博主</h2></div>
            <ul id="goodauthor">
            <!--时尚博主-->
            <!-- <img src="/BlogRoom/images/loading.gif" style="width: 100% "> -->
            <li v-for="a in 10" :key="a">
                <a href='/BlogRoom/apps/article.htmll'>
                <img src='/upload/userhead' alt='博主头像' style='height: 60px;width: 60px'/>
                </a>
            </li>
            </ul>
        </div>



        <div class="widget widget_meta"><div class="title"><h2>友情链接</h2></div>
            <ul>
            <li><a href="#">思衡网络</a></li>
            <li><a href="http://www.welcom212.com/wordpress/wp-login.php">3DST技术网</a></li>
            <li><a href="#">文章<abbr title="Really Simple Syndication">×××个人博客</abbr></a></li>
            <li><a href="#">评论<abbr title="Really Simple Syndication">RSS</abbr></a></li>
            <li>
            <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a class="shareCount"></a></div>
            <!-- <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script>
            <script type="text/javascript" id="bdshell_js"></script>
            <script type="text/javascript">
                document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
            </script> -->
            <!-- Baidu Button END -->
            <a href="/" class="weixin"> </a>
            </li>
            </ul>
        </div>
        </aside>
    </section>
  </div>
</template>


<script>
export default {
  name: 'list'
}
</script>
