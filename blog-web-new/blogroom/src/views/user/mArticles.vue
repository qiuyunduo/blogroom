<template>
    <div data-v-357a65ed="" class="view-container" id="main_content" style="margin-top: 10px;background-color: #ededee;">
        <div data-v-0d738edb="" data-v-357a65ed="" class="cont view">
            <div data-v-0d738edb="" class="right_cont" style="background-color: white">
                <h3 data-v-0d738edb="" class="title" style="border: none">博客管理
                    <div style="float: right;font-size: 15px;padding-left:  10px">
                        <input type="radio" name="status" value="0">未审核
                    </div>
                    <div style="float: right;font-size: 15px;padding-left:  10px">
                        <input type="radio" name="status" value="1">已审核
                    </div>
                    <div style="float: right;font-size: 15px;padding-left:  10px">
                        <input type="radio" name="status" checked="checked" value="2">全部
                    </div>
                </h3>

            </div>
            <div class="content-wrap">
                <div class="content">
                    <!--所有文章和分页；-->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'mArticles',
}
</script>
