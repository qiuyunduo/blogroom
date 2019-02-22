$(function () {
    $.ajax({
        url:"/BlogRoom/user/getLogUser",
        dataType:"json",
        type:"POST",
        success:function (logUser) {
            $("#userid").val(logUser.userId);
            $("#log_img").attr("src","/upload/userhead/"+logUser.userImageUrl);
            $("#myblog").attr("href","/BlogRoom/apps/userblog.html?uid="+logUser.userId);
        },
        error:function () {
            alert("请求错误");
        }
    });

});

function publicArt() {
    var plaintext = UE.getEditor('editor').getContentTxt();
    var articleDescribe = plaintext.substr(0,100);

    var formData = new FormData();
    formData.append("articleimg",$("#articlePic")[0].files[0]);
    formData.append("articleAuthorId",$("#userid").val());
    formData.append("articleDescribe",articleDescribe+"...");
    formData.append("articleContent",UE.getEditor('editor').getContent());
    formData.append("articleTitle",$("#article-title").val());
    formData.append("articleClassId",$("input[name='articleClassId']:checked").val());
    formData.append("articleKeyword",$("#articleKeyword").val());
    $.ajax({
        url:"/BlogRoom/article/addArticle",
        type:"POST",
        data:formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType:"json",
        success: function (returndata) {
            if(returndata == 1){
                alert("您的文章已提交，请耐心等待管理的审核");
            }else {
                if(returndata == 2){
                    alert("标题图片上传失败");
                }else {
                    alert("文章上传失败");
                }
            }
        },
        error: function (returndata) {
            alert("ajax出现错误");
        }
    },"json");

};


