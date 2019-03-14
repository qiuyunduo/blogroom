<template>
    <div class="user-select">
        <section class="container-fluid">
            <admin-head/>

            <div class="row">
                <el-menu
                style="width: 300px;height:800px;float:left;background-color:#EDEDED"
                background-color="#EDEDED"
                text-color="blank"
                :default-active="activeIndex"
                router
                >
                <NavMenu :navMenus="menuData"></NavMenu>
                </el-menu>
                <div style="float:left;width:1600px;margin-left:-200px">
                <router-view/>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
import NavMenu from './navMenu'
import AdminHead from './AdminHead'
export default {
    name: 'AdminIndex',
    components: {
        AdminHead,
        NavMenu
    },
    data() {
        return {
            activeIndex: 'index',
            menuData: [
                {
                //一级
                    entity: {
                        id: 0,
                        name: 'idnex',
                        icon: "el-icon-message",
                        value: '/admin/manage/index',
                        alias: "首页"
                    }
                },
                {
                //一级
                    entity: {
                        id: 1,
                        name: 'article',
                        icon: "el-icon-message",
                        alias: "文章管理"
                    },
                //二级
                    childs: [
                        {
                            entity: {
                                id: 2,
                                name: 'checkArticle',
                                icon: "el-icon-edit",
                                alias: "已审核",
                                value: "/admin/manage/article/check"
                            }
                        },
                        {
                            entity: {
                                id: 3,
                                name: 'uncheckArticle',
                                icon: "el-icon-edit",
                                alias: "待审核",
                                value: "/admin/manage/article/uncheck"
                            }
                        },
                        {
                            entity: {
                                id: 4,
                                name: 'edit',
                                icon: "el-icon-edit",
                                alias: "添加/编辑",
                                value: { path: "/admin/manage/article/edit" }
                            }
                        },
                    ]
                },
                {
                //一级
                    entity: {
                        id: 5,
                        name: "user",
                        icon: "el-icon-message",
                        value: '/admin/manage/user',
                        alias: "用户管理"
                    }
                },
            ]
        }
    },
}
</script>

<style scoped>
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';
</style>
