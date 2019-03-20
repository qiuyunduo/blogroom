<template>
  <div>
    <app-head ref="apphead"/>
    <app-main/>
    <app-foot/>
  </div>
</template>

<script>
import { AppHead, AppMain, AppFoot } from './components'

export default {
  name: 'Layout',
  components: {
    AppHead,
    AppMain,
    AppFoot
  },
  computed: {
   
  }
}
</script>
