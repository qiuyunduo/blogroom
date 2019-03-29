<template>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <div class="row">
        <el-form class="form-wrapper padding" ref="newArticle" :rules="rules" :model="newArticle">
        <form id="pulicForm" class="add-article-form" onkeydown="if(event.keyCode==13){return false;}">

          <input type="hidden" name="articleAuthorId" id="userid">
          <input type="hidden" name="articleId" id="aid">
          <div class="col-md-9">
            <h1 class="page-header">文章管理</h1>
            <div class="form-group">
              <label for="article-title" class="sr-only">标题</label>
              <el-form-item prop="title">
                <el-input v-model="newArticle.title" clearable class="filter-item" style="width: 970px;" placeholder="文章标题" autofocus/>
              </el-form-item>
            </div>
            <div class="form-group">
              <label for="update-editor" class="sr-only">内容</label>
              <div id="editor" style="height: 500px"></div>
            </div>
            <div class="add-article-box">
              <h2 class="add-article-box-title"><span>文章标签</span></h2>
              <div class="add-article-box-content" style="height:80px">
                <el-form-item prop="keyword">
                  <el-input v-model="newArticle.keyword" clearable class="filter-item" style="width: 950px;" placeholder="关键字，多个标签请用英文逗号；隔开"/>
                </el-form-item>
              </div>
            </div>

          </div>
          <div class="col-md-3">
            <h1 class="page-header">操作</h1>
            <div>
              <h2 class="add-article-box-title"><span>栏目</span></h2>
                
                <el-form-item style="width:500px;height:100px" prop="classId">
                  <el-select v-model="newArticle.classId" style="width: 300px" placeholder="文章分类">
                    <el-option v-for="index in mapLength" :key="index" :label="classMap[index-1].name" :value="classMap[index-1].id" style="padding-left:20px"/>
                  </el-select>
                </el-form-item>
            </div>

            <div>
              <h2 class="add-article-box-title">
                <span>标题图片</span>
              </h2>
              <el-form-item style="width:300px;height:100px"  prop="headPhoto">
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
            </div>

            <div>
              <el-form-item style="margin-left: 20px;margin-top:400px">
                <el-button v-show="newArticle.status === 1" style="width: 80px;border-radius:4px" @click="save(2)">同意发布</el-button>
                <el-button v-show="newArticle.status !== undefined" style="width: 80px;border-radius:4px;" @click="save()">保存修改</el-button>
                <el-button style="width: 80px;border-radius:4px" @click="goback()">返回</el-button>
              </el-form-item>
            </div>
          </div>
        </form>
        </el-form>
      </div>
    </div>
</template>

<script>
import { addIntegral } from '@/api/integral'
import { uploadPath } from '@/api/storage'
import { readOne, updateOne } from '@/api/article'
import { getAllClass } from '@/api/articleClass'
import { Message, MessageBox } from 'element-ui'

export default {
  name: 'Index',
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
        status: undefined
      },
      rules: {
        title: [
          { required: true, message: '标题不能为空', trigger: 'blur' },
          { min: 3, max: 1000, message: '长度在 3 到 1000 个字符', trigger: 'blur' }
        ],
        keyword:  { required: true, message: '请输入文章关键词', trigger: 'blur' }
      },
      integralData: {
        userId: undefined,
        type: 1,
        articleId: undefined
      }
    }
  },
  mounted() {
      this.init()
  },
  methods: {
    init() {
      this.getClassMap()
      let id = this.$route.query.id
      if( id !== undefined && id !== null) {
        readOne(id).then(res => {
          this.newArticle　= res.data.data
          let that = this
          UE.delEditor('editor');
          this.editor= window.UE.getEditor('editor',{onready:function(){
            this.setContent(that.newArticle.content)
          }});
          console.log(this.newArticle)
        }).catch(res => {
          alert("获取文章出错")
        })
      } else {
        UE.delEditor('editor');
        this.editor= window.UE.getEditor('editor')
      }
    },
    getClassMap() {
      getAllClass().then(response => {
        this.classMap = response.data.data
        this.mapLength = this.classMap.length
      }).catch(() => {
        console.error("获取文章类别出错")
      })
    },
    onHandleRemove() {

    },
    onSuccessUpload(response, file, fileList) {
      this.newArticle.headPhoto = response.data
    },
    save(status) {
      if(status !== undefined) {
        
      }
      this.$refs['newArticle'].validate((valid) => {
        if(valid) {
          this.newArticle.introduction = this.editor.getContentTxt().replace(/^\s*|\s*$/g,"").substr(0,100)
          this.newArticle.content = this.editor.getContent()
          if(this.checkField()) {
            let now = this.timeFormate(new Date)
            if(status === 2) {
              this.$confirm('确定同意 “' + this.newArticle.title+'”的发布','提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.newArticle.status = status
                this.integralData.articleId = this.newArticle.id
                this.integralData.userId = this.newArticle.userId
                addIntegral(this.integralData).then(res => {
                  Message({
                    message: "文章已发布: "+now,
                    type: 'success',
                    duration: 2 * 1000
                  })
                }).catch(() => { console.error("增加积分出错")　})
                this.updateArticle()
              }).catch(() => {})
            } else {
              this.updateArticle()
            }
          }
        } else {
          console.error('字段校验出错')
        }
      })
    },
    updateArticle(){
      updateOne(this.newArticle).then(response => {
        let article = response.data.data
        if(status === undefined) {
          Message({
            message: "文章更新已保存: "+now,
            type: 'success',
            duration: 1 * 1000
          })
        }
      }).catch(() => {
        this.$notify.error({
          title: '异常',
          message: '更新文章出现异常'
        })
      })
    },
    // 获取当前时间函数
    timeFormate(timeStamp) {
      let year = new Date(timeStamp).getFullYear()
      let month =new Date(timeStamp).getMonth() + 1 < 10? "0" + (new Date(timeStamp).getMonth() + 1): new Date(timeStamp).getMonth() + 1
      let date =new Date(timeStamp).getDate() < 10? "0" + new Date(timeStamp).getDate(): new Date(timeStamp).getDate()
      let hh =new Date(timeStamp).getHours() < 10? "0" + new Date(timeStamp).getHours(): new Date(timeStamp).getHours()
      let mm =new Date(timeStamp).getMinutes() < 10? "0" + new Date(timeStamp).getMinutes(): new Date(timeStamp).getMinutes()
      let ss =new Date(timeStamp).getSeconds() < 10? "0" + new Date(timeStamp).getSeconds(): new Date(timeStamp).getSeconds()
      return year + "-" + month + "-" + date +" "+" "+hh+":"+mm+":"+ ss
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
@import '../../style/adminStyle.css';
@import '../../style/bootstrap.min.css';

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