<template>
    <div data-v-357a65ed="" class="view-container" id="main_content" style="margin-top: 10px;min-height: 800px;">
        <div data-v-0c56b7f6="" data-v-357a65ed="" class="content view">
            <div data-v-0d738edb="" class="right_cont" style="width: 900px;padding-top: 20px;border-bottom: 1px solid darkgrey">
                <h3 data-v-0d738edb="" class="title" style="font-size: 26px;border: none">账户管理</h3>

            </div>

            <div style="width: 900px">
                <div>
                    <div class="tab-pane active">
                        <form id="password_edit" method="post">
                            <input type="hidden" id="myoldpwd">
                            <div class="form-group" style="height: 60px;margin-top: 10px">
                                <label for="old-password" style="float: left;font-size: 16px;text-align: right;width: 150px">旧密码：</label>
                                <div class="col-xs-5">
                                    <input id="old-password" type="password" class="form-control old-password">
                                </div>
                            </div>
                            <div class="form-group" style="height: 60px;margin-top: 10px">
                                <label for="new-password" style="float: left;font-size: 16px;text-align: right;width: 150px">新密码：</label>
                                <div class="col-xs-5">
                                    <input id="new-password" type="password" class="form-control new-password main-password">
                                </div>
                            </div>
                            <div class="form-group" style="height: 60px;margin-top: 10px">
                                <label for="re-password" style="float: left;font-size: 16px;text-align: right;width: 150px">再次确认密码：</label>
                                <div class="col-xs-5">
                                    <input id="re-password" type="password">
                                </div>
                            </div>
                            <div class="form-group" style="height: 34px">
                                <div style="padding-left: 200px">
                                    <input class="btn btn-primary btn-lg" id="updatePwdBtn" value="确认修改" style="width: 100px"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Security',
}
</script>

<style scoped>
    @import '../../style/user001.css';
    @import '../../style/user002.css';
</style>
