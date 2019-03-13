package cn.qyd.blogroom.website.service.impl;

import cn.qyd.blogroom.common.utils.CharUtil;
import cn.qyd.blogroom.website.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Author qyd
 * @Date 19-3-12 下午5:14
 **/
@Service
public class StorageServiceImpl implements StorageService {

    @Value("${blogroom.storage.local.storagePath}")
    private String localPath;

    @Value("${blogroom.storage.local.address}")
    private String address;

    private Path rootLocation;
    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      文件索引名
     */
    @Override
    public String store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        String key = generateKey(fileName);
        try {
            System.out.println(localPath);
            rootLocation = Paths.get(localPath);
            Files.copy(inputStream, rootLocation.resolve(key), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + key, e);
        }

        String url = generateUrl(key);

        return url;
    }

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;

        key = CharUtil.getRandomString(20) + suffix;
        //TODO　本应该判断生成的key是否和之前有重复，但由于概率较小暂时先放一边

        return key;
    }

    public String generateUrl(String keyName) {
        String url = address + keyName;
        return url;
    }
}
