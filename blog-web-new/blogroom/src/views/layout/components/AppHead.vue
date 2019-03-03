<template>
  <header id="header" class="header">
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
</header>
</template>
<script>
export default {
  name: 'AppHead'
}
</script>
