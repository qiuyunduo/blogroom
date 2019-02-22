$(function () {
    $.ajax({
        type:"GET",
        url:"/BlogRoom/article/getFashionArticle",
        dataType:"json",
        success:function (data) {
            $("#goodarticle").empty();
            for(var j = 0; j < data.length; j++){
                var goodarticle ="<li>"+
                    "<a href='/BlogRoom/apps/article.html?aid="+
                    data[j].articleId+
                    "'>"+data[j].articleTitle +"</a>"+
                    "</li>";
                $("#goodarticle").append(goodarticle);
            }

        },
        error:function () {
            alert("ajax请求错误");
        }
    });

    $.ajax({
        type:"GET",
        url:"/BlogRoom/user/getFashionUsers",
        dataType:"json",
        success:function (data) {
            $("#goodauthor").empty();
            for(var i = 0; i < data.length; i++){
                var goodauthor ="<li>"+
                    "<a href='/BlogRoom/apps/userblog.html?uid="+
                    data[i].userId+"'><img src='/upload/userhead/"
                    + data[i].userImageUrl+"' alt='博主头像' style='height: 60px;width: 60px'/></a>"+
                    "</li>";
                $("#goodauthor").append(goodauthor);
            }
        },
        error:function () {
            alert("ajax请求错误");
        }
    });

});
