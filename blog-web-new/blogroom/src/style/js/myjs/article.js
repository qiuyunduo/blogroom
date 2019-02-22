$(function () {
    var aid = UrlParam.param("aid");
    $("#commentArticleId").val(aid);
    var log_status = 0;
    //检查用户是否处于登录状态
    $.ajax({
        url:"/BlogRoom/user/checkLog",
        dataType:"json",
        type:"POST",
        success:function (data) {
            if(data != null) {
                log_status = 1;
                $("#addCommentBtn").attr("onclick","AddComment("+aid+")");
                $("#commentAuthorId").val(data.userId);
                $("#log_reg").css("display", "none");
                $("#log_user").css("display", "block");
                $("#log_img").attr("src","/upload/userhead/"+data.userImageUrl);
                $("#myblog").attr("href","/BlogRoom/apps/userblog.html?uid="+data.userId);
            }
        },
        error:function () {
            alert("请求错误");
        }
    },"json");

    getArticleDetail(aid);

    getComments(aid);

    $("#addCommentBtn").click(function () {
        var comment = $.trim($("#comment").val());
        if(comment != "") {
            if (log_status == 0) {
                $("#login").fadeIn(200, function () {
                    $("body").css("overflow", "hidden");
                    $(this).children(".pop-wp").animate({
                        left: "50%"
                    }, 200);
                });
            } else {
                $.ajax({
                    url: "/BlogRoom/comment/addComment",
                    data: $("#commentform").serialize(),
                    type: "POST",
                    dataType: "json",
                    success: function (rs) {
                        if (rs > 0) {
                            getComments(aid);
                            alert("提交成功");
                            $("#comment").val("");
                        } else {
                            alert("提交失败");
                        }
                    },
                    error: function () {
                        alert("提交评论的ajax出现错误");
                    }
                }, "json");
            }
        }else {
            alert("请输入评论内容");
        }
    });



});

//获得文章，作者两个对象
function getArticleDetail(aid) {
    $.ajax({
        url:"/BlogRoom/article/getOneById",
        dataType:"json",
        data:{"aid":aid},
        type:"POST",
        success:function (data) {
            var article = data.article;
            var author = data.author;

            var likenode = "<div class='article-social'>"+
                "<a href='javascript:;' data-action='ding' data-id='393' id='Addlike' class='action'>" +
                "<i class='fa fa-heart-o'></i>喜欢 (<span class='likecounts'>"+article.thumbsCount+"</span>)</a></div>";

            $("#article_title").html(article.articleTitle);
            $("#article_time").html(article.articleTime);
            // alert(article.commentCount);
            $("#comment_count").html(article.commentCount);
            $("#article_keyword").html(article.articleKeyword);
            $("#article_author").html(author.userName);
            $("#article_author").attr("href","/BlogRoom/apps/userblog.html?uid="+author.userId);
            $("#article_content").html(article.articleContent);
            $("#article_content").append(likenode);
        },
        error:function () {
            alert("请求错误");
        }
    },"json");
}

//获得评论，评论者两个对象集合
function getComments(aid) {
    $.ajax({
        url:"/BlogRoom/comment/getCommentsByAId",
        dataType:"json",
        data:{"aid":aid},
        type:"POST",
        success:function (data) {
            if(data.length > 0){
                $("#comment-content").empty();
                $("#comments").empty();
                $("#comments").append("<b> ("+data.length+")</b>个小伙伴在吐槽");
                for(var i = 0; i < data.length; i++) {
                    var comment = data[i].comment;
                    var commentor = data[i].commentor;
                    var commentNode = "<li class='comment odd alt thread-odd thread-alt depth-1'>" +
                        "<div class='c-avatar'><a target='_blank' href='/BlogRoom/apps/userblog.html?uid=" +
                        commentor.userId+"'><img class='avatar avatar-54 photo avatar-default' height='54' width='54'" +
                        " src='/upload/userhead/"+commentor.userImageUrl+"' style='display: block;'></a>" +
                        "<div class='c-main'>"+comment.commentContent+"<div class='c-meta'>" +
                        "<span class='c-author'>"+commentor.userName+"</span>" +
                        comment.commentDate+"</div></div></div></li>";
                    $("#comment-content").append(commentNode);
                }
            }else {
                $("#comments").append("还没有任何评论");
            }



        },
        error:function () {
            alert("请求错误");
        }
    },"json");
}


function getArticlesOfClass(classid,pageName) {
    $.ajax({
        url: "/BlogRoom/article/getAllOfClass",
        dataType: "json",
        data:{"classid":classid,"pageNum":pageName},
        type: "GET",
        success: function (pagedata) {
            var data = pagedata.list;
            $(".content").empty();

            var articlenode;

            for (var i = 0; i < data.length; i++) {
                var article = data[i].article;
                var author = data[i].author;
                articlenode = "<article class='excerpt'>" +
                    "<header><h2><a href='article.html?aid=" +
                    article.articleId +
                    "'>" + article.articleTitle + "</a></h2></header>" +
                    "<div class='focus'><a href='article.html?aid=" +
                    article.articleId +
                    "'><img class='thumb' src='/upload/articlepic/" +
                    article.articlePic +
                    "' style='width: 150px;height: 100px'></a></div>" +
                    "<span class='note'>" +
                    article.articleDescribe +
                    "</span> <p class='auth-span' >" +
                    "<span class='muted'><i class='fa fa-user'></i> <a href='/BlogRoom/apps/userblog.html?uid=" +
                    author.userId +
                    "'>" +
                    author.userName +
                    "</a></span> <span class='muted'><i class='fa fa-clock-o'></i>" +
                    article.articleTime +
                    "</span><span class='muted'><i class='fa fa-comments-o'></i> " +
                    "<a target='_blank' href='" +
                    "/BlogRoom/apps/article.html?aid=" +
                    article.articleId +
                    "#comment'>" +
                    article.commentCount +
                    "评论</a>" +
                    "</span><span class='muted'> <a href='javascript:;' data-action='ding' data-id='393' id='Addlike' class='action'><i class='fa fa-heart-o'></i>" +
                    "<span class='count'>" +
                    article.thumbsCount +
                    "</span>喜欢</a></span></p>" +
                    "</article>";
                $(".content").append(articlenode);
            }

            var pagestart = "<div class='pagination'>" +
                "<ul>" +
                "<li class='prev-page'><a href='javascript:;' onclick='getArticlesOfClass("+
                classid+","+pagedata.prePage+
                ")'>上一页</a></li>";
            var PageNodes = "";
            for(var i = 1; i < pagedata.pages+1; i++){
                var onePageNode;
                if(pagedata.pageNum == i){
                    onePageNode = "<li class='active'><a href='javascript:;' onclick='getArticlesOfClass("+classid+","+i+")'>"+i+"</a></li>";
                }else {
                    onePageNode = "<li><a href='javascript:;' onclick='getArticlesOfClass("+classid+","+i+")'>"+i+"</a></li>";
                }
                PageNodes = PageNodes+onePageNode;
            }
            var pageend ="<li class='prev-page'><a href='javascript:(0);' onclick='getArticlesOfClass("+
                classid+","+pagedata.nextPage+
                ")'>下一页</a></li>"+
                "</ul>" +
                "</div>";
            var pagenode = pagestart + PageNodes + pageend;



            $(".content").append(pagenode);
        }
    });
}



