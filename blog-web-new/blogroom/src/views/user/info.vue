<template>
    <div data-v-357a65ed="" class="view-container" id="main_content" style="margin-top: 10px">
        <div data-v-0d738edb="" data-v-357a65ed="" class="cont view">
            <div data-v-0d738edb="" class="right_cont">
                <h3 data-v-0d738edb="" class="title">个人资料</h3>

                <div data-v-0d738edb="" class="user_info">
                    <div data-v-0d738edb="" class="header">
                        <img data-v-0d738edb="" src="@/images/default.jpg" id="shead" class="head">
                        <p data-v-0d738edb="" class="modify"><a title="修改头像" data-toggle="modal" data-target="#updatehead">修改头像</a></p>
                    </div>

                    <!---->
                    <div data-v-0d738edb="" class="right_c">
                        <div data-v-0d738edb="" class="right_info">

                        </div>

                        <div data-v-0d738edb="" class="line"></div>

                        <div data-v-0d738edb="" class="nick">
                            <span data-v-0d738edb="" class="mod"><a title="查看或修改个人信息" data-toggle="modal" data-target="#seeUserInfo" id="updateInfo">修改资料</a></span>
                        </div>

                        <ul data-v-0d738edb="" class="self">
                            <li data-v-0d738edb="" class="comon">
                                <span data-v-0d738edb="" class="noWid">用户名：</span>
                                <span data-v-0d738edb="" class="cont" id="sname"></span>
                            </li>
                            <li data-v-0d738edb="" class="comon">
                                <span data-v-0d738edb="" class="noWid">性别：</span>
                                <span data-v-0d738edb="" class="cont" id="ssex"></span>
                            </li>
                            <li data-v-0d738edb="" class="comon">
                                <span data-v-0d738edb="" class="noWid">生日：</span>
                                <span data-v-0d738edb="" class="cont" id="sbirthday"></span>
                            </li>
                            <li data-v-0d738edb="" class="comon last">
                                <span data-v-0d738edb="" class="noWid">邮箱：</span>
                                <span data-v-0d738edb="" class="cont" id="semail"></span></li>
                            <li data-v-0d738edb="" class="intro">
                                <span data-v-0d738edb="" class="noWid">简介：</span>
                                <span data-v-0d738edb="" class="cont" id="desc">无</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'UserInfo',
}
</script>


<style>

</style>

