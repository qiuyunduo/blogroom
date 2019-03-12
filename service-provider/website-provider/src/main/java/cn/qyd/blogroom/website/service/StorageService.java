package cn.qyd.blogroom.website.service;

import java.io.InputStream;

public interface StorageService {
    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param keyName       文件名
     */
    String store(InputStream inputStream, long contentLength, String contentType, String keyName);
}
