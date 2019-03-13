<template>
  <div>
    <h1>撰写新文章</h1>

    <el-form class="form-wrapper padding" ref="newArticle" :model="newArticle" label-width="110px">

      <div class="myLeftDiv">
        <el-form-item label="文章标题：" >
          <el-input v-model="newArticle.title" clearable class="filter-item" style="width: 1000px;" placeholder="文章标题"/>
        </el-form-item>

        <el-form-item label="文章内容：" >
          <div id="editor" style="width:1000px;height:450px"></div>
        </el-form-item>

        <el-form-item label="文章标签：" >
        <el-input v-model="newArticle.content" clearable class="filter-item" style="width: 1000px;" placeholder="关键字，多个标签请用英文逗号；隔开"/>
        </el-form-item>
      </div>

    

      <div class="myRightDiv">
          <el-form-item style="margin-top:270px;width:500px;height:100px">
            <label class="el-form-item__label" style="width: 110px;float: none;display:inline">文章栏目：</label>
            <el-select v-model="newArticle.classId" style="width: 300px;margin-top:-40px" placeholder="文章分类">
              <el-option v-for="index in mapLength" :key="index" :label="classMap[index-1].name" :value="classMap[index-1].id"/>
              <!-- <el-option v-for="index in classMap" :key="index" :label="index.name" :value="index.id"/> -->
            </el-select>
          </el-form-item>

          <el-form-item style="width:500px;height:100px">
            <label class="el-form-item__label" style="margin-left: -300px;width: 110px;float: none;display:inline;text-align:left">文章栏目：</label>
            <el-upload
            style="width: 300px;margin-top:-40px"
            class="avatar-uploader"
            :action="uploadPath"
            accept=".jpg,.jpeg,.png,.gif"
            :show-file-list="false"
            
            :on-success="onSuccessUpload"
            :on-remove="onHandleRemove">
              <img v-if="newArticle.headPhoto" :src="newArticle.headPhoto" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon el-upload"/>
            </el-upload>
          </el-form-item>

          <el-form-item style="margin-left: 50px;margin-top:450px">
            <el-button type="danger" @click="save(0)">保存为草稿</el-button>
            <el-button type="danger" @click="save(1)">发布博客</el-button>
            <el-button @click="goBack()">返回</el-button>
          </el-form-item>
      </div>
         </el-form>

  </div>
</template>

<script> 
import { uploadPath } from '@/api/storage'
import { createOne } from '@/api/article'
import { getAllClass } from '@/api/articleClass'
export default {
    name: 'Create',
    data() {
        return {
          classMap: null,
          mapLength: 0,
          editor: undefined,
          uploadPath,
          newArticle: {
            userId: undefined,
            classId: undefined,
            headPhoto: undefined,
            title: '',
            describe: '',
            content: '',
            status: ''
          }
        }
    },
    mounted() {
      this.getClassMap()
      UE.delEditor('editor');
      this.editor = window.UE.getEditor('editor');
      // UE.getEditor('editor').render('editor');
    },
    methods: {
      getClassMap() {
        getAllClass().then(response => {
            this.classMap = response.data.data
            this.mapLength = this.classMap.length
        }).catch(() => {
            this.$notify.error({
                title: '异常',
                message: '获取文章类别出错'
            })
        })
      },
      onHandleRemove() {

      },
      onSuccessUpload(response, file, fileList) {
        this.newArticle.headPhoto = response.data
      },
      save(status) {
        this.newArticle.status = status
        createOne(this.newArticle).then(response => {

        }).catch(() => {
          this.$notify.error({
            title: '异常',
            message: '保存出现异常'
          })
        })
      },
      goBack() {
        this.$router.go(-1)
      },
    },
    // destroyed() {
    //   this.editor.destroy()
    // },
}
</script>

<style scoped>
.myLeftDiv{
  width:500px;
  height: 805px;
  margin-left:200px
}
.myRightDiv{
  width:500px;
  height: 773px;
  margin-left: 1300px;
  margin-top: -1070px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 145px;
  height: 145px;
  display: block;
}
</style>


