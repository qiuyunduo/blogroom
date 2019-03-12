<template>
    <div id="app" style="height:820px"><!---->
        <div data-v-357a65ed="" class="container view">
            <left-menu/>
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
import LeftMenu from './leftMenu'
export default {
    name: 'UserIndex',
    components: {
        LeftMenu,
    }
}
</script>

<style scoped>
    @import '../../style/user001.css';
    @import '../../style/user002.css';
</style>
