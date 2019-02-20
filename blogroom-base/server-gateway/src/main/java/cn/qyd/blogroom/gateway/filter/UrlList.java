package cn.qyd.blogroom.gateway.filter;

public class UrlList {
    public final static String[] USER_URL = {/*"/user/**",*/
            // (二版)上传文件，获取下载URL
            "/api/oss/uplaodFile",
    };

    public final static String[] BLOG_URL = {/*"/blog/**",*/
            // 获取钱包资产列表，默认包含主币
            "/api/wallet/assets",
    };

    public final static String[] ARTICLE_URL = {/*"/article/**",*/
            // (二版)第三方API推送资讯接口
            "/api/information/push",
    };

    public final static String[] WEBSITE_URL = {/*"/website/**",*/
            // 获取行情信息
            "/api/wallet/market",
    };
}
