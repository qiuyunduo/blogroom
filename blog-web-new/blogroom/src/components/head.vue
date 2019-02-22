<template>
    <div>
        <div class="container-inner">
    <div class="yusi-logo">
      <a href="/BlogRoom/apps/index.html">
        <h1>
          <span class="yusi-mono">博客屋</span>
          <span class="yusi-bloger">欢迎您</span>
        </h1>
      </a>
      <div style="float: right;padding-top: 40px;font-size: 20px" id="log_reg">
        <a href="javascript:;" data-pop="login"><span style="color: white">登录</span></a>
        &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
        <a href="javascript:;" data-pop="register"><span style="color: white">注册</span></a>
      </div>

      <div style="float: right;padding-top: 40px;margin-right:80px;display: none" id="log_user">
          <img id="log_img" src="/upload/userhead/default.jpg" style="width: 30px;height: 30px;border-radius:50%">
          <i id="i_icon" class="fa fa-chevron-up" style="color:#ffffff;"></i>

        <div style="display:none;margin:5px 0;float:left;width:80px;height:150px;border:1px solid black;background-color:#51b485;position: absolute;z-index: 999" id="log_menu">
          <div ><a target="_blank" style="color: white;" href="#" id="myblog">我的博客</a></div>
          <div ><a target="_blank" style="color: white;" href="/BlogRoom/apps/userInfo.html">个人中心</a></div>
          <div><a class="#" target="_blank" style="color: white;">消息<span class="toolbar-newsL" style="margin-left: 4px; display: none;">(2)</span></a></div>
          <div><a target="_blank" style="color: white;">反馈</a></div>
          <div><a target="_blank" style="color: white;">帮助</a></div>
          <div><a style="color: white;" href="/BlogRoom/user/quitLog">退出</a></div>
        </div>
      </div>

    </div>
  </div>

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

    </div>
</template>
<script>
export default {
    
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
