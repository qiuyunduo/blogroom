$(function () {
    var uid = $("#user-id").val();

    getAllArticlesByPage(uid,1);

    $('input[type=radio][name=status]').change(function() {
        if(this.value == 2){
            getAllArticlesByPage(uid, 1);
        }else if(this.value == 1){
            getAllCheckedArticlesByPage(uid,1);
        }else {
            getAllUncheckArticlesByPage(uid,1);
        }
    });
});

function getAllArticlesByPage(uid,pageNum) {
    $.ajax({
        url: "/BlogRoom/article/getAllArticlesOfUid",
        dataType: "json",
        data: {"pageNum": pageNum,"uid":uid},
        type: "GET",
        success: function (pagedata) {

            var data = pagedata.list;
            $(".content").empty();

            var articlenode;

            for (var i = 0; i < data.length; i++) {
                var article = data[i];
                if(article.articleStatus > 0) {
                    articlenode = "<article class='excerpt' style='width: 938px' id='"+article.articleId+"'>" +
                        "<header><span class='label label-important'>已审核<i class='label-arrow'></i></span><h2><span style='color: #3fb27e;'>" + article.articleTitle + "</span></h2></header>" +
                        "<div class='focus'><span><img class='thumb' src='/upload/articlepic/" +
                        article.articlePic +
                        "' style='width: 150px;height: 100px'></span></div>" +
                        "<span class='note'>" +
                        article.articleDescribe +
                        "</span> <p class='auth-span' >" +
                        "<span class='muted'><i class='fa fa-clock-o'></i>" +
                        article.articleTime +
                        "</span><span class='muted'><i class='fa fa-edit'></i> " +
                        "<a target='_blank' href='" +
                        "/BlogRoom/apps/article.html?aid=" +
                        article.articleId +
                        "'>" +
                        "查看" +
                        "</a>" +
                        "</span><span class='muted'> <a href='javascript:;' onclick='delArticleByAid(" +
                        article.articleId + ")' data-action='ding' data-id='393' id='Addlike' class='action'><i class='fa fa-trash-o'></i>" +
                        "删除</a></span></p>" +
                        "</article>";
                }else {
                    articlenode = "<article class='excerpt' style='width: 938px' id='"+article.articleId+"'>" +
                        "<header><span class='label label-important'>审核中<i class='label-arrow'></i></span><h2><span style='color: #3fb27e;'>" + article.articleTitle + "</span></h2></header>" +
                        "<div class='focus'><span><img class='thumb' src='/upload/articlepic/" +
                        article.articlePic +
                        "' style='width: 150px;height: 100px'></span></div>" +
                        "<span class='note'>" +
                        article.articleDescribe +
                        "</span> <p class='auth-span' >" +
                        "<span class='muted'><i class='fa fa-clock-o'></i>" +
                        article.articleTime +
                        "</span><span class='muted'><i class='fa fa-edit'></i> " +
                        "<a target='_blank' href='" +
                        "/BlogRoom/apps/edit-article.html?aid=" +
                        article.articleId +
                        "'>" +
                        "修改" +
                        "</a>" +
                        "</span><span class='muted'> <a href='javascript:;' onclick='delArticleByAid(" +
                        article.articleId + ")' data-action='ding' data-id='393' id='Addlike' class='action'><i class='fa fa-trash-o'></i>" +
                        "删除</a></span></p>" +
                        "</article>";
                }
                $(".content").append(articlenode);
            }

            var pagestart = "<div class='pagination' style='width: 938px'>" +
                "<ul>" +
                "<li class='prev-page'><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+
                pagedata.prePage+
                ")'>上一页</a></li>";
            var PageNodes = "";
            for(var i = 1; i < pagedata.pages+1; i++){
                var onePageNode;
                if(pagedata.pageNum == i){
                   onePageNode = "<li class='active'><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+i+")'>"+i+"</a></li>";
                }else {
                    onePageNode = "<li><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+i+")'>"+i+"</a></li>";
                }
                PageNodes = PageNodes+onePageNode;
            }
            var pageend ="<li class='prev-page'><a href='javascript:(0);' onclick='getAllArticlesByPage("+uid+","+
                pagedata.nextPage+
                ")'>下一页</a></li>"+
                "</ul>" +
                "</div>";
            var pagenode = pagestart + PageNodes + pageend;



            $(".content").append(pagenode);
        }
    });
}

