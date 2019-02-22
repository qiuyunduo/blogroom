$(function () {
    getAllArticlesChecked(1);
});

function getAllArticlesChecked(pageNum) {
    $.ajax({
        url: "/BlogRoom/article/getAllChecked",
        dataType: "json",
        data: {"pageNum": pageNum},
        type: "GET",
        success: function (pagedata) {

            var data = pagedata.list;
            if (data.length > 0) {
                $("#allArticles").empty();
                var articlenode;

                for (var i = 0; i < data.length; i++) {
                    var article = data[i];
                    articlenode = "<tr id='"+article.articleId+"'>" +
                        "<td class='article-title'>"+article.articleTitle+"</td>" +
                        "<td>"+article.articleClassId+"</td>" +
                        "<td class='hidden-sm'>"+article.articleKeyword+"</td>" +
                        "<td class='hidden-sm'>"+article.commentCount+"</td>" +
                        "<td>"+article.articleTime+"</td>" +
                        "<td><a href='manage-article.html?aid="+article.articleId+"'>管理</a>" +
                        "<a style='margin-left: 5px' onclick='delArticleByAid("+article.articleId+")'>删除</a>" +
                        "</tr>";
                    $("#allArticles").append(articlenode);
                }

                $("#pages").empty();
                var pagestart = "<li class='prev-page'><a href='javascript:;' onclick='getAllArticlesChecked("+
                    pagedata.prePage +
                    ")'>上一页</a></li>";
                var PageNodes = "";
                for (var i = 1; i < pagedata.pages + 1; i++) {
                    var onePageNode;
                    if (pagedata.pageNum == i) {
                        onePageNode = "<li class='active'><a href='javascript:;' onclick='getAllArticlesChecked(" + i + ")'>" + i + "</a></li>";
                    } else {
                        onePageNode = "<li><a href='javascript:;' onclick='getAllArticlesChecked("  + i + ")'>" + i + "</a></li>";
                    }
                    PageNodes = PageNodes + onePageNode;
                }
                var pageend = "<li class='prev-page'><a href='javascript:(0);' onclick='getAllArticlesChecked(" +
                    pagedata.nextPage +
                    ")'>下一页</a></li>";
                var pagenode = pagestart + PageNodes + pageend;


                $("#pages").append(pagenode);
            }
        }
    });
}


function delArticleByAid(aid) {

    if(confirm("确认删除此篇文章吗？")){
        $.ajax({
            url:"/BlogRoom/article/deleteOneByAid",
            data:{"aid":aid},
            type:"POST",
            success:function (rs) {
                if (rs > 0) {
                    alert("删除成功");
                    $("#"+aid).css("display","none");
                } else {
                    alert("删除失败");
                }
            },
            error:function () {
                alert("ajax请求错误");
            }
        });
    }


}


