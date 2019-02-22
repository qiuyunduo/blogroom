$(function () {
   $("#searchA").click(function () {
       var keyword = $.trim($("#keywords").val());

       if(keyword != ""){
           searchArticle(keyword,1);
       }
   }) ;
});
function searchArticle(title,pageName) {
    $.ajax({
        url: "/BlogRoom/article/getSearchRs",
        dataType: "json",
        data:{"title":title,"pageNum":pageName},
        type: "GET",
        success: function (pagedata) {
            var data = pagedata.list;
            if(data.length > 0) {
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
                    "<li class='prev-page'><a href='javascript:;' onclick='searchArticle(" +
                    title + "," + pagedata.prePage +
                    ")'>上一页</a></li>";
                var PageNodes = "";
                for (var i = 1; i < pagedata.pages + 1; i++) {
                    var onePageNode;
                    if (pagedata.pageNum == i) {
                        onePageNode = "<li class='active'><a href='javascript:;' onclick='searchArticle(" + title + "," + i + ")'>" + i + "</a></li>";
                    } else {
                        onePageNode = "<li><a href='javascript:;' onclick='searchArticle(" + title + "," + i + ")'>" + i + "</a></li>";
                    }
                    PageNodes = PageNodes + onePageNode;
                }
                var pageend = "<li class='prev-page'><a href='javascript:(0);' onclick='searchArticle(" +
                    title + "," + pagedata.nextPage +
                    ")'>下一页</a></li>" +
                    "</ul>" +
                    "</div>";
                var pagenode = pagestart + PageNodes + pageend;


                $(".content").append(pagenode);
            }else {
                alert("没有找到相关文章");
            }
        }
    });
}


