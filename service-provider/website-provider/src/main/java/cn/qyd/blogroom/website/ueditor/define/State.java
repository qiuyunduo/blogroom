package cn.qyd.blogroom.website.ueditor.define;

public interface State {
    boolean isSuccess();

    void putInfo(String var1, String var2);

    void putInfo(String var1, long var2);

    String toJSONString();
}