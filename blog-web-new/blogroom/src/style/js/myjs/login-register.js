$(function() {
    //检查用户是否登录，区别显示（登录注册）还是（用户模块）

    $.ajax({
        url:"/BlogRoom/user/checkLog",
        dataType:"json",
        type:"POST",
        success:function (data) {
            if(data != null) {
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
    //弹出框
    $(document).on("click", "a[data-pop]", function (e) {
        e.preventDefault();
        var dom = $(this).data("pop");
        $("#" + dom).fadeIn(200, function () {
            $("body").css("overflow", "hidden");
            $(this).children(".pop-wp").animate({
                left: "50%"
            }, 200);
        });
    });
    //关闭弹出框
    $(document).on("click", ".pop-close", function (e) {
        e.preventDefault();
        var s = $(this);
        $("body").removeAttr("style");
        s.parent(".pop-wp").animate({
            left: "150%"
        }, 200, function () {
            s.parents(".pop").fadeOut(200, function () {
                s.parent(".pop-wp").css({"right": "auto", "left": "-50%"});
            });
        });
    });


    //登录
    $("#loginForm").validate({
        errorPlacement: function (error, element) {
            $('#error_tip_' + element[0].name).html(error);
        },
        errorElement: "span",
        onkeyup: false,
        focusInvalid: true,
        rules: {
            lusername: {
                required: true
            },
            lpassword: {
                required: true
            }
        },
        messages: {
            lusername: {
                required: "请输入用户名"
            },
            lpassword: "请输入密码"
        },
        submitHandler: function (form) {
            var s = $(form),
                url = s.attr("action"),
                btn = s.children(".account-btn");
            btn.attr("disabled", "disabled").text("登录中…");
            $.ajax({
                url:url,
                data:s.serialize(),
                type:"POST",
                dataType:"json",
                success:function (rs) {
                    if(rs > 0){
                        alert("登录成功");
                        window.location.reload();
                    }else {
                        alert("用户名或密码错误");
                        $("#loginForm")[0].reset();
                        btn.removeAttr("disabled").text("登录");
                    }
                }
            });
            return false;
        }
    });
    //注册
    $("#registerForm").validate({
        errorPlacement: function (error, element) {
            $('#error_tip_' + element[0].name).html(error);
        },
        errorElement: "span",
        onkeyup: false,
        focusInvalid: false,
        rules: {
            userName: {
                required: true
            },
            userEmail: {
                required: true,
                email: true
            },
            userPwd: {
                required: true
            },
            repassword: {
                required: true,
                equalTo: "#rpassword"
            },
            angree: {
                required: true
            }
        },
        messages: {
            userName: "请输入昵称",
            userEmail: {
                required: "请输入邮箱",
                email: "请输入正确的邮箱格式"
            },
            userPwd: "请输入密码",
            repassword: {
                required: "请再次输入密码",
                equalTo: "两次密码输入不一致"
            },
            angree: "请阅读并同意悦读FM使用协议"
        },
        submitHandler: function (form) {
            var s = $(form),
                url = s.attr("action"),
                btn = s.find(".account-btn");
            btn.attr("disabled", "disabled").text("提交中…");
            $.ajax({
                url:url,
                data:s.serialize(),
                type:"GET",
                dataType:"json",
                success:function (rs) {
                    if(rs == 1){
                        alert("注册成功");
                        window.location.reload();
                    }else{
                        if(rs == 2){
                            alert("用户名已存在！！！");
                        }else {
                            alert("数据库繁忙，注册失败");
                        }
                        $("#registerForm")[0].reset();
                        btn.removeAttr("disabled").text("注册");
                    }
                },
                error:function () {
                    alert("请求错误");
                }
            },"json");
            return false;
        }
    });

});
