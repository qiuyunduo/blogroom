$.ajax({
    url: "/BlogRoom/user/getLogUser",
    dataType: "json",
    type: "POST",
    success: function (logUser) {
        $("#user-id").val(logUser.userId);
        $("#log_img").attr("src", "/upload/userhead/" + logUser.userImageUrl);
        $("#myblog").attr("href", "/BlogRoom/apps/userblog.html?uid=" + logUser.userId);
    },
    error: function () {
        alert("请求错误");
    }
});
