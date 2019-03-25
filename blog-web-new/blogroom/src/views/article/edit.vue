<template>
  <div style="padding-top:34px">
    <el-form class="form-wrapper padding" ref="newArticle" :rules="rules" :model="newArticle" label-width="110px">

      <div class="myLeftDiv">
        <el-form-item label="文章标题：" prop="title">
          <el-input v-model="newArticle.title" clearable class="filter-item" style="width: 1000px;" placeholder="文章标题"/>
        </el-form-item>

        <el-form-item label="文章内容：" >
          <div id="editor" type="text/plain" style="width:1000px;height:450px"></div>
        </el-form-item>

        <el-form-item label="文章标签：" prop="keyword">
        <el-input v-model="newArticle.keyword" clearable class="filter-item" style="width: 1000px;" placeholder="关键字，多个标签请用英文逗号；隔开"/>
        </el-form-item>
      </div>

    

      <div class="myRightDiv">
          <el-form-item style="margin-top:270px;width:500px;height:100px">
            <label class="el-form-item__label" style="text-align:left;float: none;">文章栏目：</label>
            <el-select v-model="newArticle.classId" style="width: 300px;" placeholder="文章分类">
              <el-option v-for="index in mapLength" :key="index" :label="classMap[index-1].name" :value="classMap[index-1].id"/>
            </el-select>
          </el-form-item>

          <el-form-item style="width:500px;height:100px"  prop="headPhoto">
            <label class="el-form-item__label" style="float: none;display:inline;text-align:left">标题图片：</label>
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

          <el-form-item style="margin-left: 20px;margin-top:450px">
            <el-button v-show="newArticle.status === 0" type="danger" @click="save(1)">发布博客</el-button>
            <el-button type="danger" @click="save()">保存修改</el-button>
            <el-button @click="goback()">返回</el-button>
          </el-form-item>
      </div>
    </el-form>

  </div>
</template>

<script> 
import { uploadPath } from '@/api/storage'
import { readOne, updateOne } from '@/api/article'
import { getAllClass } from '@/api/articleClass'
import { Message, MessageBox } from 'element-ui'

export default {
    name: 'Edit',
    data() {
        return {
          classMap: null,
          mapLength: 0,
          editor: undefined,
          uploadPath,
          newArticle: {
            id: undefined,
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
      // this.editor = UE.getEditor('editor');
      // UE.getEditor('editor').render('editor');
      let id = this.$route.params.articleId
      if( id !== undefined && id !== null) {
        readOne(id).then(res => {
          this.newArticle　= res.data.data
          alert(this.newArticle.status)
          let that = this
          UE.delEditor('editor');
          this.editor= window.UE.getEditor('editor',{onready:function(){
          this.setContent(that.newArticle.content)
        }});
          // this.editor.setContent(this.newArticle.content,true)
          console.log(this.newArticle)
        }).catch(res => {
          alert("获取文章出错")
        })
      }
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
        if(status !== undefined) {
          this.newArticle.status = status
        }
        this.$refs['newArticle'].validate((valid) => {
          if(valid) {
            this.newArticle.introduction = this.editor.getContentTxt().replace(/^\s*|\s*$/g,"").substr(0,100)
            this.newArticle.content = this.editor.getContent()
            if(this.checkField()) {
              updateOne(this.newArticle).then(response => {
                let article = response.data.data
                let date = new Date()
                if(status === undefined) {
                  Message({
                    message: "文章已保存: "+date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(),
                    type: 'success',
                    duration: 1 * 1000
                  })
                } else {
                  Message({
                    message: "文章已提交审核: "+date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(),
                    type: 'success',
                    duration: 2 * 1000
                  })
                  this.$router.push("/blog/user/"+this.newArticle.userId+"/mArticles")
                }
              }).catch(() => {
                this.$notify.error({
                  title: '异常',
                  message: '保存出现异常'
                })
              })
            }
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
      },
      goback() {
        this.$router.go(-1)
      }
    }
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


