<template>
    <div class="widget widget_meta"><div class="title"><h2>友情链接</h2></div>
        <ul>
            <li v-for="index in linkSize" :key="index"><a :href="linkList[index-1].url">{{linkList[index-1].content}}</a></li>
            <!-- <li><a href="#">思衡网络</a></li>
            <li><a href="http://www.welcom212.com/wordpress/wp-login.php">3DST技术网</a></li>
            <li><a href="#">文章<abbr title="Really Simple Syndication">×××个人博客</abbr></a></li>
            <li><a href="#">评论<abbr title="Really Simple Syndication">RSS</abbr></a></li> -->
            <li>
                <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare">
                    <a class="bds_tsina"></a>
                    <a class="bds_qzone"></a>
                    <a class="bds_tqq"></a>
                    <a class="bds_renren"></a>
                    <span class="bds_more"></span>
                    <a class="shareCount"></a>
                </div>
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
</template>


<script>
import { all } from '@/api/friendlyLink'
export default {
  name: 'FriendlyLink',
  data() {
      return {
          linkList: null,
          linkSize: 0
      }
  },
  mounted() {
    //   this.linkList = [{url: '123',content: '123'},{url: '123',content: '123'},{url: '123',content: '123'},{url: '123',content: '123'},{url: '123',content: '123'}]
    //   this.linkSize = 5
      this.getAllLinks()
  },
  methods: {
      getAllLinks() {
          all().then(response => {
              this.linkList = response.data.data
              this.linkSize = this.linkList.length
          }).catch(() => {
            //   this.$notify.error({
            //       title: '异常',
            //       message: '友情链接获取出错'
            //   })
          })
      }
  },
}
</script>
