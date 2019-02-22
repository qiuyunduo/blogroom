$.ajax({
    url:"/BlogRoom/user/getLogUser",
    dataType:"json",
    type:"POST",
    success:function (logUser) {
        $("#log_img").attr("src","/upload/userhead/"+logUser.userImageUrl);
        $("#myblog").attr("href","/BlogRoom/apps/userblog.html?uid="+logUser.userId);
        $("#shead").attr("src","/upload/userhead/"+logUser.userImageUrl);
        $("#sname").html(logUser.userName);

        $("#ssex").html(logUser.userSex);
        $("#sbirthday").html(logUser.userBirthday);
        $("#semail").html(logUser.userEmail);
        $("#desc").html(logUser.userDescription);
    },
    error:function () {
        alert("请求错误");
    }
});

$(function () {
    $("#updateHeadBtn").click(function () {
        var formdata = new FormData($("#updateHeadForm")[0]);
        $.ajax({
            url:"/BlogRoom/user/updateHead",
            type:"POST",
            data:formdata,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            dataType:"json",
            success: function (returndata) {
                if(returndata > 0) {
                    alert("修改成功");
                }else{
                    alert("修改失败");
                }
            },
            error: function () {
                alert("ajax请求错误");
            }
        },"json");
    });

    $("#updateInfo").click(function () {
        $.ajax({
            url:"/BlogRoom/user/getLogUser",
            dataType:"json",
            type:"POST",
            success:function (logUser) {
                $("#userid").val(logUser.userId);
                $("#userName").val(logUser.userName);

                $("#userSex").val(logUser.userSex);
                $("#userBirthday").val(logUser.userBirthday);
                $("#userEmail").val(logUser.userEmail);
                $("#userDescription").val(logUser.userDescription);
            },
            error:function () {
                alert("请求错误");
            }
        });
    });

    $("#updateInfoBtn").click(function () {
        $.ajax({
            url:"/BlogRoom/user/updateInfo",
            type:"POST",
            data:$("#updateInfoForm").serialize(),
            async: false,
            dataType:"json",
            success: function (returndata) {
                if(returndata == 1){
                    alert("修改成功")
                }else {
                    if(returndata == 2){
                        alert("用户名已存在");
                    }else {
                        alert("修改失败");
                    }
                }
            },
            error: function () {
                alert("ajax请求错误");
            }
        },"json");
    });
});