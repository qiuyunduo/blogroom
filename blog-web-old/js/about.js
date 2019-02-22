function login(anchor) {
    var returnUri = location.href;
    if (anchor && returnUri.indexOf("#" + anchor) < 0) {
        returnUri += "#" + anchor;
    }
    location.href = "https://passport.cnblogs" + getHostPostfix() + "/login.aspx?ReturnUrl=" + encodeURIComponent(returnUri);
    return false;
}

function logout() {
    if (confirm("确认退出吗？")) {
        location.href = "https://passport.cnblogs" + getHostPostfix() + "/logout.aspx?ReturnUrl=" + location.href;
    }
    return false;
}

function register() {
    location.href = "https://passport.cnblogs" + getHostPostfix() + "/register.aspx?ReturnUrl=" + location.href;
    return false;
}


function getHostPostfix() {
    var hostname = location.hostname;
    hostname = hostname.substring(hostname.lastIndexOf("."), hostname.length);
    return hostname;
}

$(function () {
    var prefixUrl = location.protocol + '//passport.cnblogs' + getHostPostfix();
    $.ajax({
        url: prefixUrl + '/user/LoginInfo',
        dataType: 'jsonp',
        success: function (data) {
            $("#top_nav_right").html(data);
            var spacerUserId = parseInt($("#current_spaceId").html());
            if (spacerUserId > 0) {
                $.ajax({
                    url: prefixUrl + '/user/NewMsgCount',
                    data: 'spaceUserId=' + spacerUserId,
                    dataType: 'jsonp',
                    success: function (data) {
                        if (data) {
                            $("#msg_count").html('(' + data + ')').show();
                        }
                    }
                });
            }
        }
    });
});