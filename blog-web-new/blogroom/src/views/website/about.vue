<template>
    <div>
        <div id="top_nav">
            <div id="top_nav_left">«<a href="/index" title="返回网站首页">首页</a></div>
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
                        <li><a href="/about" class="current">网站简介</a></li>
                        <li><a href="/contact">联系方式</a></li>
                    </ul>
                </div>

                <div id="side_right">
        
                    <h1>博客屋简介 - 代码改变世界 Coding Changes the World</h1>
                    <div id="content">
                        <p>
                        博客屋创立于2018年8月，是一个面向开发者的知识分享社区。自创建以来，博客屋一直致力并专注于为开发者打造一个纯净的技术交流社区，推动并帮助开发者通过互联网分享知识，从而让更多开发者从中受益。博客园的使命是帮助开发者用代码改变世界。
                        </p>				
                        <p>
                        Cnblogs.com was founded in 2004. It is a knowledge-sharing community for developers. Our mission is to help developers change the world by coding.
                        </p>
                    </div>
                </div>

                <div class="clear"></div>

                <div id="footer">
                    <a href="/about">关于博客屋</a><a href="/contact">联系我们</a>© 2004-2018<a href="/">博客屋</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'About',
    mounted() {
        alert(this.$store.state.user.token)
    },
}
</script>

<style scoped>
@import '../../style/reset.css';
@import '../../style/about.css';
</style>


