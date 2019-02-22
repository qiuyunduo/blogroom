$(function () {
    $.ajax({
        url: "/BlogRoom/user/getLogUser",
        dataType: "json",
        type: "POST",
        success: function (logUser) {
            $("#user-id").val(logUser.userId);
            $("#myoldpwd").val(logUser.userPwd);
            $("#log_img").attr("src", "/upload/userhead/" + logUser.userImageUrl);
            $("#myblog").attr("href", "/BlogRoom/apps/userblog.html?uid=" + logUser.userId);
        },
        error: function () {
            alert("请求错误");
        }
    });

    $("#updatePwdBtn").click(function () {
        if ($("#old-password").val() == null || $("#old-password").val() == "") {
            alert("请输入密码");
        } else if ($("#new-password").val() == null || $("#new-password").val() == "") {
            alert("请输入新密码");
        } else if ($("#re-password").val() == null || $("#re-password").val() == "") {
            alert("请输入确认密码");
        } else {
            var userId = $("#user-id").val();
            var userPwd = $("#myoldpwd").val();

            var oldPwd = $("#old-password").val();
            var newPwd = $("#new-password").val();
            var newPwd1 = $("#re-password").val();
            if (oldPwd != userPwd) {
                alert("密码不正确");
            } else if (newPwd != newPwd1) {
                alert("两次密码不一致");
            } else {
                updatePwd(userId, newPwd);
            }
        }
    });
});

function updatePwd(userId,userPwd) {
    $.ajax({
        url:"/BlogRoom/user/updatePwd",
        type:"POST",
        data:{"userId":userId,"userPwd":userPwd},
        dataType:"json",
        success: function (returndata) {
            if(returndata > 0) {
                alert("密码修改成功");
                window.location.reload();
            }else{
                alert("密码修改失败");
            }
        },
        error: function () {
            alert("ajax请求错误");
        }
    },"json");

}