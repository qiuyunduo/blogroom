<template>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <h1 class="page-header">信息总览</h1>
      <div class="row placeholders">
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>文章</h4>
          <span class="text-muted">15 条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>评论</h4>
          <span class="text-muted">28 条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>友链</h4>
          <span class="text-muted">4 条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>访问量</h4>
          <span class="text-muted">12312</span> </div>
      </div>
      <h1 class="page-header">状态</h1>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <tbody>
            <tr>
              <td>登录者: <span>admin</span>，这是您第 <span>13</span> 次登录</td>
            </tr>
            <tr>
              <td>上次登录时间: 2016-01-08 15:50:28 , 上次登录IP: ::1:55570</td>
            </tr>
          </tbody>
        </table>
      </div>
      <h1 class="page-header">系统信息</h1>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <thead>
            <tr> </tr>
          </thead>
          <tbody>
            <tr>
              <td>管理员个数:</td>
              <td>1 人</td>
              <td>服务器软件:</td>
              <td>Springboot内嵌Apache Tomcat/8.5.32</td>
            </tr>
            <tr>
              <td>浏览器:</td>
              <td>Chrome</td>
              <td>Springboot版本:</td>
              <td>2.0.4.RELEASE</td>
            </tr>
            <tr>
              <td>操作系统:</td>
              <td>Linux</td>
              <td>JAVA运行方式:</td>
              <td>CGI-FCGI</td>
            </tr>
            <tr>
              <td>登录者IP:</td>
              <td>192.168.1.167</td>
              <td>MYSQL版本:</td>
              <td>5.7.25</td>
            </tr>
            <tr>
              <td>程序版本:</td>
              <td class="version">BlogRoom 1.0 <font size="-6" color="#BBB">(20190327)</font></td>
              <td>上传文件:</td>
              <td>可以 <font size="-6" color="#BBB">(最大文件：2M )</font></td>
            </tr>
            <tr>
              <td>程序编码:</td>
              <td>UTF-8</td>
              <td>当前时间:</td>
              <td>{{ nowTime }}</td>
            </tr>
          </tbody>
          <tfoot>
            <tr></tr>
          </tfoot>
        </table>
      </div>
      <footer>
        <h1 class="page-header">程序信息</h1>
        <div class="table-responsive">
        <table class="table table-striped table-hover">
          <tbody>
            <tr>
              <td><span style="display:inline-block; width:8em">版权所有</span> POWERED BY WY ALL RIGHTS RESERVED</td>
            </tr>
            <tr>
              <td><span style="display:inline-block;width:8em">页面加载时间</span> PROCESSED IN 1.0835s  SECONDS</td>
            </tr>
          </tbody>
        </table>
        </div>
      </footer>
    </div>
</template>

<script>

export default {
    name: 'index',
    data() {
      return {
        timer: null,
        nowTime: undefined,
      }
    },
    mounted() {
      this.nowTimes()
    },
    methods: {
      // 获取当前时间函数
      timeFormate(timeStamp) {
          let year = new Date(timeStamp).getFullYear()
          let month =new Date(timeStamp).getMonth() + 1 < 10? "0" + (new Date(timeStamp).getMonth() + 1): new Date(timeStamp).getMonth() + 1
          let date =new Date(timeStamp).getDate() < 10? "0" + new Date(timeStamp).getDate(): new Date(timeStamp).getDate()
          let hh =new Date(timeStamp).getHours() < 10? "0" + new Date(timeStamp).getHours(): new Date(timeStamp).getHours()
          let mm =new Date(timeStamp).getMinutes() < 10? "0" + new Date(timeStamp).getMinutes(): new Date(timeStamp).getMinutes()
          let ss =new Date(timeStamp).getSeconds() < 10? "0" + new Date(timeStamp).getSeconds(): new Date(timeStamp).getSeconds()
          this.nowTime = year + "-" + month + "-" + date +" "+" "+hh+":"+mm+":"+ ss
        },
        // 定时器函数
        nowTimes(){
          let _this = this;
          this.timeFormate(new Date) //声明一个变量指向vue实例this,保证作用域一致
          this.timer = setInterval(function() {
            _this.timeFormate(new Date)
          }, 1000)
        }
    },
    beforeDestroy () {
      if(this.timer) {
        clearInterval(this.timer);//在vue实例销毁钱，清除我们的定时器
      }
    }
}
</script>


<style scoped>
@import '../style/adminStyle.css';
@import '../style/bootstrap.min.css';
</style>