function getAllUncheckArticlesByPage(uid,pageNum) {
    $.ajax({
        url: "/BlogRoom/article/getAllUncheckArticlesOfUid",
        dataType: "json",
        data: {"pageNum": pageNum,"uid":uid},
        type: "GET",
        success: function (pagedata) {

            var data = pagedata.list;
            $(".content").empty();

            var articlenode;

            for (var i = 0; i < data.length; i++) {
                var article = data[i];

                articlenode = "<article class='excerpt' style='width: 938px' id='"+article.articleId+"'>" +
                    "<header><span class='label label-important'>审核中<i class='label-arrow'></i></span><h2><span style='color: #3fb27e;'>" + article.articleTitle + "</span></h2></header>" +
                    "<div class='focus'><span><img class='thumb' src='/upload/articlepic/" +
                    article.articlePic +
                    "' style='width: 150px;height: 100px'></span></div>" +
                    "<span class='note'>" +
                    article.articleDescribe +
                    "</span> <p class='auth-span' >" +
                    "<span class='muted'><i class='fa fa-clock-o'></i>" +
                    article.articleTime +
                    "</span><span class='muted'><i class='fa fa-edit'></i> " +
                    "<a target='_blank' href='" +
                    "/BlogRoom/apps/edit-article.html?aid=" +
                    article.articleId +
                    "'>" +
                    "修改" +
                    "</a>" +
                    "</span><span class='muted'> <a href='javascript:;' onclick='delArticleByAid(" +
                    article.articleId + ")' data-action='ding' data-id='393' id='Addlike' class='action'><i class='fa fa-trash-o'></i>" +
                    "删除</a></span></p>" +
                    "</article>";
                $(".content").append(articlenode);
            }

            var pagestart = "<div class='pagination' style='width: 938px'>" +
                "<ul>" +
                "<li class='prev-page'><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+
                pagedata.prePage+
                ")'>上一页</a></li>";
            var PageNodes = "";
            for(var i = 1; i < pagedata.pages+1; i++){
                var onePageNode;
                if(pagedata.pageNum == i){
                    onePageNode = "<li class='active'><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+i+")'>"+i+"</a></li>";
                }else {
                    onePageNode = "<li><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+i+")'>"+i+"</a></li>";
                }
                PageNodes = PageNodes+onePageNode;
            }
            var pageend ="<li class='prev-page'><a href='javascript:(0);' onclick='getAllArticlesByPage("+uid+","+
                pagedata.nextPage+
                ")'>下一页</a></li>"+
                "</ul>" +
                "</div>";
            var pagenode = pagestart + PageNodes + pageend;



            $(".content").append(pagenode);
        }
    });
}

function getAllCheckedArticlesByPage(uid,pageNum) {
    $.ajax({
        url: "/BlogRoom/article/getAllCheckedArticlesOfUid",
        dataType: "json",
        data: {"pageNum": pageNum,"uid":uid},
        type: "GET",
        success: function (pagedata) {

            var data = pagedata.list;
            $(".content").empty();

            var articlenode;

            for (var i = 0; i < data.length; i++) {
                var article = data[i];
                articlenode = "<article class='excerpt' style='width: 938px' id='"+article.articleId+"'>" +
                    "<header><span class='label label-important'>已审核<i class='label-arrow'></i></span><h2><span style='color: #3fb27e;'>" + article.articleTitle + "</span></h2></header>" +
                    "<div class='focus'><span><img class='thumb' src='/upload/articlepic/" +
                    article.articlePic +
                    "' style='width: 150px;height: 100px'></span></div>" +
                    "<span class='note'>" +
                    article.articleDescribe +
                    "</span> <p class='auth-span' >" +
                    "<span class='muted'><i class='fa fa-clock-o'></i>" +
                    article.articleTime +
                    "</span><span class='muted'><i class='fa fa-edit'></i> " +
                    "<a target='_blank' href='" +
                    "/BlogRoom/apps/article.html?aid=" +
                    article.articleId +
                    "'>" +
                    "查看" +
                    "</a>" +
                    "</span><span class='muted'> <a href='javascript:;' onclick='delArticleByAid(" +
                    article.articleId + ")' data-action='ding' data-id='393' id='Addlike' class='action'><i class='fa fa-trash-o'></i>" +
                    "删除</a></span></p>" +
                    "</article>";

                $(".content").append(articlenode);
            }

            var pagestart = "<div class='pagination' style='width: 938px'>" +
                "<ul>" +
                "<li class='prev-page'><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+
                pagedata.prePage+
                ")'>上一页</a></li>";
            var PageNodes = "";
            for(var i = 1; i < pagedata.pages+1; i++){
                var onePageNode;
                if(pagedata.pageNum == i){
                    onePageNode = "<li class='active'><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+i+")'>"+i+"</a></li>";
                }else {
                    onePageNode = "<li><a href='javascript:;' onclick='getAllArticlesByPage("+uid+","+i+")'>"+i+"</a></li>";
                }
                PageNodes = PageNodes+onePageNode;
            }
            var pageend ="<li class='prev-page'><a href='javascript:(0);' onclick='getAllArticlesByPage("+uid+","+
                pagedata.nextPage+
                ")'>下一页</a></li>"+
                "</ul>" +
                "</div>";
            var pagenode = pagestart + PageNodes + pageend;



            $(".content").append(pagenode);
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


