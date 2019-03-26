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
import { visit } from '@/api/blog'

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
    mounted() {
        this.id = this.$route.params.id
        this.addVisit()
    },
    methods: {
        addVisit() {
            visit(this.id).then(res => {}).catch(() => {})
        }
    },
    activated() {
    },
    }
</script>
