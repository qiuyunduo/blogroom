<template>
  <div class="hello">
    {{ msg }}
    <button class="btn btn-default" value="button1" @click="changeMsg">改变信息</button>
  </div>
</template>

<script>
import { allArticles } from '@/api/article'

export default {
  name: 'index',
  data () {
    return {
      msg: '欢迎来到邱云多的空间',
      queryList: {
        page: 1,
        limit: 10
      }
    }
  },
  methods: {
    changeMsg() {
      allArticles(this.queryList).then(response => {
        this.msg = response.data.pagingData.item
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
