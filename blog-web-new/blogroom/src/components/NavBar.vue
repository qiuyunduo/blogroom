<template>
    <div id="nav-header" class="navbar">
        <ul class="nav">
            <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home"><a href="/BlogRoom/apps/index.html">首页</a></li>
            <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(1,1)">程序人生</a></li>
            <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(2,1)">编程</a></li>
            <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(3,1)">前端</a></li>
            <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(4,1)">数据库</a></li>
            <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(5,1)">算法</a></li>
            <li class="menu-item menu-item-type-taxonomy menu-item-object-category"><a href="javascript:;" onclick="getArticlesOfClass(6,1)">其他</a></li>
            <li style="float:left;margin-top:10px;margin-left:300px; "><input style="width:230px; height:25px" type="text" name="searchInput" placeholder="请输入搜索关键词"></li>
            <li style="float:right;">
                <div class="toggle-search active" style="width: 50px;">
                    <a id="searchA">
                        <i class="fa fa-search fa-lg"></i>
                    </a>
                </div>
                <!-- <div class="search-expand" style="display: block;">
                    <div class="search-expand-inner">
                        <form method="get" class="searchform themeform" onkeydown="if(event.keyCode==13)return false;">
                            <div>
                                <input type="text" id="keywords" value="" class="search" name="s" placeholder="search...">
                            </div>
                        </form>
                    </div>
                </div> -->
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'NavBar'
}
</script>

