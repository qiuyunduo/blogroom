package cn.qyd.blogroom.common.resp;


import cn.qyd.blogroom.common.resp.code.CodeAndMessage;
import cn.qyd.blogroom.common.resp.code.RespCodeAndMessageEnum;
import cn.qyd.blogroom.common.resp.paging.PagingInfo;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回数据信息
 */
public class Resp {

    @ApiModelProperty(notes = "调用结果状态")
    private RespStatus status;

    @ApiModelProperty(notes = "调用结果")
    private Object data;

    @ApiModelProperty(notes = "调用分页结果")
    private PagingInfo pagingData;


    public static Resp succeed(){
        RespCodeAndMessageEnum succeed = RespCodeAndMessageEnum.SUCCEED;
        RespStatus respStatus = new RespStatus(succeed.getCode(),succeed.getMessage());
        Resp resp = new Resp();
        resp.setStatus(respStatus);
        return resp;
    }

    public static Resp succeed(Object data){
        Resp resp = succeed();
        resp.setData(data);
        return resp;
    }

    public static Resp ifAndReturn(Boolean flag){
        if(flag) { return Resp.succeed(); }
        else { return Resp.fail(); }
    }


    public static Resp succeedPaging(PagingInfo pagingData){
        Resp resp = succeed();
        resp.setPagingData(pagingData);
        return resp;
    }


    public static Resp fail(){
        RespCodeAndMessageEnum systemError = RespCodeAndMessageEnum.SYSTEM_ERROR;
        RespStatus respStatus = new RespStatus(systemError.getCode(),systemError.getMessage());
        Resp resp = new Resp();
        resp.setStatus(respStatus);
        return resp;
    }

    public static Resp fail(String message){
        RespCodeAndMessageEnum systemError = RespCodeAndMessageEnum.SYSTEM_ERROR;
        RespStatus respStatus = new RespStatus(systemError.getCode(),message);
        Resp resp = new Resp();
        resp.setStatus(respStatus);
        return resp;
    }

    public static Resp fail(Integer code, String message){
        RespStatus respStatus = new RespStatus(code,message);
        Resp resp = new Resp();
        resp.setStatus(respStatus);
        return resp;
    }

    public static Resp fail(CodeAndMessage codeAndMessageEnum){
        RespStatus respStatus = new RespStatus(codeAndMessageEnum.getCode(),codeAndMessageEnum.getMessage());
        Resp resp = new Resp();
        resp.setStatus(respStatus);
        return resp;
    }


    public Resp() {
    }

    public Resp(RespStatus status, PagingInfo pagingData) {
        this.status = status;
        this.pagingData = pagingData;
    }

    public Resp(RespStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PagingInfo getPagingData() {
        return pagingData;
    }

    public void setPagingData(PagingInfo pagingData) {
        this.pagingData = pagingData;
    }
}
