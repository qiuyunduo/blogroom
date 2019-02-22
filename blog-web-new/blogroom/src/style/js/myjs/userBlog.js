$(function () {
    var uid = UrlParam.param("uid");
    $.ajax({
        url:"/BlogRoom/user/checkLog",
        dataType:"json",
        type:"POST",
        success:function (data) {
            var logId = 0;
            if(data != null) {
                $("#log_img").attr("src","/upload/userhead/"+data.userImageUrl);
                $("#myblog").attr("href","/BlogRoom/apps/userblog.html?uid="+data.userId);
                logId = data.userId;
            }
            getBloginfoOfUser(uid,logId);
            getAllOfUid(uid,1);
        },
        error:function () {
            alert("请求错误");
        }
    },"json");

});
//获得用户博客界面除文章之外的其它基本信息（用户等级，喜欢数，关注，粉丝。。。）
function getBloginfoOfUser(uid,logId) {

    if(uid == logId){
        $("#blank_main").html("<h6>空空如也</h6>" +
            "<p class='remark'>去创作属于自己的第一篇文章</p>" +
            "<p>" +
            "<a href='/BlogRoom/apps/add-article.html' class='btn btn-link-blue'>写博客</a>" +
            "</p>")
    }else {
        $("#AttentionBtn").css("display","block");
        $("#AttentionBtn").attr("onclick","addAttention("+uid+","+logId+")");
    }
    $.ajax({
        url: "/BlogRoom/user/getBlogInfo",
        data: {"uid": uid},
        dataType: "json",
        type: "GET",
        success: function (data) {
            var user = data.user;
            var rank = data.rank;
            var attentions = data.attentions;
            var fans = data.fans;
            var likecounts = data.likecounts;
            var commentcounts = data.commentcounts;

            $("#user_name").html(user.userName);
            $("#user_head").attr("src","/upload/userhead/"+user.userImageUrl);


            $("#fensi").html(fans.length);
            $("#xihuan").html(likecounts);
            $("#pinglun").html(commentcounts);

            $("#dengji").html(rank.rankLevel);
            $("#chenghao").html(rank.rankName);
            $("#jifen").html(user.userMark);
            $("#jianjie").html(user.userDescription);

            if(attentions.length > 0) {
                $("#guanzhu").empty();
                for (var i = 0; i < attentions.length; i++) {
                    var guanzhuor = "<a href='/BlogRoom/apps/userblog.html?uid=" +
                        attentions[i].userId+"'><img src='/upload/userhead/" +
                        attentions[i].userImageUrl+"' alt='博主头像'" +
                        "style='float: left;width: 80px;height: 80px;padding: 5px 5px'/></a>";
                    $("#guanzhu").append(guanzhuor);
                }
            }
            if(fans.length > 0) {
                $("#fans").empty();
                for (var i = 0; i <fans.length; i++) {
                    if(fans[i].userId == logId){
                        $("#AttentionBtn").val("取消关注");
                        $("#AttentionBtn").attr("onclick","delAttention("+uid+","+logId+")");
                    }
                    var fan = "<a href='/BlogRoom/apps/userblog.html?uid=" +
                        fans[i].userId+"'><img src='/upload/userhead/" +
                        fans[i].userImageUrl+"' alt='博主头像'" +
                        "style='float: left;width: 80px;height: 80px;padding: 5px 5px'/></a>";
                    $("#fans").append(fan);
                }
            }

        },
        error: function () {
            alert("请求错误");
        }
    }, "json");

}

//获得用户的所有已审核的文章，展示给其他用户进入博客时看
function getAllOfUid(uid,pageNum) {
    $.ajax({
        url:"/BlogRoom/article/getArticlesOfUid",
        data:{"uid":uid,"pageNum":pageNum},
        dataType:"json",
        type:"GET",
        success:function (pagedata) {
            var data = pagedata.list;
            $("#wenzhang").html(pagedata.total);
            if(data.length > 0) {
                $(".content").empty();

                var articlenode;

                for (var i = 0; i < data.length; i++) {
                    var article = data[i].article;
                    var author = data[i].author;
                    articlenode = "<article class='excerpt' style='width: 865px'>" +
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

                var pagestart = "<div class='pagination'  style='width: 900px'>" +
                    "<ul>" +
                    "<li class='prev-page'><a href='javascript:;' onclick='getAllOfUid(" +
                    uid + "," + pagedata.prePage +
                    ")'>上一页</a></li>";
                var PageNodes = "";
                for (var i = 1; i < pagedata.pages + 1; i++) {
                    var onePageNode;
                    if (pagedata.pageNum == i) {
                        onePageNode = "<li class='active'><a href='javascript:;' onclick='getAllOfUid(" + uid + "," + i + ")'>" + i + "</a></li>";
                    } else {
                        onePageNode = "<li><a href='javascript:;' onclick='getAllOfUid(" + uid + "," + i + ")'>" + i + "</a></li>";
                    }
                    PageNodes = PageNodes + onePageNode;
                }
                var pageend = "<li class='prev-page'><a href='javascript:(0);' onclick='getAllOfUid(" +
                    uid + "," + pagedata.nextPage +
                    ")'>下一页</a></li>" +
                    "</ul>" +
                    "</div>";
                var pagenode = pagestart + PageNodes + pageend;


                $(".content").append(pagenode);
            }
        }
    },"json");
}

//添加关注
function addAttention(attentionedId,attentionId) {
    if(attentionId == 0){
        $("#login").fadeIn(200, function () {
            $("body").css("overflow", "hidden");
            $(this).children(".pop-wp").animate({
                left: "50%"
            }, 200);
        });
    }else {
        $.ajax({
            url: "/BlogRoom/attention/addAttention",
            data: {"attentionedId": attentionedId, "attentionId": attentionId},
            type: "POST",
            dataType: "json",
            success: function (rs) {
                if (rs > 0) {
                    alert("关注成功");
                    window.location.reload();
                } else {
                    alert("关注失败");
                }

            },
            error: function () {
                alert("关注ajax请求出现错误")
            }
        });
    }
}

//删除关注
function delAttention(attentionedId,attentionId) {
    $.ajax({
        url:"/BlogRoom/attention/delAttention",
        data:{"attentionedId":attentionedId,"attentionId":attentionId},
        type:"POST",
        dataType:"json",
        success:function (rs) {
            if(rs > 0){
                alert("取消关注成功");
                window.location.reload();
            }else {
                alert("取消关注失败");
            }

        },
        error:function () {
            alert("关注ajax请求出现错误")
        }
    });
}



