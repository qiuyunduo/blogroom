<template>
    <div>
        <div id="top_nav">
            <div id="top_nav_left">«<a href="index.html" title="返回网站首页">首页</a></div>
        </div>
        <div>
            <div id="main">
                <div id="logo">
                    <a href="/"><img src="@/images/logo.png" alt="博客屋 logo" style="height: 50px;width: 100px;"></a>
                </div>

                <div id="aboutus">
                    <h3>关于博客屋</h3>
                </div>

                <div id="side_left">
                    <ul id="nav_block">
                        <li><a href="/about">网站简介</a></li>
                        <li><a href="/contact" class="current">联系方式</a></li>
                    </ul>
                </div>

                <div id="side_right">
                
                    <h1>联系方式</h1>
                    <div id="content">
                        <ul id="history_block">
                            <li>
                                Email：<span id="email"><a href="mailto:contact@cnblogs.com">contact@cnblogs.com</a></span>
                            </li>
                            <li>
                                联系电话：<span id="phone_number">021-58950900（上海） 0571-88079867（杭州）</span>
                            </li>
                        </ul>
                        <!-- <script>
                            document.getElementById('phone_number').innerText = '021-' + '58950900（上海）' + ' ' + '0571-' + '8807' + '9867' + '（杭州）';
                            document.getElementById('email').innerHTML = '<a href="mailto:contact&#64;' + 'cnblogs.com">contact' + '&#64;blogroom.com</a>';
                        </script> -->
                    </div>
                </div>

                <div class="clear"></div>
            </div>
            <div id="footer">
                <a href="/about">关于博客屋</a><a href="/contact">联系我们</a>© 2004-2018<a href="/">博客屋</a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Contact'
}
</script>

<style scoped>
@import '../../style/reset.css';
@import '../../style/about.css';
</style>


