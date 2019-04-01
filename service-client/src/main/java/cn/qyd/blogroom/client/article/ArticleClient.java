package cn.qyd.blogroom.client.article;

/**
 * @Author qyd
 * @Date 19-2-26 上午9:47
 **/
public class ArticleClient {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("sdsd");
        });
        thread.start();
        thread.run();
    }
}
