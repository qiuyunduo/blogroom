$(function () {
    getAllUsers(1);
});

function getAllUsers(pageNum) {
    $.ajax({
        url: "/BlogRoom/user/getAll",
        dataType: "json",
        data: {"pageNum": pageNum},
        type: "GET",
        success: function (pagedata) {

            var data = pagedata.list;
            if (data.length > 0) {
                $("#allUsers").empty();
                var usernode;

                for (var i = 0; i < data.length; i++) {
                    var user = data[i];
                    usernode = "<tr id='"+user.userId+"'>" +
                        "<td>"+user.userId+"</td>" +
                        "<td class='article-title'>"+user.userName+"</td>" +
                        "<td class='hidden-sm'>"+user.userEmail+"</td>" +
                        "<td>"+user.userRegisterTime+"</td>" +
                        "<td><a onclick='lockUser("+user.userId+")'>锁定账户</a>" +
                        "</tr>";
                    $("#allUsers").append(usernode);
                }

                $("#pages").empty();
                var pagestart = "<li class='prev-page'><a href='javascript:;' onclick='getAllUsers("+
                    pagedata.prePage +
                    ")'>上一页</a></li>";
                var PageNodes = "";
                for (var i = 1; i < pagedata.pages + 1; i++) {
                    var onePageNode;
                    if (pagedata.pageNum == i) {
                        onePageNode = "<li class='active'><a href='javascript:;' onclick='getAllUsers(" + i + ")'>" + i + "</a></li>";
                    } else {
                        onePageNode = "<li><a href='javascript:;' onclick='getAllUsers("  + i + ")'>" + i + "</a></li>";
                    }
                    PageNodes = PageNodes + onePageNode;
                }
                var pageend = "<li class='prev-page'><a href='javascript:(0);' onclick='getAllUsers(" +
                    pagedata.nextPage +
                    ")'>下一页</a></li>";
                var pagenode = pagestart + PageNodes + pageend;


                $("#pages").append(pagenode);
            }
        }
    });
}


function lockUser(uid) {

    if(confirm("确认删除此篇文章吗？")){
        $.ajax({
            url:"/BlogRoom/user/lockUser",
            data:{"uid":uid},
            type:"POST",
            success:function (rs) {
                if (rs > 0) {
                    alert("锁定成功");
                } else {
                    alert("锁定失败");
                }
            },
            error:function () {
                alert("ajax请求错误");
            }
        });
    }


}


