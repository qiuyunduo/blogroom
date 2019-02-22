$(function () {

    $(window).scroll(function () {
        if($(document).scrollTop() >= 212){
            $(".rollto").css("display","block");
        }else {
            $(".rollto").css("display","none");
        }
    });

    //鼠标悬浮在用户头像出现功能模块
    $("#log_img").hover(function () {
        $("#i_icon").prop("class","fa fa-chevron-down");
        $("#log_menu").css("display","block");
    },function () {
        $("#log_menu").hover(function () {
            $("#i_icon").prop("class","fa fa-chevron-down");
            $("#log_menu").css("display","block");
        },function () {
            $("#i_icon").prop("class","fa fa-chevron-up");
            $("#log_menu").css("display","none");
        });
    });

    //回到顶部
    $("button[data-type=totop]").click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 1000);
        return false;
    });
    //指向评论
    $("button[data-type=torespond]").click(function () {
        $('#comment').focus();
    });
});