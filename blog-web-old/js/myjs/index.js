$(function () {
    getArticlesByPage(1);
});

function getArticlesByPage(pageNum) {
    $.ajax({
        url: "http:localhost:8088/article/info/all",
        dataType: "json",
        data: {"page": pageNum},
        type: "GET",
        success: function (pagedata) {
            var data = pagedata.pagingData;
            $(".content").empty();

            var articlenode;

            for (var i = 0; i < data.item.length; i++) {
                var article = data.item[i];
                articlenode = "<article class='excerpt'>" +
                    "<header><h2><a href='article.html?aid=" +
                    article.id +
                    "'>" + article.title + "</a></h2></header>" +
                    "<div class='focus'><a href='article.html?aid=" +
                    article.id +
                    "'><img class='thumb' src='../upload/articlepic/" +
                    article.headPhoto +
                    "' style='width: 150px;height: 100px'></a></div>" +
                    "<span class='note'>" +
                    article.describe +
                    "</span> <p class='auth-span' >" +
                    "<span class='muted'><i class='fa fa-user'></i> <a href='userblog.html?uid=" +
                    article.userId +
                    "'>" +
                    article.author +
                    "</a></span> <span class='muted'><i class='fa fa-clock-o'></i>" +
                    article.publishTime +
                    "</span><span class='muted'><i class='fa fa-comments-o'></i> " +
                    "<a target='_blank' href='" +
                    "article.html?aid=" +
                    article.id +
                    "#comment'>" +
                    article.comments +
                    "评论</a>" +
                    "</span><span class='muted'> <a href='javascript:;' data-action='ding' data-id='393' id='Addlike' class='action'><i class='fa fa-heart-o'></i>" +
                    "<span class='count'>" +
                    article.thumbs +
                    "</span>喜欢</a></span></p>" +
                    "</article>";
                $(".content").append(articlenode);
            }

            var pagestart = "<div class='pagination'>" +
                "<ul>" +
                "<li class='prev-page'><a href='javascript:;' onclick='getArticlesByPage("+
                pagedata.pagingData.prePage+
                ")'>上一页</a></li>";


            var PageNodes = "";
            for(var i = 1; i <= pagedata.pagingData.totalPage; i++){
                var onePageNode;
                if(pageNum == i){
                   onePageNode = "<li class='active'><a href='javascript:;' onclick='getArticlesByPage("+i+")'>"+i+"</a></li>";
                }else {
                    onePageNode = "<li><a href='javascript:;' onclick='getArticlesByPage("+i+")'>"+i+"</a></li>";
                }
                PageNodes = PageNodes+onePageNode;
            }
            var pageend ="<li class='prev-page'><a href='javascript:(0);' onclick='getArticlesByPage("+
                pagedata.pagingData.nextPage+
                ")'>下一页</a></li>"+
                "</ul>" +
                "</div>";
            var pagenode = pagestart + PageNodes + pageend;



            $(".content").append(pagenode);
        }
    });

    $('body,html').animate({
        scrollTop: 0
    }, 100);
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


