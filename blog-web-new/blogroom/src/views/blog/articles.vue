<template>
    <main>
        <div class="filter-box d-flex align-items-center">
            <form action="https://blog.csdn.net/qydcsdn" id="seeOriginal">
                <label class="chk-box" for="chkOriginal">
                    <input  type="hidden" name="t" value="1" id="chkOriginal">
                </label>
            </form>
            <dl class="filter-sort-box d-flex align-items-center">
                <dt>排序：</dt>
                <dd><a href="javascript:void(0);" class="btn-filter-sort active" target="_self">默认</a></dd>
                <dd><a href="https://blog.csdn.net/qydcsdn?orderby=UpdateTime" class="btn-filter-sort " target="_self">按更新时间</a></dd>
                <dd><a href="https://blog.csdn.net/qydcsdn?orderby=ViewCount" class="btn-filter-sort " target="_self">按访问量</a></dd>
            </dl>
        </div>


        <div class="d-flex align-items-center" style="width: 900px;">
            <div class="content-wrap">
                <div class="content">
                    <div id="blank_main" style="background: white;width: 900px;height: 500px;text-align: center;padding-top: 200px">
                        <h6>空空如也</h6>
                        <p class="remark">该博主很懒,什么文章都没有留下</p>
                    </div>
                </div>
            </div>
        </div>
    </main>
</template>

<script>
export default {
    name: 'Articles',
    data() {
        return {
            
        }
    },
}
</script>

