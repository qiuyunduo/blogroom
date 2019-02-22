$(function () {
    var userId = $("#user-id").val();
    getAttentionOfUser(userId);

});
//获得用户博客界面除文章之外的其它基本信息（用户等级，喜欢数，关注，粉丝。。。）
function getAttentionOfUser(uid) {
    $.ajax({
        url: "/BlogRoom/user/getBlogInfo",
        data: {"uid": uid},
        dataType: "json",
        type: "GET",
        success: function (data) {
            var attentions = data.attentions;
            if(attentions.length > 0) {
                $("#myattention").empty();
                for (var i = 0; i < attentions.length; i++) {
                    var guanzhuor = "<li data-v-0c56b7f6='' class='item_cont'>" +
                        "<a data-v-0c56b7f6='' href='/BlogRoom/apps/userblog.html?uid=" +
                        attentions[i].userId + "' target='_blank' class='fans'>" +
                        "<img data-v-0c56b7f6='' src='/upload/userhead/" +
                        attentions[i].userImageUrl + "' class='header'></a>" +
                        "<a data-v-0c56b7f6='' href='/BlogRoom/apps/userblog.html?uid=" +
                        attentions[i].userId + "' target='' class='nick'>" +
                        attentions[i].userName + "</a>" +
                        "<a data-v-0c56b7f6='' id='"+attentions[i].userId+"' class='watch_btn' onclick='delAttention("+
                        uid+","+attentions[i].userId+")'>取消关注</a>" +
                        "</li>";
                    $("#myattention").append(guanzhuor);
                }
            }


        },
        error: function () {
            alert("请求错误");
        }
    }, "json");

}

//添加关注
function addAttention(uid,attentionid) {
    $.ajax({
        url:"/BlogRoom/attention/addAttention",
        data:{"attentionedId":attentionid,"attentionId":uid},
        type:"POST",
        dataType:"json",
        success:function (rs) {
            if(rs > 0){
                alert("关注成功");
                $("#"+attentionid).attr("onclick","delAttention("+uid+","+attentionid+")");
                $("#"+attentionid).html("取消关注");
            }else {
                alert("关注失败");
            }

        },
        error:function () {
            alert("关注ajax请求出现错误")
        }
    });
}

function delAttention(uid,attentionid) {
    $.ajax({
        url:"/BlogRoom/attention/delAttention",
        data:{"attentionedId":attentionid,"attentionId":uid},
        type:"POST",
        dataType:"json",
        success:function (rs) {
            if(rs > 0){
                alert("取消关注成功");
                $("#"+attentionid).attr("onclick","addAttention("+uid+","+attentionid+")");
                $("#"+attentionid).html("关注");
            }else {
                alert("取消关注失败");
            }

        },
        error:function () {
            alert("关注ajax请求出现错误")
        }
    });
}
