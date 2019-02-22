$(function () {
    getArticlesByPage(1);
});

function getArticlesByPage(pageNum) {
    // alert(pageNum);
    $.ajax({
        url: "/BlogRoom/article/getAll",
        dataType: "json",
        data: {"pageNum": pageNum},
        type: "GET",
        success: function (pagedata) {
            // alert("sdsd");
            // alert(pagedata.list.length);
            // alert("当前页"+pagedata.pageNum);
            // alert("每页显示数据量"+pagedata.pageSize);
            // alert("当前页面显示数据量"+pagedata.size);
            // alert("当前页面第一个元素在数据库中的行号"+pagedata.startRow);
            // alert("当前页面最后一个元素在数据库中的行号"+pagedata.endRow);
            // alert("总记录数"+pagedata.total);
            // alert("总页数"+pagedata.pages);
            // alert("第一页"+pagedata.firstPage);
            // alert("前一页"+pagedata.prePage);
            // alert("后一页"+pagedata.nextPage);
            // alert("最后一页"+pagedata.lastPage);
            // alert("是否为第一页"+pagedata.isFirstPage);
            // alert("是否为最后一页"+pagedata.isLastPage);
            // alert("是否有前一页"+pagedata.hasPreviousPage);
            // alert("是否有下一页"+pagedata.hasNextPage);
            // alert("导航页码数"+pagedata.navigatePages);
            // alert("所有导航页号"+pagedata.navigatepageNums);
            var data = pagedata.list;
            $(".content").empty();

            var articlenode;

            for (var i = 0; i < data.length; i++) {
                var article = data[i].article;
                var author = data[i].author;
                articlenode = "<article class='excerpt'>" +
                    "<header><h2><a href='/BlogRoom/apps/article.html?aid=" +
                    article.articleId +
                    "'>" + article.articleTitle + "</a></h2></header>" +
                    "<div class='focus'><a href='/BlogRoom/apps/article.html?aid=" +
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
                "<li class='prev-page'><a href='javascript:;' onclick='getArticlesByPage("+
                pagedata.prePage+
                ")'>上一页</a></li>";
            var PageNodes = "";
            for(var i = 1; i < pagedata.pages+1; i++){
                var onePageNode;
                if(pagedata.pageNum == i){
                   onePageNode = "<li class='active'><a href='javascript:;' onclick='getArticlesByPage("+i+")'>"+i+"</a></li>";
                }else {
                    onePageNode = "<li><a href='javascript:;' onclick='getArticlesByPage("+i+")'>"+i+"</a></li>";
                }
                PageNodes = PageNodes+onePageNode;
            }
            var pageend ="<li class='prev-page'><a href='javascript:(0);' onclick='getArticlesByPage("+
                pagedata.nextPage+
                ")'>下一页</a></li>"+
                "</ul>" +
                "</div>";
            var pagenode = pagestart + PageNodes + pageend;



            $(".content").append(pagenode);
        }
    });
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


