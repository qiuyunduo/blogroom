$(function () {
    var aid = UrlParam.param("aid");
    $("#aid").val(aid);
    UE.getEditor('update-editor').ready(function () {
        $.ajax({
            url:"/BlogRoom/article/getOneById",
            data:{"aid":aid},
            dataType:"json",
            type:"POST",
            success:function (data) {
                var article = data.article;
                var content = article.articleContent;
                // alert(article.articleClassId);
                // alert(article.articleKeyword);
                // alert(article.articleContent);
                // alert(article.articleTitle);
                // alert(article.articlePic);
                UE.getEditor('update-editor').setContent(content);
                $("#userid").val(article.articleAuthorId);
                $("#old-pic").attr("src","/upload/articlepic/"+article.articlePic);
                $("#article-title").val(article.articleTitle);
                $("input[name='articleClassId'][value='"+article.articleClassId+"']").attr("checked","checked");
                $("#articleKeyword").val(article.articleKeyword);

            },
            error:function () {
                alert("请求错误");
            }
        });
    });


});

function updateArt() {
    var plaintext = UE.getEditor('update-editor').getContentTxt();
    var articleDescribe = plaintext.substr(0,100);

    var formData = new FormData();
    formData.append("articleId",$("#aid").val());
    formData.append("articleimg",$("#articlePic")[0].files[0]);
    formData.append("articleAuthorId",$("#userid").val());
    formData.append("articleDescribe",articleDescribe+"...");
    formData.append("articleContent",UE.getEditor('update-editor').getContent());
    formData.append("articleTitle",$("#article-title").val());
    formData.append("articleClassId",$("input[name='articleClassId']:checked").val());
    formData.append("articleKeyword",$("#articleKeyword").val());
    $.ajax({
        url:"/BlogRoom/article/updateArticle",
        type:"POST",
        data:formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType:"json",
        success: function (returndata) {
            if(returndata == 1){
                alert("修改成功");
                window.location.reload();
            }else {
                if(returndata == 2){
                    alert("标题图片更改失败");
                }else {
                    alert("修改失败");
                }
            }
        },
        error: function () {
            alert("ajax出现错误");
        }
    },"json");

};


