<template>
  <div>
    <img src="blob:file:///home/qiuyunduo/myHome/blogroom/k6rn32ic4mixm6f7ckuz.png" style="width:300px;height:300px"/>
    <h1 class="page-header">撰写新文章</h1>

    <el-form class="form-wrapper padding" ref="addForm" :model="newArticle" label-width="110px">

    <div class="myLeftDiv">
      <el-input v-model="newArticle.title" clearable class="filter-item" style="width: 1000px;" placeholder="文章标题"/>
      <div id="editor" style="width:1000px;height:500px"></div>
      <div style="margin-top:5px">
        <!-- <label>关键词：</label> -->
        <el-input v-model="newArticle.content" clearable class="filter-item" style="width: 1000px;" placeholder="关键字，多个标签请用英文逗号；隔开"/>
      </div>
    </div>

    

    <div class="myRightDiv">
      <div>
        <!-- <label>文章栏目：</label> -->
        <el-select v-model="newArticle.classId" style="width: 500px" class="filter-item" placeholder="文章分类">
          <el-option v-for="(key, value) in classList" :key="key" :label="key" :value="value"/>
        </el-select>
      </div>
       <div style="padding: 50px;">

      <!-- <el-form-item label="文章标题图片：" prop="headPhoto"> -->
          <el-upload
          v-show="isShow"
          ref="upload"
          action="http://localhost:8088/website/storage/upload"
          accept="image/png,image/gif,image/jpg,image/jpeg"
          :limit=1
          :on-success="onSuccessUpload"
          :on-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
      <!-- </el-form-item> -->
  </div>

        <div style="margin-top:300px">
          <el-button type="danger">保存为草稿</el-button>
          <el-button type="danger">发布博客</el-button>
          <el-button>返回</el-button>
        </div>
    </div>


    </el-form>
  </div>
</template>

<script>
export default {
    name: 'Create',
    data() {
        return {
          classList: null,
          editor: null,
          isShow: true,
          newArticle: {
            userId: undefined,
            classId: undefined,
            headPhoto: undefined,
            title: '',
            describe: '',
            content: '',
          }
        }
    },
    watch: {
      imageList:'changeShow'
    },
    mounted() {
      this.classList = {1:'程序人生',2:'编程',3:'前端',4:'数据库',5:'算法',6:'其他'}
      this.editor = UE.getEditor('editor')
    },
    methods: {
      handleRemove(file) {
        alert("ssd")
        this.isHavePhoto = true
      },
      onSuccessUpload(response, file, fileList) {
        alert(response.data)
        alert(file)
        alert(fileList)
      }
    },
    destroyed() {
        this.editor.destroy()
    },
}
</script>

<style scoped>

.myLeftDiv{
  width:500px;
  height: 763px;
  margin-left:200px
}
.myRightDiv{
  width:500px;
  height: 723px;
  margin-left: 1300px;
  margin-top: -760px;
}
.disabled {
    display: none;
}
</style>


