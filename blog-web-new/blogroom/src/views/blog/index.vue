<template>
    <div class="container clearfix pt0" id="mainBox">
        <base-info :id="id"/>
        <articles :id="id"/>
    </div>
</template>


<script>
import Articles from './articles'
import BaseInfo from './baseInfo'
import Pagination from '@/components/Pagination'

export default {
  name: 'index',
  components: {
      Articles,
      BaseInfo,
      Pagination
  },
  data() {
      return {
          id: undefined,

      }
  },
  created() {     
  },
  methods: {

  },
  activated() {
  },
}
</script>
