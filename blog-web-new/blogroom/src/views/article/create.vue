<template>
  <div style="padding-top:34px">
    <el-form class="form-wrapper padding" ref="newArticle" :rules="rules" :model="newArticle" label-width="110px">

      <div class="myLeftDiv">
        <el-form-item label="文章标题：" prop="title">
          <el-input v-model="newArticle.title" clearable class="filter-item" style="width: 100%;" placeholder="文章标题"/>
        </el-form-item>

        <el-form-item label="文章内容：" >
          <div id="editor" type="text/plain" style="width:100p%;height:450px"></div>
        </el-form-item>

        <el-form-item label="文章标签：" prop="keyword">
        <el-input v-model="newArticle.keyword" clearable class="filter-item" style="width: 100%;" placeholder="关键字，多个标签请用英文逗号；隔开"/>
        </el-form-item>
      </div>

    

      <div class="myRightDiv">
          <el-form-item label="文章栏目：">
            <el-select v-model="newArticle.classId" style="" placeholder="文章分类">
              <el-option v-for="index in mapLength" :key="index" :label="classMap[index-1].name" :value="classMap[index-1].id"/>
            </el-select>
          </el-form-item>

          <el-form-item  label="标题图片：" prop="headPhoto">
            <el-upload
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

          <el-form-item style="margin-top:30%">
            <el-button type="danger" @click="save(0)">保存为草稿</el-button>
            <el-button type="danger" @click="save(1)">发布博客</el-button>
          </el-form-item>
      </div>
    </el-form>

  </div>
</template>

<script> 
import { uploadPath } from '@/api/storage'
import { readOne, createOne } from '@/api/article'
import { getAllClass } from '@/api/articleClass'
import { Message, MessageBox } from 'element-ui'

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
            keyword: '',
            title: '',
            introduction: '',
            content: '',
            status: ''
          },
          rules: {
            title: [
              { required: true, message: '标题不能为空', trigger: 'blur' },
              { min: 3, max: 1000, message: '长度在 3 到 1000 个字符', trigger: 'blur' }
            ],
            keyword:  { required: true, message: '请输入文章关键词', trigger: 'blur' },
          }
        }
    },
    computed: {
      loginUser() {
        return this.$store.state.user.userInfo
      }
    },
    mounted() {
      this.getClassMap()
      UE.delEditor('editor');
      this.editor= window.UE.getEditor('editor');
      // this.editor = UE.getEditor('editor');
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
        this.$refs['newArticle'].validate((valid) => {
          if(valid) {
            this.newArticle.status = status
            this.newArticle.introduction = this.editor.getContentTxt().replace(/^\s*|\s*$/g,"").substr(0,100)
            this.newArticle.content = this.editor.getContent()
            this.newArticle.userId = this.loginUser.id
            this.newArticle.author = this.loginUser.nickName
            if(this.checkField()) {
              createOne(this.newArticle).then(response => {
                let article = response.data.data
                this.$confirm('是否去查看刚完成的文章', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                  window.location.href = "http://localhost:9000/article/"+article.id
                }).catch(() => {
                  window.location.reload()
                })
              }).catch(() => {
                this.$notify.error({
                  title: '异常',
                  message: '保存出现异常'
                })
              })
            }

          // alert(JSON.stringify(this.newArticle))
          } else {
            console.log('字段校验出错')
          }
        })
      },
      checkField() {
        if(this.newArticle.introduction === '' || this.newArticle.introduction === undefined || this.newArticle.introduction === null){
          Message({
            message: "文章内容不能为空",
            type: 'error',
            duration: 1 * 1000
          })
          return false
        }

        if(this.newArticle.classId === undefined) {
          Message({
            message: "请选择文章类别",
            type: 'error',
            duration: 1 * 1000
          })
          return false
        }
        if(this.newArticle.headPhoto === undefined) {
          Message({
            message: "请选择文章标题图片",
            type: 'error',
            duration: 1 * 1000
          })
          return false
        }

        return true
      }
    }
}
</script>
<style scoped>
.myLeftDiv{
  width:60%;
  height: 100%;
  margin-left:5%
}
.myRightDiv{
  position:absolute;
  width:50%;
  margin-left: 65%;
  margin-top: -41%;
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
  width: 300px;
  height: 300px;
  line-height: 300px;
  text-align: center;
}
.avatar {
  width: 300px;
  height: 300px;
  display: block;
}
</style>


