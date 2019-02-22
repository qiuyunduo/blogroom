<template>
  <div class="hello">
    {{ msg }}
    <button class="btn btn-default" value="button1" @click="changeMsg">改变信息</button>
  </div>
</template>

<script>
import { infoAll } from '@/api/user'

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: '欢迎来到邱云多的空间'
    }
  },
  methods: {
    changeMsg() {
      getUser().then(response => {
        this.msg = response.data
      }).catch(() => {
        this.msg = '出现异常'
      })
    }
  }
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
