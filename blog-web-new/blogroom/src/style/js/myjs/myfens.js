$(function () {
    var userId = $("#user-id").val();
    getFenOfUser(userId);

});
//获得用户博客界面除文章之外的其它基本信息（用户等级，喜欢数，关注，粉丝。。。）
function getFenOfUser(uid) {
    $.ajax({
        url: "/BlogRoom/user/getBlogInfo",
        data: {"uid": uid},
        dataType: "json",
        type: "GET",
        success: function (data) {
            var fans = data.fans;
            if(fans.length > 0) {
                $("#myfens").empty();
                for (var i = 0; i < fans.length; i++) {
                    var fan = "<li data-v-0c56b7f6='' class='item_cont'>" +
                        "<a data-v-0c56b7f6='' href='/BlogRoom/apps/userblog.html?uid=" +
                        fans[i].userId + "' target='_blank' class='fans'>" +
                        "<img data-v-0c56b7f6='' src='/upload/userhead/" +
                        fans[i].userImageUrl + "' class='header'></a>" +
                        "<a data-v-0c56b7f6='' href='/BlogRoom/apps/userblog.html?uid=" +
                        fans[i].userId + "' target='' class='nick'>" +
                        fans[i].userName + "</a>" +
                        "</li>";
                    $("#myfens").append(fan);
                }
            }

        },
        error: function () {
            alert("请求错误");
        }
    }, "json");

}