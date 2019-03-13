<template>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <div class="row">
        <form id="pulicForm" class="add-article-form" onkeydown="if(event.keyCode==13){return false;}">

          <input type="hidden" name="articleAuthorId" id="userid">
          <input type="hidden" name="articleId" id="aid">
          <div class="col-md-9">
            <h1 class="page-header">撰写新文章</h1>
            <div class="form-group">
              <label for="article-title" class="sr-only">标题</label>
              <input type="text" id="article-title" name="articleTitle" class="form-control" placeholder="在此处输入标题" required autofocus autocomplete="off">
            </div>
            <div class="form-group">
              <label for="update-editor" class="sr-only">内容</label>
              <script id="update-editor" name="articleContent" type="text/plain"></script>
            </div>
            <div class="add-article-box">
              <h2 class="add-article-box-title"><span>关键字</span></h2>
              <div class="add-article-box-content">
                <input type="text" class="form-control" placeholder="请输入关键字" name="articleKeyword" id="articleKeyword" autocomplete="off">
                <span class="prompt-text">多个标签请用英文逗号,隔开。</span>
              </div>
            </div>

          </div>
          <div class="col-md-3">
            <h1 class="page-header">操作</h1>
            <div class="add-article-box">
              <h2 class="add-article-box-title"><span>栏目</span></h2>
              <div class="add-article-box-content">
                <ul class="category-list">
                  <li>
                    <label>
                      <input name="articleClassId" type="radio" value="1" checked="checked">
                      程序人生
                    </label>
                  </li>
                  <li>
                    <label>
                      <input name="articleClassId" type="radio" value="2">
                      编程</label>
                  </li>
                  <li>
                    <label>
                      <input name="articleClassId" type="radio" value="3">
                      前端</label>
                  </li>
                  <li>
                    <label>
                      <input name="articleClassId" type="radio" value="4">
                      数据库</label>
                  </li>
                  <li>
                    <label>
                      <input name="articleClassId" type="radio" value="5">
                      算法</label>
                  </li>
                  <li>
                    <label>
                      <input name="articleClassId" type="radio" value="6">
                      其他</label>
                  </li>
                </ul>
              </div>
            </div>

            <div class="add-article-box">
              <h2 class="add-article-box-title">
                <span>标题图片</span>
              </h2>

              <div style="float:left;height: 60px;margin-top: 10px">
                <img src="" alt="原图片" id="old-pic" style="height: 150px">
              </div>
              <div style="float:left;margin-top: 100px">
                <label>更改图片</label>
                <input type="file" id="articlePic" name="articlePic" style="width: 250px;outline: none;border:0">
              </div>
            </div>
          </div>

          <div style="text-align: center">
            <input class="btn btn-primary" onclick="updateArt()" type="button" value="提交修改"/>
          </div>

        </form>
      </div>
    </div>
</template>
