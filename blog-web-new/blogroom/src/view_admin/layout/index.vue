<template>
    <div class="user-select">
        <section class="container-fluid">
            <admin-head/>

            <div class="row">
                <el-menu
                style="width: 15%;background-color:#EDEDED;position:fixed"
                background-color="#EDEDED"
                text-color="blank"
                :default-active="activeIndex"
                unique-opened
                router
                >
                <NavMenu :navMenus="menuData"></NavMenu>
                </el-menu>
                <div style="float:left;width:100%;">
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
    computed: {
        loginAdmin() {
            return this.$store.state.website.adminInfo
        }
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
                        alias: "用户管理"
                    },
                    childs: [
                        {
                            entity: {
                                id: 51,
                                name: 'user',
                                icon: "el-icon-edit",
                                alias: "用户管理",
                                value: "/admin/manage/user"
                            }
                        },
                    ]
                },
                {
                //一级
                    entity: {
                        id: 6,
                        name: 'website',
                        icon: "el-icon-message",
                        alias: "网站管理"
                    },
                //二级
                    childs: [
                        // {
                        //     entity: {
                        //         id: 7,
                        //         name: 'fashionArticle',
                        //         icon: "el-icon-edit",
                        //         alias: "时尚博文管理",
                        //         value: "/admin/manage/website/fashionBlog"
                        //     }
                        // },
                        // {
                        //     entity: {
                        //         id: 8,
                        //         name: 'edit',
                        //         icon: "el-icon-edit",
                        //         alias: "时尚博主管理",
                        //         value: { path: "/admin/manage/website/fashionBlogger" }
                        //     }
                        // },
                        {
                            entity: {
                                id: 9,
                                name: 'friendlyLink',
                                icon: "el-icon-edit",
                                alias: "友情链接",
                                value: "/admin/manage/website/friendlyLink"
                            }
                        },
                    ]
                },
                {
                //一级
                    entity: {
                        id: 10,
                        name: 'config',
                        icon: "el-icon-message",
                        alias: "积分等级管理"
                    },
                //二级
                    childs: [
                        {
                            entity: {
                                id: 11,
                                name: 'rankConfig',
                                icon: "el-icon-edit",
                                alias: "等级管理",
                                value: "/admin/manage/ranks"
                            }
                        },
                        {
                            entity: {
                                id: 12,
                                name: 'integralConfig',
                                icon: "el-icon-edit",
                                alias: "积分配置",
                                value: { path: "/admin/manage/integral/config" }
                            }
                        },
                        {
                            entity: {
                                id: 13,
                                name: 'integralLog',
                                icon: "el-icon-edit",
                                alias: "积分记录",
                                value: { path: "/admin/manage/integral/log" }
                            }
                        }
                    ]
                },
            ]
        }
    },
    mounted() {
        this.setMenu()
    },
    methods: {
        setMenu() {
            if(this.loginAdmin.status.toString() === '1'){
                return
            }
            let adminManage = {
                            entity: {
                                id: 61,
                                name: 'admin',
                                icon: "el-icon-edit",
                                alias: "管理员",
                                value: "/admin/manage/website/admin"
                            }
                        }
            this.menuData[3].childs.push(adminManage)
        }
    },

}
</script>

<style scoped>
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';
</style>
